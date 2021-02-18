package file.ingest.util;

import file.ingest.model.Arguments;

import java.util.Objects;
import java.util.function.Function;

public class ExtractArguments implements Function<String[], Arguments> {

    @Override
    public Arguments apply(String[] args) {
        if(args.length != 2)
            throw new IllegalArgumentException("Please provide valid arguments");

        if(!(Objects.equals(args[0], "-p") || Objects.equals(args[0], "-u")))
            throw new IllegalArgumentException("Please provide valid arguments");

        if(Objects.isNull(args[1]) || args[1].isBlank())
            throw new IllegalArgumentException("Please provide valid arguments");

        return Arguments.builder()
                .fileSystemBasedPath(Objects.equals(args[0], "-p"))
                .path(args[1])
                .build();
    }

}
