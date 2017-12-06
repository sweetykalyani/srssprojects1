package in.srssprojects.utils;

import java.io.FileInputStream;

import jxl.Sheet;
import jxl.Workbook;

public class DataProviderClass {
	static Workbook book;
	static Sheet sh;

	public static String[][] getExcelContents(String fileName, String sheetName) {
		String[][] sheetData = null;
		try {

			FileInputStream fis = new FileInputStream(fileName);
			book = Workbook.getWorkbook(fis);
			sh = book.getSheet(sheetName);
			int nor = sh.getRows();
			int noc = sh.getColumns();
			sheetData = new String[nor - 1][noc];
			for (int r = 1; r < nor; r++) {
				for (int c = 0; c < noc; c++) {
					sheetData[r - 1][c] = sh.getCell(c, r).getContents();
				}
			}
		} catch (Exception e) {

		}
		return sheetData;
	}

}
