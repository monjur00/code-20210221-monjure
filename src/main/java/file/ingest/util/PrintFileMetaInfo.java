package file.ingest.util;

import file.ingest.model.FileMetaInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

@Slf4j
public class PrintFileMetaInfo implements Consumer<FileMetaInfo> {

    @Override
    public void accept(FileMetaInfo fileMetaInfo) {
        log.info("Printing file meta information...");
        log.info("Column headers : {}", fileMetaInfo.getColumns());
        log.info("File size {} bytes", fileMetaInfo.getFileSize());
        log.info("Total no of rows: {}", fileMetaInfo.getNoOfRow());
    }
}
