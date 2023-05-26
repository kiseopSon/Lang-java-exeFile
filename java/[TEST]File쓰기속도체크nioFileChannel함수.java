import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

public class writeTime {
	public static void main(String[] args) {
		
		//FileWriter -> BufferedWriter = 스트링타입이라 저장만은되도 제대로 된 내용을 전달하지 못함. = 담기는것까지 2만건 평균 1.1초
		File origin = new File("C:/Users/miso/Desktop/origin.xlsx");//1
		File file = new File("C:/Users/miso/Desktop/test.xlsx");//1
		
		System.out.println("파일 존재여부 : " + origin.exists());
		System.out.println("존재하는 파일의 이름 : " + origin.getName());
		
		FileWriter fw = null;
		long startTime1 = System.nanoTime();
		long endTime1 = 0;
		try {
			FileReader fr = new FileReader(origin);
			BufferedReader br = new BufferedReader(fr);
			fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			
			int data;
			while((data = br.read()) != -1) {
				bw.write(data);
			}
			br.close();
			fr.close();
			bw.close();
			fw.close();
			endTime1 = System.nanoTime();
		} catch (IOException e) {}
		long checkTime1 = endTime1 - startTime1;
		System.out.println("FileWriter 쓰는 시간 : " + checkTime1/1000000.0 + "(m/s)");
		
		//FileOutputStream -> BufferedOutputStream = 담기는것까지 2만건 평균 0.5초
		File file2 = new File("C:/Users/miso/Desktop/test2.xlsx");//2
		
		FileOutputStream fo = null;
		long startTime2 = System.nanoTime();
		long endTime2 = 0;
		try {
			FileInputStream fi = new FileInputStream(origin);
			BufferedInputStream bi = new BufferedInputStream(fi);
			fo = new FileOutputStream(file2);
			BufferedOutputStream bo = new BufferedOutputStream(fo);
			int data2;
			while( (data2 = bi.read()) != -1) {
				bo.write(data2);
			}
			bi.close();
			fi.close();
			bo.close();
			fo.close();
			endTime2 = System.nanoTime();
		} catch (IOException e) {}
		long checkTime2 = endTime2 - startTime2;
		System.out.println("FileOutputStream 쓰는 시간 : " + checkTime2/1000000.0 + "(m/s)");
		
//		//FileChannel = 담기는것까지 2만건 평균 0.016초
//		long startTime3 = System.nanoTime();
//		long endTime3 = 0;
//		Path from = Paths.get(origin.getAbsolutePath());
//		Path to = Paths.get("C:/Users/miso/Desktop/test3.xlsx");
//		try {
//			FileChannel fc_from = FileChannel.open(from, StandardOpenOption.READ);//3
//			FileChannel fc_to = FileChannel.open(to, StandardOpenOption.CREATE_NEW,StandardOpenOption.WRITE);//3
//			
//			ByteBuffer bytebuffer = ByteBuffer.allocate(1000000);//2만건 약 15mb = 1천5백만 바이트 -> 1백만 단위로 자름.
//			int byteCnt;
//			
//			while(true) {
//				bytebuffer.clear();
//				byteCnt = fc_from.read(bytebuffer);
//				if(byteCnt == -1) break;
//				bytebuffer.flip();
//				fc_to.write(bytebuffer);
//			}
//			
//			fc_from.close();
//			fc_to.close();
//			endTime3 = System.nanoTime();
//		} catch (IOException e) {}
//		long checkTime3 = endTime3 - startTime3;
//		System.out.println("FileChannel 쓰는 시간 : " + checkTime3/1000000.0 + "(m/s)");
//		
//		//nio를 사용한 단순 카피 담기는것까지 2만건 평균 0.010초
//		Path from2 = Paths.get(origin.getAbsolutePath());
//		Path to2 = Paths.get("C:/Users/miso/Desktop/test4.xlsx");
//		long startTime4 = System.nanoTime();
//		long endTime4 = 0;
//		try {
//			Files.copy(from2, to2, StandardCopyOption.REPLACE_EXISTING);
//			endTime4 = System.nanoTime();
//		} catch (IOException e) {}
//		long checkTime4 = endTime4 - startTime4;
//		System.out.println("Files copy 시간 : " + checkTime4/1000000.0 + "(m/s)");
	}
}
