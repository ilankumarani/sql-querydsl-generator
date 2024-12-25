package com.ilan;


import io.ilan.GenerateSqlDslApplication;
import org.apache.maven.RepositoryUtils;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.artifact.ArtifactTypeRegistry;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.repository.RemoteRepository;
import org.eclipse.aether.resolution.ArtifactRequest;
import org.eclipse.aether.resolution.ArtifactResolutionException;
import org.eclipse.aether.resolution.ArtifactResult;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static java.lang.String.format;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

public abstract class AbstractQueryDslMojo extends AbstractMojo {

    private static final String SOURCE_CLASSIFIER = "sources";

    @Parameter
    private List<String> arguments;

    /**
     * append source artifacts to sources list
     *
     * @since 2.2.0
     */
    @Parameter(defaultValue = "false")
    private boolean appendSourceArtifacts = false;
    /**
     *
     */
    @Parameter(defaultValue = "${project}", readonly = true)
    protected MavenProject project;


    @Component
    private RepositorySystem repoSystem;

    /**
     * The current repository/network configuration of Maven.
     */
    @Parameter(defaultValue = "${repositorySystemSession}", readonly = true)
    protected RepositorySystemSession repoSession;

    /**
     * The project's remote repositories to use for the resolution of plugins and their dependencies.
     */
    @Parameter(defaultValue = "${project.remoteProjectRepositories}", readonly = true)
    private List<RemoteRepository> remoteRepos;

    /**
     *
     */
    @Parameter(property = "plugin.artifacts", readonly = true)
    private java.util.List<Artifact> pluginArtifacts;

    @Parameter(defaultValue = "false", property = "skipAnnotationProcessing")
    protected boolean skip;

    /**
     * Specify the directory where to place generated source files (same behaviour of -s option)
     */
    @Parameter
    private File outputDirectory;


    @Parameter(defaultValue = "false", property = "fork")
    protected boolean fork;

    @Override
    public void execute() throws MojoExecutionException {
        if (skip) {
            getLog().info("skipped sql-queryDsl-generation");
            return;
        }

        if (outputDirectory == null) {
            outputDirectory = getDefaultOutputDirectory();
            getLog().info("Output directory path :: " + outputDirectory.getPath().toString());
        }

        project.getDependencies();


        try {
            arguments.forEach(argument -> {
                getLog().info("Program argument :: " + argument.toString());
            });
            arguments.add("--target.outputDirectory=".concat(outputDirectory.getPath().toString()));
            String args[] = arguments.stream().toArray(String[]::new);
            GenerateSqlDslApplication.main(args);

            getLog().info("*** SQL QueryDsl generate successfully ***");
        } catch (Exception e) {
            getLog().error("Exception executing Main method :: " + e.toString());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void processSourceArtifacts(Consumer<Artifact> closure) {

        final java.util.Set<Artifact> depArtifacts = this.project.getDependencyArtifacts();
        if (depArtifacts != null) {

            for (Artifact dep : depArtifacts) {

                if (dep.hasClassifier() && SOURCE_CLASSIFIER.equals(dep.getClassifier())) {

                    if (appendSourceArtifacts) {
                        closure.accept(dep);
                    }
                    //getLog().debug("Append source artifact to classpath: " + dep.getGroupId() + ":" + dep.getArtifactId());
                    //this.sourceArtifacts.add(dep.getFile());
                } else {
                    try {
                        resolveSourceArtifact(dep).ifPresent(closure::accept);

                    } catch (ArtifactResolutionException ex) {
                        getLog().warn(format(" sources for artifact [%s] not found!", dep.toString()));
                        getLog().debug(ex);

                    }
                }
            }
        }
    }

    private Optional<Artifact> resolveSourceArtifact(Artifact dep) throws ArtifactResolutionException {

        if (!matchArtifact(dep)) {
            return empty();
        }

        final ArtifactTypeRegistry typeReg = repoSession.getArtifactTypeRegistry();

        final DefaultArtifact artifact =
                new DefaultArtifact(dep.getGroupId(),
                        dep.getArtifactId(),
                        SOURCE_CLASSIFIER,
                        null,
                        dep.getVersion(),
                        typeReg.get(dep.getType()));

        final ArtifactRequest request = new ArtifactRequest();
        request.setArtifact(artifact);
        request.setRepositories(remoteRepos);

        getLog().debug(format("Resolving artifact %s from %s", artifact, remoteRepos));

        final ArtifactResult result = repoSystem.resolveArtifact(repoSession, request);

        return ofNullable(RepositoryUtils.toArtifact(result.getArtifact()));
    }


    protected abstract File getOutputClassDirectory();

    /**
     * @return
     */
    public abstract File getDefaultOutputDirectory();

}
