package excel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class exceltxt{
	private FileInputStream Fileinputstream = null;
	private FileWriter Filewriter = null;
	private BufferedWriter Bufferedwriter = null;
	private Workbook Workbook = null;
	private Sheet Sheet = null;
	private Row row = null;
	private Cell cell = null;
	private String value = null;
	private Iterator<Row> Iterator = null;
	private File Oldpath = null;
	private File Newpath = null;
	
	/**
	 * @param readFilePath:정보를 받아올 경로 + .xlsx파일(확장자명 포함), filePath : 작성할 경로및파일(확장자명포함). 
	 * @throws IOException,NullPointerException,Exception
	 * @return "S" or "F"
	 * 
	 * @see 
	 * ※필수 라이브러리※ 
	 * commons-collection4 4.1, itextpdf5.3.4, xmlbeans3.0.1, 
	 * poi3.16, poi-ooxml3.16, ooxml-schemas1.4
	 * 
	 * @Warnings 
	 * 텍스트형식이 아니거나 그림같은것이 함께있는 파일은 에러발생.
	 * 클래스 생성자는 이슈이후 삭제할 예정.
	 * 첫시트 정보밖에 가져오지 못함.
	 * 첫시트 이름도 파일명으로 따라가게됨.
	 * excelFunction() {}; 현재 위치를 수정중이라, 생성자를 생성하지않으면 nullPointException발생.
	 */
	void txt(String oldPath, String newPath) throws IOException, NullPointerException, Exception {
		this.Oldpath = new File(oldPath);
		this.Newpath = new File(newPath);
		
		if(oldPath.endsWith(".txt")) {
			try{
				Fileinputstream = new FileInputStream(this.Oldpath);
				Workbook = WorkbookFactory.create(Fileinputstream);
				Filewriter = new FileWriter(this.Newpath);
				Bufferedwriter = new BufferedWriter(Filewriter);
				Sheet = Workbook .getSheetAt(0);
				int rows = Sheet.getPhysicalNumberOfRows();
				int cells = Sheet.getRow(0).getPhysicalNumberOfCells();
				
				for (int i = 0; i < rows; i++) {
					row = Sheet.getRow(i);
					
					if(row != null) {
						for (int j = 0; j < cells; j++) {
							cell = row.getCell(j);
							
							if(cell != null) {
								value = cell.getStringCellValue()+",";
								Bufferedwriter.write(value);
							}
						}
						Bufferedwriter.write("\n");
					}
				}
				
			} catch (IOException e) {
				System.out.println("IOException - exceltxt.txt");
			} catch (NullPointerException e) {
				System.out.println("NullPointerException - exceltxt.txt");
			}catch (Exception e) {
				System.out.println("Exception - exceltxt.txt");
			} finally {
				Workbook.close();
				Bufferedwriter.close();
				Fileinputstream.close();
				Filewriter.close();
			}//finally
		}
	}//void
}