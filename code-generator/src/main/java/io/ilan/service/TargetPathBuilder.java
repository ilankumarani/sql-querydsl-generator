package io.ilan.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Service
public class TargetPathBuilder {

    private final ResourceLoader resourceLoader;

    /**
     * Get target path generated-test-source, this method is just for test case
     *
     * @return
     */
    public Path getTargetPath() {
        Path resourcePath = null;
        try {
            URL resourceUrl = resourceLoader.getResource("").getURL();
            resourcePath = Paths.get(resourceUrl.toURI());
        } catch (Exception e) {
            throw new RuntimeException("Hey Ilan getting error while getting resource URL");
        }
        Path absolutePath = resourcePath.toAbsolutePath();
        String targetPath = absolutePath.toString();

        if (targetPath.contains("target")) {
            targetPath = targetPath.substring(0, targetPath.indexOf("target") + 6);
        }
        Path srcMain = Paths.get(targetPath.toString());
        return srcMain.resolve("generated-sources");
    }
}
