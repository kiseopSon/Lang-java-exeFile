package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//업로드 기능만(100mb까지만), 업데이트나, 삭제기능은 제외.
public class excelupload {
	
	private final int maxSize = 1024*1024*100;
	private File path = null;
	private String sheetName = "";
	private int sheetCnt = 0;
	
	
	void Exceldownload(String path, List<Map<String, Object>>itemList,  String sheetName,int sheetCnt) throws FileNotFoundException, NullPointerException, IOException, Exception {
		excelbasic basic = new excelbasic();
		if(this.maxSize > this.path.length() && this.path.toString().endsWith(".xlsx")) {
			FileInputStream fileInput = null;
			FileOutputStream fileOut = null;
			XSSFWorkbook xssfWorkbook = null;
			XSSFSheet xssfsheet = null;
			Row row = null;
			Cell cell = null;
			
			int rowMax = 0;
			this.sheetName = sheetName;
			this.sheetCnt = sheetCnt;
			int listCnt = itemList.size();
			
			try {
				basic.Excelbasic(this.path.toString(), this.sheetName, this.sheetCnt);
				fileInput = new FileInputStream(this.path);
				xssfWorkbook = new XSSFWorkbook(fileInput);
				if(listCnt != this.sheetCnt) {
					for (int i = 0; i < listCnt; i++) {
						xssfWorkbook.createSheet(this.sheetName+(i+1));
					}
					sheetCnt = xssfWorkbook.getNumberOfSheets();//시트총갯수
				}
				
				if(sheetCnt < listCnt || sheetCnt == 0) throw new NullPointerException("갯수가 다름.");
				
				xssfsheet = xssfWorkbook.getSheetAt(sheetCnt-1);//2번째 있는 시트 고정.
				rowMax = xssfsheet.getPhysicalNumberOfRows();//전체 행 갯수
				row = xssfsheet.createRow(rowMax);//그 다음줄에 새롭게 행추가.
				
				int cellCnt = 0;
				
				for (Entry<String, Object> element : itemList.get(listCnt).entrySet()) {
					cell = row.createCell(cellCnt);
					cell.setCellValue(element.getKey());
					cellCnt++;
				}
				
				cellCnt = 0;
				row = xssfsheet.createRow(rowMax+1);
				
				for (Entry<String, Object> element : itemList.get(listCnt).entrySet()) {
					cell = row.createCell(cellCnt);
					String value = (String) element.getValue();
					cell.setCellValue(value);
					cellCnt++;
				}
				
				fileOut = new FileOutputStream(this.path);
				xssfWorkbook.write(fileOut);
				
			} catch (FileNotFoundException e) {
				System.out.println("FileNotFoundException - exceldownload.Exceldownload");
			} catch (NullPointerException e) {
				System.out.println("NullPointerException - exceldownload.Exceldownload");
			} catch (IOException e) {
				System.out.println("IOException - exceldownload.Exceldownload");
			} catch (Exception e) {
				System.out.println("Exception - exceldownload.Exceldownload");
			} finally {
				fileInput.close();
				xssfWorkbook.close();
				fileOut.close();
			}
		}//if
	}
}