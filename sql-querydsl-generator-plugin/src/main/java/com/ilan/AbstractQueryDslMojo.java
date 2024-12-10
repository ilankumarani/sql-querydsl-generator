package com.ilan;


import io.ilan.GenerateSqlDslApplication;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.util.List;

public abstract class AbstractQueryDslMojo extends AbstractMojo {


    @Parameter
    private List<String> arguments;
    /**
     *
     */
    @Parameter(defaultValue = "${project}", readonly = true)
    protected MavenProject project;

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


    protected abstract File getOutputClassDirectory();

    /**
     * @return
     */
    public abstract File getDefaultOutputDirectory();

}
