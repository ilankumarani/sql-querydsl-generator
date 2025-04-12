package com.ilan;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

import java.io.File;

@Mojo(name = "sql-querydsl-test", threadSafe = true, requiresDependencyResolution = ResolutionScope.TEST, defaultPhase = LifecyclePhase.GENERATE_TEST_SOURCES)
public class TestGeneratorMojo extends AbstractQueryDslMojo {

    @Parameter(defaultValue = "${project.build.directory}/generated-test-sources/sql-test", required = true)
    private File defaultOutputDirectory;

    /**
     * Set the destination directory for class files (same behaviour of -d option)
     */
    @Parameter(defaultValue = "${project.build.testOutputDirectory}")
    private File outputClassDirectory;


    @Override
    public File getDefaultOutputDirectory() {
        return defaultOutputDirectory;
    }

    @Override
    protected File getOutputClassDirectory() {
        return outputClassDirectory;
    }
}
