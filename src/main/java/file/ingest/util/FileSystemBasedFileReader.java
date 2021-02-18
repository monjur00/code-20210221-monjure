package file.ingest.util;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.function.Function;

public class FileSystemBasedFileReader implements Function<String, Path> {

    @Override
    public Path apply(String filePath) {
        if(Objects.isNull(filePath) || filePath.isBlank())
            throw new IllegalArgumentException("Not a valid file path");

        return Paths.get(filePath);
    }

}
