package E2E.tests;
import E2E.utils.CSVFileReader;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class csvTest {

  @Test
    public void csvValue() throws IOException, CsvException {
      String path = System.getProperty("user.dir")+"\\src\\test\\java\\E2E\\Data\\test.csv";
     List<String[]> allRowList = CSVFileReader.csvReader(path);

     for(int i=1;i<allRowList.size();i++)
     {
         String[] row = allRowList.get(i);
         System.out.println(row[3]);
     }
  }
}
