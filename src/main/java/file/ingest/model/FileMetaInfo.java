package file.ingest.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FileMetaInfo {

    private List<String> columns;

    private long fileSize;

    private int noOfRow;

}
