package br.com.cron.util;
/*
 * Class by Gabriel Panza 08/11
 */
import java.io.*;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import br.com.cron.resources.*;

public class GenerateXms {

	public static void GenerateExcel(List<MyteamsCustomFieldQuery> NullList) {
		try {

			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Profarma Custom Field Null");
	
			HSSFRow rowhead = sheet.createRow((short) 0);
			rowhead.createCell(0).setCellValue("ID");
			rowhead.createCell(1).setCellValue("Team Name");
			HSSFRow row = null;

			for (int i = 0; i < NullList.size(); i++) {
				row = sheet.createRow((short) i + 1);
				row.createCell(0).setCellValue(NullList.get(i).getId().toString());
				row.createCell(1).setCellValue(NullList.get(i).getName());
			}
			for (int i = 0; i < 3; i++) {
				sheet.autoSizeColumn(i);
			}
			
			String filename = "RelatorioProfarma.xls";
			FileOutputStream fileOut = new FileOutputStream(filename);
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();

			System.out.println("Gerado Excel para envio por email!");

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
}