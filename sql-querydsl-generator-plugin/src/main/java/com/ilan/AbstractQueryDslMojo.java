package com.ilan;


import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractQueryDslMojo extends AbstractMojo {


    @Parameter
    private String[] arguments;
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
        }


        // Define JVM arguments
        String jvmArgs = "-Xmx512m -Dproperty-value";

        getLog().info("Statement 1");
        // Define application arguments
        String appArgs = "arg1 arg2";
        // Create the command list
        List<String> command = new ArrayList<>();
        command.add("java");
        // Add JVM arguments
        if (!jvmArgs.isEmpty()) {
            for (String arg : jvmArgs.split("\\s+")) {
                command.add(arg);
            }
        }
        getLog().info("Statement 2");
        // Add the main class
        // Define the main class to run
        String mainClass = io.ilan.GenerateSqlDslApplication.class.getCanonicalName();
        command.add(mainClass);
        // Add application arguments
        if (!appArgs.isEmpty()) {
            for (String arg : appArgs.split("\\s+")) {
                command.add(arg);
            }
        }
        getLog().info("Statement 3");
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.inheritIO();

        try {
            //GenerateSqlDslApplication.main(arguments);
            Process process = processBuilder.start();
            process.waitFor();
            Integer exitCode = process.exitValue();
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
