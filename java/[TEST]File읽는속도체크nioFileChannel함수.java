import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class nioFileChannel {
	public static void main(String[] args) {
		String path = "C:/Users/miso/Desktop/origin.xlsx";
		
		//FileReader -> BufferedReader
		File file = new File(path);
		
		System.out.println("파일 존재여부 : " + file.exists());
		System.out.println("존재하는 파일의 이름 : " + file.getName());
		
		FileReader fr = null;
		long startTime1 = System.nanoTime();
		long endTime1 = 0;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			br.close();
			fr.close();
			endTime1 = System.nanoTime();
		} catch (IOException e) {}
		long checkTime1 = endTime1 - startTime1;
		System.out.println("FileReader 읽는 시간 : " + checkTime1/1000000.0 + "(m/s)");
		
		//FileInputStream -> BufferedInputStream
		File file2 = new File(path);
		FileInputStream fi = null;
		long startTime2 = System.nanoTime();
		long endTime2 = 0;
		try {
			fi = new FileInputStream(file2);
			BufferedInputStream bi = new BufferedInputStream(fi);
			bi.close();
			fi.close();
			endTime2 = System.nanoTime();
		} catch (IOException e) {}
		long checkTime2 = endTime2 - startTime2;
		System.out.println("FileInputStream 읽는 시간 : " + checkTime2/1000000.0 + "(m/s)");
		
		//FileChannel
		Path path2 = Paths.get("C:/Users/miso/Desktop/origin.xlsx");
		long startTime3 = System.nanoTime();
		long endTime3 = 0;
		try {
			FileChannel fc = FileChannel.open(path2, StandardOpenOption.READ);
			
			fc.close();
			endTime3 = System.nanoTime();
		} catch (IOException e) {}
		
		long checkTime3 = endTime3 - startTime3;
		System.out.println("FileChannel 읽는 시간 : " + checkTime3/1000000.0 + "(m/s)");
	}
}
