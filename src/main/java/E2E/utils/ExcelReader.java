package E2E.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelReader {

    public XSSFSheet getExcelData(String filename,String sheetName) throws IOException {
        int indexOfSheet = 0;
        String ExcelPath = System.getProperty("user.home") + "\\Downloads\\"+filename+".xlsx";
        FileInputStream fis = new FileInputStream(ExcelPath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        if(sheetName!=null) {
             indexOfSheet = workbook.getSheetIndex(sheetName);
        }
        else {
         indexOfSheet = workbook.getSheetIndex("Main");}
        return workbook.getSheetAt(indexOfSheet);
    }

    public ArrayList getRowValue(int rowSno) throws IOException {
        ArrayList a = new ArrayList();
        XSSFSheet sheet = this.getExcelData("exceldemo","Main");
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

    public ArrayList<String> getInputRow(String filename,String sheetName,String TestcaseName) throws IOException {

        XSSFSheet sheet = this.getExcelData(filename,sheetName);
        Iterator<Row> rows = sheet.iterator();
        ArrayList<String> a = new ArrayList<String>();

        while(rows.hasNext())
        {
            Row currentRow = rows.next();
            int cellCount = currentRow.getLastCellNum();
            if(currentRow.getCell(0).getStringCellValue().equalsIgnoreCase(TestcaseName))
            {

               for(int i=1;i<cellCount;i++) {
                   if(currentRow.getCell(i).getCellType()==CellType.STRING) {
                       a.add(currentRow.getCell(i).getStringCellValue());
                   }
                   else
                   {
                     String value = String.valueOf(currentRow.getCell(i).getNumericCellValue());
                       a.add(value);
                   }
               }
               break;
            }
        }
        return a;

    }

    private int getColumnIndex(String path,String columnName) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Main");
        Iterator<Row> rows = sheet.iterator();

        Row header = rows.next();
       Iterator<Cell> cell = header.cellIterator();

       while(cell.hasNext())
       {
           Cell currentCell = cell.next();
          if( currentCell.getStringCellValue().equalsIgnoreCase(columnName))
          {
            return currentCell.getColumnIndex();
          }
       }

        workbook.close();
        fis.close();
       return -1;

    }

    private int getRowIndex(String path,String productName) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Main");
        int rowCount = sheet.getPhysicalNumberOfRows();
        int columnCount = sheet.getRow(0).getLastCellNum();

        for(int i=0;i<rowCount;i++)
        {
            Row currentRow = sheet.getRow(i);
            for(int j=0;j<columnCount;j++) {
                Cell currentCell = currentRow.getCell(j);
                if(currentCell.getCellType()==CellType.STRING)
                {
                   if (currentCell.getStringCellValue().equalsIgnoreCase(productName))
                   {
                      return currentCell.getRowIndex();
                   }
                }
            }

        }

        workbook.close();
        fis.close();
        return -1;

    }

    public boolean getUpdateFlag(String ExcelPath,String ProductName,String columnName,int productValue) throws IOException {
        int rowIndex = getRowIndex(ExcelPath,ProductName);
        int columnIndex = getColumnIndex(ExcelPath,columnName);

        FileInputStream fis = new FileInputStream(ExcelPath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Main");
        sheet.getRow(rowIndex).getCell(columnIndex).setCellValue(productValue);

        FileOutputStream fos = new FileOutputStream(ExcelPath);
        workbook.write(fos);
        fos.close();

        double value = sheet.getRow(rowIndex).getCell(columnIndex).getNumericCellValue();
        fis.close();
        workbook.close();
        return value == productValue;
    }

    }
