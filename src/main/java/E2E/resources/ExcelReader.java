package E2E.resources;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelReader {

    public XSSFSheet getExcelData() throws IOException {
        String ExcelPath = System.getProperty("user.home") + "\\Downloads\\exceldemo.xlsx";
        FileInputStream fis = new FileInputStream(ExcelPath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        int indexOfSheet = workbook.getSheetIndex("Main");
        XSSFSheet sheet = workbook.getSheetAt(indexOfSheet);
        return sheet;
    }

    public ArrayList getRowValue(int rowSno) throws IOException {
        ArrayList a = new ArrayList();
        XSSFSheet sheet = this.getExcelData();
        Iterator<Row> rows = sheet.iterator();

        int rowCount = sheet.getLastRowNum();
        rows.next();
        for (int i = 0; i <= rowCount; i++) {
            Row currentRow = rows.next();
            int colCount = currentRow.getLastCellNum();
            double cellValue = currentRow.getCell(0).getNumericCellValue();

            if (cellValue == rowSno) {
                for (int j = 0; j < colCount; j++) {
                    Cell currentCell = currentRow.getCell(j);

                    if (currentCell.getCellType() == CellType.STRING) {
                        a.add(currentRow.getCell(j).getStringCellValue());
                    } else {
                        a.add(currentRow.getCell(j).getNumericCellValue());
                    }
                }
                break;
            }
        }
        return a;
    }

    }
