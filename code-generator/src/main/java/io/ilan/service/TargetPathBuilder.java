package io.ilan.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Slf4j
public class TargetPathBuilder {

    private final ResourceLoader resourceLoader;

    /**
     * Get target path generated-test-source, this method is just for test case
     *
     * @return
     */
    public Path getTargetPath() {
        log.info("Hey Ilan ResourceLoader bean found ? TRUE:FALSE :: {}", Objects.nonNull(resourceLoader));
        Path resourcePath = null;
        try {
            URL resourceUrl = resourceLoader.getResource("").getURL();
            log.debug("ResourceUrl from the  :: {}", resourceUrl);
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
