package E2E.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVFileReader {

    public static List<String[]> csvReader(String path) throws IOException, CsvException {
        CSVReader reader = new CSVReader(new FileReader(path));
       List<String[]> allRowList =reader.readAll();
        return allRowList;
    }

}
