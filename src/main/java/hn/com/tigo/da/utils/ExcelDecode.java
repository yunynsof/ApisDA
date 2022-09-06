
package hn.com.tigo.da.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDecode {
    public static List<List<String>> decodeExcel(final String excelB64,final long nuRow) throws IOException, InvalidFormatException {
		byte[] decodedExcel = Base64.decodeBase64(excelB64);
		InputStream targetStream = new ByteArrayInputStream(decodedExcel);
		XSSFWorkbook wb = new XSSFWorkbook(targetStream);
        XSSFSheet sheet = wb.getSheetAt(0);
        List<List<String>> globalLista = new ArrayList<List<String>>();   
        DataFormatter formatter = new DataFormatter();
        for (int i = 1; i <= nuRow; ++i) {
        	List<String> lista = new ArrayList<String>();
        	if(sheet.getRow(i)!=null) {
        		XSSFRow row = sheet.getRow(i);
        		lista.add(formatter.formatCellValue(row.getCell(0)));
                lista.add(formatter.formatCellValue(row.getCell(1)));
                lista.add(formatter.formatCellValue(row.getCell(2)));
                lista.add(formatter.formatCellValue(row.getCell(3)));
                lista.add(formatter.formatCellValue(row.getCell(4))); 
                globalLista.add(lista);
        	}
        }
        wb.close();
		return globalLista;
    }
}
