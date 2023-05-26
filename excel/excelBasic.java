package excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//excelupload에만 사용됨.
public class excelbasic {
	private final int maxSize = 1024*1024*100;
	//private int rowLength = 0;//수정중이라 나중에 사용하거나 잊지않기위해, 주석처리.
	private int sheetNum = 0;
	private String sheetName = "";
	private List<String> path = new ArrayList<String>();
	private File file = null;
	
	void Excelbasic(String path, String sheetName, int sheetCnt) throws IOException,Exception {
		this.file = new File(path);
		this.sheetName = sheetName;
		this.sheetNum = sheetCnt;
		
		if(this.maxSize > file.length() && this.file.toString().endsWith(".xlsx")) {
			FileOutputStream Fileoutputstream = null;
			XSSFWorkbook Xssfwork = null;
			
			try {
				Xssfwork = new XSSFWorkbook();
				
				for (int i = 0; i <this.sheetNum; i++) {
					Xssfwork.createSheet(this.sheetName+(i+1));
				}
				
				String oldFileName = this.file.toString();
				if( oldFileName != null && !"".equals(oldFileName)) oldFileName = oldFileName.toLowerCase().replaceAll("/", "").replaceAll("\\\\", "").replaceAll("&", "");
				
				StringWriter StringWriter = new StringWriter();
				StringWriter.append(UUID.randomUUID().toString()).append(".").append(".xlsx");
				
				String url = this.file.getParent().toLowerCase().replace("\\", "/");
				
				File newFilePath = new File(url+File.separator+StringWriter);
				
				Fileoutputstream = new FileOutputStream(newFilePath);
				Xssfwork.write(Fileoutputstream);
				
				this.file = newFilePath;
				this.path.add(url+"/");
				this.path.add(oldFileName);
				this.path.add(""+newFilePath);
				
			} catch (IOException e) {
				System.out.println("IOException - excelbasic.Excelbasic");
			} catch (Exception e) {
				System.out.println("Exception - excelbasic.Excelbasic");
			} finally {
				Xssfwork.close();
				Fileoutputstream.close();
			}
		}
	}//void

	void ExcelReplace(File file){
		String orgFileNm = file.getName();
				
		if(orgFileNm != null && !"".equals(orgFileNm)){
			orgFileNm = orgFileNm.toLowerCase().replaceAll("/", "").replaceAll("\\\\", "").replaceAll("&", "");
		}
		
		StringBuffer sysFileNm = new StringBuffer();
		sysFileNm.append(UUID.randomUUID().toString()).append(".").append("xlsx");
		
		String url = file.getParent().toLowerCase().replace("\\", "/");
		
		LOGGER.error("파일 이름 : "+orgFileNm);
		LOGGER.error("시스템 이름 : "+sysFileNm);
		LOGGER.error("파일 업로드 경로 : "+url);
	}

	void ExcelEndsWithCk(String name){
		String url = "C:\\Users\\miso\\Desktop\\"+ name;
		System.out.println(url.endsWith("txt"));
	}
}
