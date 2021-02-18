package file.ingest.util;

import file.ingest.model.FileMetaInfo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
public class ExtractFileMetaData implements Function<Path, FileMetaInfo> {

    @SneakyThrows
    @Override
    public FileMetaInfo apply(Path path) {
        List<String> lines = Files.readAllLines(path);

        Optional<String> header = lines.stream()
                .findFirst();

        String[] headerCols = header.map(e -> e.split(";"))
                .orElseThrow(() -> new NoSuchFieldException("Header not available"));



        return FileMetaInfo.builder()
                .columns(Arrays.asList(headerCols))
                .fileSize(Files.size(path))
                .noOfRow(lines.size())
                .build();
    }
}
