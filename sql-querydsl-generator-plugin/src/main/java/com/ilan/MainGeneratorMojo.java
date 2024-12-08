package com.ilan;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

import java.io.File;

@Mojo(name = "sql-querydsl", threadSafe = true, requiresDependencyResolution = ResolutionScope.COMPILE, defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class MainGeneratorMojo extends AbstractQueryDslMojo {

    @Parameter(defaultValue = "${project.build.directory}/generated-sources/sql", required = true)
    private File defaultOutputDirectory;

    /**
     * Set the destination directory for class files (same behaviour of -d option)
     */
    @Parameter(defaultValue = "${project.build.outputDirectory}")
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
