package com.ilan;

//import io.ilan.GenerateSqlDslApplication;
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

    /**
     * Allows running the compiler in a separate process.
     * If false it uses the built in compiler, while if true it will use an executable.
     * <p>
     * to set source and target use
     * <pre>
     *  maven.processor.source
     *  maven.processor.target
     * </pre>
     *
     * @since 3.3
     */
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


// Define the main class to run
        String mainClass = "com.example.MainClass";
// Define JVM arguments
        String jvmArgs = "-Xmx512m -Dproperty-value";
        
        String appArgs = "A  B";
// Define application arguments String appArgs = "arg1 arg2";
// Create the command list
        List<String> command = new ArrayList<>();
        command.add("java");
// Add JVM arguments
        if (!jvmArgs.isEmpty()) {
            for (String arg : jvmArgs.split("\\s+")) {
                command.add(arg);
            }
        }
// Add the main class
        command.add(mainClass);
// Add application arguments
        if (!appArgs.isEmpty()) {
            for (String arg : appArgs.split("\\s+")) {
                command.add(arg);
            }

        }
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.inheritIO();

        try {
            //GenerateSqlDslApplication.main(arguments);
            Process process = processBuilder.start();
            process.waitFor();
            Integer exitCode = process.exitValue();
            getLog().info("*** SQL QueryDsl generate successfully ***");
        } catch (Exception e) {
            getLog().info("Exception executing Main method :: " + e.toString());
            throw new RuntimeException(e);
        }
    }


    protected abstract File getOutputClassDirectory();

    /**
     * @return
     */
    public abstract File getDefaultOutputDirectory();

}
