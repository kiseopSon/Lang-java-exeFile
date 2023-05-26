
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.SAXHelper;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.aspose.cells.SaveFormat;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jdk.jfr.events.FileWriteEvent;

public class excelFunction {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(excelFunction.class);
	
	private final int maxSize = 1024*1024*100;
	private int rowLength;
	private int sheetNum;
	private String pdfFilePath;
	private String sheetName;
	private List<String> path = new ArrayList<String>();
	
	private XSSFWorkbook xssfWork;
	private File file;
	
	/**
	 * @param path : 파일경로 지정, sheetName : 시트이름 지정, sheetNumMax : 시트번호 최대치.
	 * @throws 
	 * @return 
	 * @see 
	 * @Warnings 
	 *  excelTranforToCSVorTXT 테스트용 생성자. 나중에 readExcelPath 상속받게되면 지우면됨.
	 */
	
	public excelFunction() {};
	
	/**
	 * 
	 * @param path : 파일경로 지정, sheetName : 시트이름 지정 : 안써도 되지만 필수값임, sheetNumMax : 시트번호 최대치 고정.
	 * @throws 
	 * @return 
	 * @see 
	 * @Warnings 
	 * 필수로 선언되어야 작동함.
	 */
	public excelFunction(String path, String sheetName, int sheetNumMax) {
		this.file = new File(path);
		this.sheetName = sheetName;
		this.sheetNum = sheetNumMax;
		excel100MBFileGenerate();
	}
	
	/**
	 * @param SheetName : 시트명 지정,SheetCnt : 시트 총갯수 지정
	 * @throws IOException,NullPointerException,Exception
	 * @return "S" or "F"
	 * 
	 * @see 
	 * ※필수 라이브러리※
	 * poi3.16,poi-ooxml 3.16,ooxml-schemas1.4
	 * 
	 * @Warnings 
	 */
	private String excel100MBFileGenerate() {
		File file = this.file;
		String SheetName = this.sheetName;
		int sheetCnt = this.sheetNum;
		
		if(this.maxSize > file.length() || file.toString().endsWith(".xlsx")) {
			FileOutputStream fileOut = null;
			XSSFWorkbook xssfWorkbook = null;

			try {
				xssfWorkbook = new XSSFWorkbook();
				
				for (int i = 0; i < sheetCnt; i++) {
					xssfWorkbook.createSheet(SheetName+(i+1));					
				}
				
				String orgFileNm = file.getName();
				
				if(orgFileNm != null && !"".equals(orgFileNm)) orgFileNm = orgFileNm.toLowerCase().replaceAll("/", "").replaceAll("\\\\", "").replaceAll("&", "");
				
				StringBuffer sysFileNm = new StringBuffer();
				sysFileNm.append(UUID.randomUUID().toString()).append(".").append("xlsx");
				
				String url = file.getParent().toLowerCase().replace("\\", "/");
				
//				LOGGER.error("파일 이름 : "+orgFileNm);
//				LOGGER.error("시스템 이름 : "+sysFileNm);
//				LOGGER.error("파일 업로드 경로 : "+url);
				
				File newFile = new File(url+file.separator+sysFileNm);
				
				fileOut = new FileOutputStream(newFile);
				xssfWorkbook.write(fileOut);
				xssfWorkbook.close();
				fileOut.close();
				
				this.file = newFile;
				this.path.add(url);
				this.path.add(orgFileNm);
				this.path.add(""+sysFileNm);
				return "S";
				
			}  catch (IOException e) {
				LOGGER.error("excel100MBFileGenerate IOException 에러 : "+e.getMessage());
				return "F";
				
			} catch (NullPointerException e) {
				LOGGER.error("excel100MBFileGenerate NullPointerException 에러 : "+e.getMessage());
				return "F";
				
			} catch (Exception e) {
				LOGGER.error("excel100MBFileGenerate Exception 에러 : "+e.getMessage());
				return "F";
			}
		}
		else return "F";
	}
	
	/**
	 * 설명 추가 예정.
	 * @param Filepath : fileinputData 입력.
	 * @throws FileNotFoundException,IOException,Exception
	 * @return "S" or "F"
	 * 
	 * @see 
	 * ※필수 라이브러리※
	 * poi3.16,poi-ooxml 3.16,ooxml-schemas1.4
	 * 
	 * @Warnings 
	 * 
	 * @SuppressWarnings("resource") 필터의 return경고문을 무시한다.
	 */
	@SuppressWarnings({ "resource", "deprecation" })
	public String excel100MBFileRead(String filePath) {
		File file = new File(filePath);
		
		if(this.maxSize > file.length() || file.toString().endsWith(".xlsx")) {
			FileInputStream fileInput = null;
			FileOutputStream fileOutput = null;
			XSSFWorkbook xssfWorkbook = null;
			XSSFSheet xssfsheet = null;
			Row row = null;
			Cell cell = null;
			
			int rowMax = 0;
			int sheetCnt = 0;
			try {
				fileInput = new FileInputStream(file);
				xssfWorkbook = new XSSFWorkbook(fileInput);
				sheetCnt = xssfWorkbook.getNumberOfSheets();
				
				for (int i = 0; i <sheetCnt; i++) {
					xssfsheet = xssfWorkbook.getSheetAt(i);
					rowMax = xssfsheet.getPhysicalNumberOfRows();
					row = xssfsheet.createRow(rowMax);
					
					Iterator<Row> rowIterator = xssfsheet.iterator();
					
					//loop
					while(rowIterator.hasNext()) {
						row = rowIterator.next();
						Iterator<Cell> cellIterator = row.cellIterator();
						while(cellIterator.hasNext()) {
							cell = cellIterator.next();
							switch(cell.getCellType()) {
								case Cell.CELL_TYPE_STRING:
									cell.setCellValue(cell.getStringCellValue());
									break;
								case Cell.CELL_TYPE_BLANK:
									cell.setCellValue("");
									break;
								case Cell.CELL_TYPE_FORMULA:
									cell.setCellValue(cell.getCellFormula());
									break;
							}//switch
						}//while2
					}//while1
				}//for
				
				if(sheetCnt == 0) return "F";
				
				fileInput.close();
				
				fileOutput = new FileOutputStream(this.file);
				xssfWorkbook.write(fileOutput);
				xssfWorkbook.close();
				fileOutput.close();
				
				return "S";
				
			} catch (FileNotFoundException e) {
				LOGGER.error("excel100MBFileRead FileNotFoundException 에러 : "+e.getMessage());
				return "F";
				
			} catch (IOException e) {
				LOGGER.error("excel100MBFileRead IOException 에러 : "+e.getMessage());
				return "F";
				
			} catch (NullPointerException e) {
				LOGGER.error("excel100MBFileRead NullPointerException 에러 : "+e.getMessage());
				return "F";
				
			} catch (Exception e) {
				LOGGER.error("excel100MBFileRead Exception 에러 : "+e.getMessage());
				return "F";
			}
		}//if
		else return "F";
	}
	
	/**
	 * @param List : 리스트 맵으로 넘어옴,sheetNum : 시트 지정. 1부터,listNum : 0부터.
	 * @throws FileNotFoundException,IOException,Exception
	 * @return "S" or "F"
	 * 
	 * @see 
	 * ※필수 라이브러리※
	 * poi3.16,poi-ooxml 3.16,ooxml-schemas1.4
	 * 
	 * @Warnings 
	 * 텍스트형식만을 기입하니, 다른 형식을 넣을 수 없다.
	 * 파라미터에 0값은 사용되지 않고, 엑셀기준으로 1번부터 시작한다.
	 * 해당 메서드에선 getLastRowNum()을 주를 이뤄 사용하는데, 아무것도없는 상태에서 1번부터 시작한다.
	 * getPhysicalNumberOfRows()사용하면 0번부터 추가에는 용이하나, 데이터가 중간에 삭제된 이후 추가가 되지 않는다.
	 * 단, 행에 1개의 열이라도 존재한다면 인식한다.
	 * fileOut은 나중에 AES-256으로 이름 수정할수잇음. 확장자는 변함없음.
	 * 
	 * @SuppressWarnings("resource") 필터의 return경고문을 무시한다.
	 */
	@SuppressWarnings("resource")
	public List<String> excel100MBFileUpload(List<Map<String, Object>> List, int sheetNum, int listNum) {
		File file = this.file;
		
		if(this.maxSize > file.length() || file.toString().endsWith(".xlsx")) {
			FileInputStream fileInput = null;
			FileOutputStream fileOut = null;
			XSSFWorkbook xssfWorkbook = null;
			XSSFSheet xssfsheet = null;
			Row row = null;
			Cell cell = null;
			
			int rowMax = 0;
			int sheetCnt = 0;
			int listCnt = 0;
			
			try {
				fileInput = new FileInputStream(file);
				xssfWorkbook = new XSSFWorkbook(fileInput);
				
				sheetCnt = xssfWorkbook.getNumberOfSheets();//시트 총갯수
				listCnt = List.size();//데이터 갯수

				if(listCnt != sheetCnt) {
					
					for (int i = sheetCnt; i <listCnt; i++) {
						xssfWorkbook.createSheet("Sheet"+(i+1));
					}
					sheetCnt = xssfWorkbook.getNumberOfSheets();//시트 총갯수
				}
				
				if(sheetCnt < sheetNum || sheetNum == 0 || listCnt < listNum) return null;
				
				xssfsheet = xssfWorkbook.getSheetAt(sheetNum-1); //2번째 있는 시트 fix
				rowMax = xssfsheet.getPhysicalNumberOfRows();
				row = xssfsheet.createRow(rowMax);// 그다음 줄에 새롭게 행추가.
				
				int cellCnt = 0;
				for (Entry<String, Object> elem : List.get(listNum).entrySet()) { 
					cell = row.createCell(cellCnt);
					cell.setCellValue(elem.getKey());
					cellCnt++;
				}
				
				cellCnt = 0;
				row = xssfsheet.createRow(rowMax+1);
				
				for (Entry<String, Object> elem : List.get(listNum).entrySet()) { 
					cell = row.createCell(cellCnt);
					System.out.println(cell);
					String value = (String) elem.getValue();
					System.out.println(value);
					cell.setCellValue(value);
					cellCnt++;
				}
			
				fileInput.close();
				
				fileOut = new FileOutputStream(file);
				xssfWorkbook.write(fileOut);
				xssfWorkbook.close();
				fileOut.close();
				
				return this.path;	
				
			} catch (FileNotFoundException e) {
				LOGGER.error("FileNotFoundException 에러 : "+e.getMessage());
				return null;
				
			} catch (IOException e) {
				LOGGER.error("IOException 에러 : "+e.getMessage());
				return null;
				
			} catch (NullPointerException e) {
				LOGGER.error("NullPointerException 에러 : "+e.getMessage());
				return null;
				
			} catch (Exception e) {
				LOGGER.error("Exception 에러 : "+e.getMessage());
				return null;				
			}
		}
		else return null;
	}
	
	/**
	 * @param sheetNum : 시트 지정. 1부터,List : 리스트 맵으로 넘어옴,listNum : 0부터.
	 * @throws FileNotFoundException,IOException,Exception
	 * @return "S" or "F"
	 * 
	 * @see 
	 * ※필수 라이브러리※
	 * poi3.16,poi-ooxml 3.16,ooxml-schemas1.4
	 * 
	 * @Warnings 
	 * 텍스트형식만을 기입하니, 다른 형식을 넣을 수 없다.
	 * 파라미터에 0값은 사용되지 않고, 엑셀기준으로 1번부터 시작한다.
	 * 해당 메서드에선 getLastRowNum()을 주를 이뤄 사용하는데, 아무것도없는 상태에서 1번부터 시작한다.
	 * getPhysicalNumberOfRows()사용하면 0번부터 추가에는 용이하나, 데이터가 중간에 삭제된 이후 추가가 되지 않는다.
	 * 단,단 행에 1개의 열이라도 존재한다면 인식한다.
	 * fileOut은 나중에 AES-256으로 이름 수정할수잇음. 확장자는 변함없음.
	 * 
	 * @SuppressWarnings("resource") 필터의 return경고문을 무시한다.
	 */
	@SuppressWarnings("resource")
	public List<String> excel100MBFileUpdate(List<Map<String, Object>> List, int sheetNum, int listNum) {
		File file = this.file;
		
		if(this.maxSize > this.file.length() || file.toString().endsWith(".xlsx")) {
			
			FileInputStream fileInput = null;
			XSSFWorkbook xssfWorkbook = null;
			XSSFSheet xssfsheet = null;
			Row row = null;
			Cell cell = null;
			
			int rowMax = 0;
			
			try {
				fileInput = new FileInputStream(file);
				xssfWorkbook = new XSSFWorkbook(fileInput);
				
				int sheetCnt = xssfWorkbook.getNumberOfSheets();//시트 총갯수
				int listCnt = List.size();//데이터 갯수
				
				if(sheetCnt < sheetNum || sheetNum == 0 || listCnt < listNum) return null;
				
				xssfsheet = xssfWorkbook.getSheetAt(sheetNum-1); //2번째 있는 시트 fix
				rowMax = xssfsheet.getLastRowNum();
				//xssfsheet.getPhysicalNumberOfRows(); //해당 함수를 사용 할 경우 바로 밑에 if주석처리.
				if(!"".equals(row) || row != null) rowMax = xssfsheet.getLastRowNum()+1;
				
				row = xssfsheet.createRow(rowMax);// 그다음 줄에 새롭게 행추가.
				int cellCnt = 0;
				
				for (Entry<String, Object> elem : List.get(listNum).entrySet()) { 
					cell = row.createCell(cellCnt);
					cell.setCellValue(elem.getKey());
					cellCnt++;
				}
				
				cellCnt = 0;
				row = xssfsheet.createRow(rowMax+1);
				
				for (Entry<String, Object> elem : List.get(listNum).entrySet()) { 
					cell = row.createCell(cellCnt);
					String value = (String) elem.getValue();
					cell.setCellValue(value);
					cellCnt++;
				}

				fileInput.close();
				
				FileOutputStream fileOut = new FileOutputStream(file);
				xssfWorkbook.write(fileOut);
				xssfWorkbook.close();
				fileOut.close();
				
				return this.path;
				
			} catch (FileNotFoundException e) {
				LOGGER.error("FileNotFoundException 에러 : "+e.getMessage());
				return null;
				
			} catch (IOException e) {
				LOGGER.error("IOException 에러 : "+e.getMessage());
				return null;
				
			} catch (NullPointerException e) {
				LOGGER.error("NullPointerException 에러 : "+e.getMessage());
				return null;
				
			} catch (Exception e) {
				LOGGER.error("Exception 에러 : "+e.getMessage());
				return null;
			}
		}
		else return null;
	}
	
	/**
	 * @param 
	 * sheetNum : 시트 지정,rowNum : row 위치 지정,
	 * colAll : true/false 로 column전체를 지울건지 지정,columnNum : false일 경우 column 위치 지정.
	 * @throws FileNotFoundException,DocumentException,IOException,Exception
	 * @return "S" or "F"
	 * 
	 * @see 
	 * ※필수 라이브러리※ 
	 * poi3.16,poi-ooxml3.16,ooxml-schemas1.4
	 * 
	 * @Warnings 
	 * 텍스트형식만을 기입하니,다른 형식을 넣을 수 없다.
	 * 파라미터에 0값은 사용되지 않고,엑셀기준으로 1번부터 시작한다.
	 * 
	 * @SuppressWarnings("resource") 필터의 return경고문을 무시한다.
	 */
	@SuppressWarnings("resource")
	public String excelSelectRemove(int sheetNum, int rowNum, boolean colAll, int columnNum) {
		File file = this.file;
		
		FileInputStream fileInput = null;
		XSSFWorkbook xssfWorkbook = null;
		XSSFSheet xssfsheet = null;
		Row row = null;
		Cell cell  = null;
		
		int sheetMax;
		int rowMax;
		int columnMax;
		
		if(sheetNum == 0 || rowNum == 0 || columnNum == 0) return "F";
		
		try {
			fileInput = new FileInputStream(file);
			xssfWorkbook = new XSSFWorkbook(fileInput);
			xssfsheet = xssfWorkbook.getSheetAt(sheetNum-1);
			row = xssfsheet.getRow(rowNum-1);
			
			sheetMax= xssfWorkbook.getNumberOfSheets();//시트 총갯수
			rowMax = xssfsheet.getPhysicalNumberOfRows();//해당 시트의 총행수
			columnMax = xssfsheet.getRow(0).getPhysicalNumberOfCells(); //첫번째있는 행의 총열수
			
			if(sheetMax < sheetNum || rowMax < rowNum || columnMax < columnNum) return "F";
			
			if(colAll) {
				
				for (int i = 0; i < columnMax; i++) {
					cell = row.getCell(i);
					if(cell != null) row.removeCell(cell);
				}
			} else {
				
				cell = row.getCell(columnNum-1);
				if(cell != null || "".equals(cell)) row.removeCell(cell);
			}
			
			fileInput.close();
			
			FileOutputStream fileOut = new FileOutputStream(file);
			xssfWorkbook.write(fileOut);
			xssfWorkbook.close();
			fileOut.close();
			
			return "S";
			
		} catch (FileNotFoundException e) {
			LOGGER.error("FileNotFoundException 에러 : "+e.getMessage());
			return "F";
			
		} catch (IOException e) {
			LOGGER.error("IOException 에러 : "+e.getMessage());
			return "F";
			
		} catch (Exception e) {
			LOGGER.error("Exception 에러 : "+e.getMessage());
			return "F";
		}
	}
	
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
	public String excelTranforToCSVorTXT(String readFilePath, String filePath) {
	
        if(filePath.endsWith(".txt") || filePath.endsWith(".csv")) {
        	FileInputStream file = null;
        	long start =System.currentTimeMillis();
        	
	        try {
	        	OPCPackage opc = OPCPackage.open(new File(readFilePath));
	        	XSSFReader xssfreader = new XSSFReader(opc);
	        	StylesTable styles = xssfreader.getStylesTable();
	        	ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(opc);
	        	InputStream in = xssfreader.getSheetsData().next();
	        	InputSource source = new InputSource(in);
	        	
	        	
	        	XMLReader xmlreader = SAXHelper.newXMLReader();
	        	xmlreader.parse(source);
	        	in.close();
	        	opc.close();
	        	
	        	
	        	BufferedWriter out = new BufferedWriter(new FileWriter(filePath));

	        	out.close();
	        	
	        	
	        	long end = System.currentTimeMillis();
	        	System.out.println("실행 시간 : " + (end -start) / 1000.0 + "초");
                return "S";
	                
	        }   catch (NullPointerException e) {
	        	LOGGER.error("NullPointerException 에러 : "+e.getMessage());
	        	return "F";
	        	
	        }  catch (Exception e) {
	        	LOGGER.error("Exception 에러 : "+e.getMessage());
	        	return "F";
	        }
        }
        else return "F";
	}
	
	/**
	 * @param pdfFilePath : pdf파일로 저장할 경로, rowLength : 기존 엑셀의 행길이기준으로 차트작성.
	 * @throws IOException, DocumentException
	 * @return "S" or "F"
	 * 
	 * @see 
	 * ※필수 라이브러리※ 
	 * commons-collection4 4.1, itextpdf5.3.4, xmlbeans3.0.1, 
	 * poi3.16, poi-ooxml3.16, ooxml-schemas1.4
	 * 
	 * @Warnings 텍스트형식이 아니거나 그림같은것이 함께있는 파일은 에러발생.
	 * 
	 * @SuppressWarnings Cell.CELL_TYPE_FORMULA등등 관련 경고무시.
	 */
	@SuppressWarnings("deprecation")
	public String excelTranforToPDF(String pdfFilePath, int rowLength) {
		this.rowLength = rowLength;
		File file = this.file;
		
		int pdfCheck = pdfFilePath.lastIndexOf('.');
		if(pdfCheck > 0) {
			String name = pdfFilePath.substring(pdfCheck + 1);
			
			if(name.equals("pdf") || name.equals("PDF")) this.pdfFilePath = pdfFilePath;				
			
			else return "F";
		}
		else return "F";

		if(this.maxSize > this.file.length()) {
			
			try {
				FileOutputStream fileOut = new FileOutputStream(new File(this.pdfFilePath));
				FileInputStream fileInput = new FileInputStream(file);
				xssfWork = new XSSFWorkbook(fileInput);
				int sheetCnt = xssfWork.getNumberOfSheets();
				Document 	iText = new Document();
				PdfWriter.getInstance(iText, fileOut);
				iText.open();
				
				for (int i = 0; i < sheetCnt; i++) {
					XSSFSheet workSheet = xssfWork.getSheetAt(i);
					Iterator<Row> rowIterator = workSheet.iterator();
					
					PdfPTable table = new PdfPTable(this.rowLength);
					PdfPCell tableCell;
					
					//loop
					while(rowIterator.hasNext()) {
						Row row = rowIterator.next();
						Iterator<Cell> cellIterator = row.cellIterator();
						while(cellIterator.hasNext()) {
							Cell cell = cellIterator.next();
							switch(cell.getCellType()) {
								case Cell.CELL_TYPE_STRING:
									tableCell = new PdfPCell(new Phrase(cell.getStringCellValue()));
									table.addCell(tableCell);
									break;
								case Cell.CELL_TYPE_BLANK:
									tableCell = new PdfPCell(new Phrase(cell.getStringCellValue()));
									table.addCell(tableCell);
									break;
								case Cell.CELL_TYPE_FORMULA:
									tableCell = new PdfPCell(new Phrase(cell.getStringCellValue()));
									table.addCell(tableCell);
									break;
							}//switch
						}//while2
					}//while1
					iText.add(table);
				}//for
				
				iText.close();
				fileInput.close();
				xssfWork.close();
				fileOut.close();
			
				return "S";
				
			} catch(FileNotFoundException e) {
				LOGGER.error("FileNotFoundException 에러 : " + e.getMessage());
				return "F";
				
			} catch(IOException e) {
				LOGGER.error("IOException 에러 : " + e.getMessage());
				return "F";
				
			}  catch(DocumentException e) {
				LOGGER.error("DocumentException 에러 : " + e.getMessage());
				return "F";
				
			} catch(Exception e) {
				LOGGER.error("Exception 에러 : " + e.getMessage());
				return "F";
			}
		}//if
		else return "F";
	}
}