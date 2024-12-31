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

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

public abstract class AbstractQueryDslMojo extends AbstractMojo {

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

        //processProjectArtifacts();


        try {
            arguments.forEach(argument -> {
                getLog().info("Program argument :: " + argument.toString());
            });
            arguments.add("--target.outputDirectory=".concat(outputDirectory.getPath().toString()));
            String args[] = arguments.stream().toArray(String[]::new);

            List<String> files = this.project.getCompileSourceRoots();
            files.forEach(sourceRoot -> {
                File sourceDirectory = new File(sourceRoot);

                try {
                    processSourceDirectory(sourceDirectory);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            GenerateSqlDslApplication.main(args);

            getLog().info("*** SQL QueryDsl generate successfully ***");
        } catch (Exception e) {
            getLog().error("Exception executing Main method :: " + e.toString());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void processSourceDirectory(File sourceDir) throws IOException {
        if (sourceDir.isDirectory()) {
            File[] files = sourceDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".java")) {
                        getLog().info("Found source file: " + file.getName()); // Further processing of the file
                        compileAndAddToClasspath(file);
                    }
                }
            }
        }
    }

    private void compileAndAddToClasspath(File javaFile) throws IOException {

        try (FileReader fileReader = new FileReader(javaFile)) {
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            if (compiler == null) {
                getLog().error("No Java compiler found. Make sure you are using JDK, not a JRE");
                return;
            }

            StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
            Iterable<? extends javax.tools.JavaFileObject> compileUtils = fileManager.getJavaFileObjects(javaFile);
            Boolean compilationSuccess = compiler.getTask(null, fileManager, null, null, null, compileUtils).call();
            fileManager.close();

            if (compilationSuccess){
                getLog().info("Compilation successful for Java file" + javaFile.getName());

                String className = javaFile.getName().replace(".java", "");
                File parentDir = javaFile.getParentFile();
                URLClassLoader classLoader = new URLClassLoader(new URL[]{parentDir.toURI().toURL()}, Thread.currentThread().getContextClassLoader());

                Thread.currentThread().setContextClassLoader(classLoader);

                getLog().info("Class Loaded to classPath: "+ className);
            }
            else{
                getLog().error("Compilation failed for Java file" + javaFile.getName());
            }
            /*int compilationResult = compiler.run(null, null, null, javaFile.getPath());
            if (compilationResult == 0) {

            }else{
                getLog().error("Compilation failed for Java file" + javaFile.getName());
            }*/
        }
    }

    /*private void processProjectArtifacts() {
        final java.util.Set<Artifact> depArtifacts = this.project.getDependencyArtifacts();
        if (depArtifacts != null) {

            for (Artifact dep : depArtifacts) {

                try {
                    resolveSourceArtifact(dep);
                } catch (ArtifactResolutionException ex) {
                    getLog().warn(format("Hey Ilan artifact [%s] not found!", dep.toString()));
                    getLog().debug(ex);

                }
            }
        }
    }

    private Optional<Artifact> resolveSourceArtifact(Artifact dep) throws ArtifactResolutionException {

        final ArtifactTypeRegistry typeReg = repoSession.getArtifactTypeRegistry();

        final DefaultArtifact artifact =
                new DefaultArtifact(dep.getGroupId(),
                        dep.getArtifactId(),
                        null,
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
*/

    protected abstract File getOutputClassDirectory();

    /**
     * @return
     */
    public abstract File getDefaultOutputDirectory();

}
