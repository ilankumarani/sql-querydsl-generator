import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;

public class AbstractQueryDslMojo extends AbstractMojo {


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
            getLog().info("skipped");
            return;
        }
    }
}
