package file.ingest.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Arguments {

    private boolean fileSystemBasedPath;

    private String path;
}
