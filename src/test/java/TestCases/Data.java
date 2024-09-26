package TestCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;


public class Data {
	
	@Test
	public String[][] readData() throws EncryptedDocumentException, IOException
	{

	  FileInputStream excelFile = new FileInputStream(new File("D:\\Automation\\GoogleTags\\TagData.xlsx") );
	  Workbook workbook = WorkbookFactory.create(excelFile);
	  Sheet sheet = workbook.getSheetAt(0);
	  int rows = sheet.getLastRowNum() + 1;
	  System.out.println("row no"+rows);
      int cols = sheet.getRow(1).getLastCellNum();
      String[][] data = new String[rows][cols];
      
      for (int i = 0; i < rows; i++) {
          Row row = sheet.getRow(i);
          for (int j = 0; j < cols; j++) {
              Cell cell = row.getCell(j);
              data[i][j] = cell.toString(); // Assuming everything is treated as String
          }
      }
      workbook.close();
      
      for (String[] row : data) {
          for (String cellData : row) {
              System.out.print(cellData + "\t");
          }
          System.out.println();
      }
      return data;
}
}
