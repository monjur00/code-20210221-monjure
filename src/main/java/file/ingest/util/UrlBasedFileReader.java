package file.ingest.util;

import lombok.NonNull;
import lombok.SneakyThrows;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;

public class UrlBasedFileReader implements Function<String, Path> {

    @SneakyThrows
    @Override
    public Path apply(@NonNull String fileUrl) {
        if(Objects.isNull(fileUrl) || fileUrl.isBlank())
            throw new IllegalArgumentException("Not a valid file url");

        File tempFile = File.createTempFile("temp_", UUID.randomUUID().toString());
        tempFile.deleteOnExit();

        try (InputStream in = URI.create(fileUrl).toURL().openStream()) {
            Files.copy(in, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        return tempFile.toPath();
    }
}
