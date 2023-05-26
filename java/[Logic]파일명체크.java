import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class java파일명체크 {
	public static void main(String[] args) {
		String excelDirectory = "D:\\amsRss\\fileupload\\excel";//임시로 절대경로 지정, 원래는 이것 : MultipartServletRequest fileData
		String location = "Clinical";
		File dir = new File(excelDirectory);
		File files[] = dir.listFiles();
		List<String> item = new ArrayList<>();
		for (File file : files) {
			System.out.println("file :" + file);
			if(file.getName().split("-")[1].equals(location)){
				item.add(file.getName());
				file.delete();
			}
		}
		int itemCnt = item.size();
		
		
		System.out.println(item);
		System.out.println(itemCnt);
	}
}
