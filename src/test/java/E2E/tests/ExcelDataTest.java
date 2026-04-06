package E2E.tests;

import E2E.TestComponents.BaseTest;
import E2E.resources.ExcelReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelDataTest {

    ExcelReader excelReader = new ExcelReader();

    @DataProvider(name="getAllRows")
    public Object[][] getData() throws IOException {
        DataFormatter formatter = new DataFormatter();
        XSSFSheet sheet = excelReader.getExcelData("exceldemo");
        int rowCount = sheet.getPhysicalNumberOfRows(); //all value present count
        Iterator<Row> rows = sheet.iterator();
        Row Header = rows.next(); // you are in header row now
        int colCount = Header.getLastCellNum(); // starts from 0 and ends with +1

        Object[][] data = new Object[rowCount - 1][colCount];
        for (int i = 0; i < rowCount - 1; i++) {
            Row currentRow = rows.next();
            for (int j = 0; j < colCount; j++) {
                data[i][j] = formatter.formatCellValue(currentRow.getCell(j));
                System.out.println(data[i][j]);
            }
        }
        return data;
    }


    @Test(dataProvider="getAllRows")
    public void verifyDataFromExcel(String sno,String greeting,String name,String age) throws IOException {
        System.out.println(sno+" "+greeting+" "+name+" "+age);
    }

    @Test
    public void getRowBySno() throws IOException {
       ArrayList a = excelReader.getRowValue(1);
       System.out.println(a);
    }

    @Test
    public void writeExcel() throws IOException {
        String ExcelPath = System.getProperty("user.home") + "\\Downloads\\exceldemo1.xlsx";
        boolean updateFlag = excelReader.getUpdateFlag(ExcelPath,"Apple","price",569);
        Assert.assertTrue(updateFlag);
    }
}
