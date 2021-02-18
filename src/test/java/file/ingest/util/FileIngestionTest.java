package file.ingest.util;

import file.ingest.model.Arguments;
import file.ingest.model.FileMetaInfo;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileIngestionTest {

    private final FileSystemBasedFileReader fileSystemBasedFileReader = new FileSystemBasedFileReader();
    private final ExtractFileMetaData extractFileMetaData = new ExtractFileMetaData();
    private final UrlBasedFileReader urlBasedFileReader = new UrlBasedFileReader();
    private final ExtractArguments extractArguments = new ExtractArguments();

    private final List<String> columns = Arrays.asList("No", "Country", "Level of development",
            "European Union Membership", "Currency", "Women Entrepreneurship Index",
            "Entrepreneurship Index", "Inflation rate", "Female Labor Force Participation Rate");


    @Test
    void validateArguments() {
        Arguments arguments = extractArguments.apply(new String[]{"-u", "https://raw.githubusercontent.com/vamstar/challenge/master/Dataset3.csv"});

        assertFalse(arguments.isFileSystemBasedPath());
        assertEquals(arguments.getPath(), "https://raw.githubusercontent.com/vamstar/challenge/master/Dataset3.csv");
    }

    @Test
    void validateWrongArguments1() {
        assertThrows(IllegalArgumentException.class, () ->
                extractArguments.apply(new String[]{"a", ""}));
    }

    @Test
    void validateWrongArguments2() {
        assertThrows(IllegalArgumentException.class, () ->
                extractArguments.apply(new String[]{"a"}));
    }

    @Test
    void readFromUrl() {
        String fileUrl = "https://raw.githubusercontent.com/vamstar/challenge/master/Dataset3.csv";
        FileMetaInfo fileMetaInfo = urlBasedFileReader.andThen(extractFileMetaData)
                .apply(fileUrl);

        //For this case assuming that file content is static
        assertEquals(fileMetaInfo.getColumns(), columns);
        assertEquals(fileMetaInfo.getNoOfRow(), 52);
    }

    @Test
    void readFromFileSystem() {
        String filePath = "Dataset3.csv";
        FileMetaInfo fileMetaInfo = fileSystemBasedFileReader.andThen(extractFileMetaData)
                .apply(new File(filePath).getAbsolutePath());

        //For this case assuming that file content is static
        assertEquals(fileMetaInfo.getColumns(), columns);
        assertEquals(fileMetaInfo.getNoOfRow(), 52);
    }

    @Test
    void readFromInvalidUrl() {
        assertThrows(IllegalArgumentException.class, () ->
                urlBasedFileReader.andThen(extractFileMetaData)
                        .apply("  "));
    }

    @Test
    void readFromInvalidFileSystem() {
        assertThrows(IllegalArgumentException.class, () ->
                fileSystemBasedFileReader.andThen(extractFileMetaData)
                        .apply(null));
    }

}