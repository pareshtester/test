package TestCases;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class read1 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String filepath= "D:\\Automation\\POI\\Book1.xlsx";
		FileInputStream fis = new FileInputStream(filepath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator iterator = sheet.iterator();
		
		while(iterator.hasNext())
		{
			XSSFRow row =  (XSSFRow)iterator.next();
			Iterator  cell  =  row.cellIterator();
			
			while(cell.hasNext())
			{
			   XSSFCell celldata =(XSSFCell) cell.next();
			   
			   switch(celldata.getCellType())
			   {
			   case STRING :System.out.print(celldata.getStringCellValue()); break;
			   
			   case NUMERIC :System.out.print(celldata.getNumericCellValue()); break;
			   
			   case BOOLEAN :System.out.print(celldata.getBooleanCellValue()); break;

			   
			   }
			}
			System.out.print(" | ");
		}
	

	}

}
