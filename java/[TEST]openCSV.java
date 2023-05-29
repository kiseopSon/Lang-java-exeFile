import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//import com.opencsv.CSVWriter; //cw 변수명 모두 주석처리되어 있음.

public class openCSV {
private static ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();//1번
//private static String[][] list;//2번
private static HashMap<String, Object> hmap = new HashMap<String, Object>();
	public static void main(String[] args) {
		//DB조회용 더미 데이터
		long nowtime = System.currentTimeMillis();
		Date date = new Date(nowtime);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = sdf.format(date);
		System.out.println(now);

		for (int i = 0; i < 500000; i++) {//2x50만 1초, 10x100만 8초 + cpuOver, 5x400만 14초(100만, 4초) + cpuOver = 총 용량 : 100만건 -> 7MB, 1000만건 -> 81MB, 2000만건 -> 160MB
			hmap = new HashMap<String, Object>();
			hmap.put("one", i);hmap.put("two", "강남");

			list.add(hmap);
			
		}
	/*	list = new String[1000][1000];
		
		for (int i = 0; i < 1000; i++) {//1000x1000 1초(가장 안전), 1000x1만 7초 + cpuOver, 2000x1만 14초+ cpuOver = 총 용량 : 100만건 -> 11MB, 1000만건 -> 120MB, 2000만건 -> 250MB
			for (int j = 0; j < 1000; j++) {
				list[i][j] = i+"one"+j;
			}
		}*/
		
		try {
			/**
			 * CSVWriter현재 라이브러리 잃어버림, = 오픈소스라서 추가하면 됨.
			 * CSVWriter 파라미터 = writer밖에 안됨.
			 * OutputStreamWriter = outputStream밖에 안되고, 한글인코딩 설정.
			 * FileOutputStream = 파일생성 경로설정.
			 * writeNext = StringTokenizer.nextToken() = Scanner.nextLine()과 동일.
			 * 
			 * 
			 * @author 손기섭
			 * */
			//CSVWriter cw = new CSVWriter(new OutputStreamWriter(new FileOutputStream("c:\\test.csv"), "EUC-KR"));
			
			try {
				for (Map<String, Object> m : list) {//2x1000만 2초, 10x100만 10초 + cpuOver, 5x200만 7초(100만, 4초) + cpuOver = 총 용량 : 80MB
					//cw.writeNext(new String[] {String.valueOf(m.get("one")),
					//										String.valueOf(m.get("two")),
															
					//								});
				}
				
				/*for (String[] m : list) {//1000x1000 1초(가장 안전), 1000x1만 7초 + cpuOver, 2000x1만 14초+ cpuOver = 총 용량 : 100만건 -> 11MB, 1000만건 -> 120MB, 2000만건 -> 250MB
					cw.writeNext((m));
				}*/
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				//cw.close();
				long nowtimes = System.currentTimeMillis();
				Date dates = new Date(nowtimes);
				String after = sdf.format(dates);
				System.out.println(after);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
