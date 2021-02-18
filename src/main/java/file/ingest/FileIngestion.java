package file.ingest;

import file.ingest.model.Arguments;
import file.ingest.model.FileMetaInfo;
import file.ingest.util.*;

/**
 * A simple java program to read a csv file seperated by ';' and print basic file information
 * like header names, file size, no if rows etc.
 * File can be provided as file system path arguments or file url arguments. For more info please refer README.
 */
public class FileIngestion {

    public static void main(String[] args){
        ExtractArguments extractArguments = new ExtractArguments();
        FileSystemBasedFileReader fileSystemBasedFileReader = new FileSystemBasedFileReader();
        ExtractFileMetaData extractFileMetaData = new ExtractFileMetaData();
        UrlBasedFileReader urlBasedFileReader = new UrlBasedFileReader();
        PrintFileMetaInfo printFileMetaInfo = new PrintFileMetaInfo();

        Arguments arguments = extractArguments.apply(args);
        FileMetaInfo metaInfo;

        if(arguments.isFileSystemBasedPath()){
            metaInfo = fileSystemBasedFileReader.andThen(extractFileMetaData)
                    .apply(arguments.getPath());
        }else {
            metaInfo = urlBasedFileReader.andThen(extractFileMetaData)
                    .apply(arguments.getPath());
        }

        printFileMetaInfo.accept(metaInfo);
    }

}
