import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.opencsv.CSVWriter;
//+ select쿼리중에 mybatis를 쓴다면, select속성중에 fetchSize 가 있다. 해당 수치 공식은 다음과 같고, y값을 대입하면 된다.
//+ 약 2000만개를 기준, 2000 = 테이블 필드갯수 * x => (x를 구하고) => x* (3/2) = y
// rowCacheSize : 메모리에 보관할 행 수(기본값은 10)
// bufferSize : InputStream을 파일로 읽을 때 사용할 버퍼 크기(기본값은 1024)
// open : InputStream 또는 XLSX 파일용 파일(필수)
public class openCSVTest {
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
			 * CSVWriter 파라미터 = writer밖에 안됨.
			 * OutputStreamWriter = outputStream밖에 안되고, 한글인코딩 설정.
			 * FileOutputStream = 파일생성 경로설정.
			 * writeNext = StringTokenizer.nextToken() = Scanner.nextLine()과 동일.
			 * 
			 * 
			 * @author 손기섭
			 * */
			CSVWriter cw = new CSVWriter(new OutputStreamWriter(new FileOutputStream("c:\\test.csv"), "EUC-KR"));
			
			try {
				for (Map<String, Object> m : list) {////2x1000만 2초, 10x100만 10초 + cpuOver, 5x200만 7초(100만, 4초) + cpuOver = 총 용량 : 80MB
					cw.writeNext(new String[] {String.valueOf(m.get("one")),
															String.valueOf(m.get("two")),
															
													});
				}
				
				/*for (String[] m : list) {//1000x1000 1초(가장 안전), 1000x1만 7초 + cpuOver, 2000x1만 14초+ cpuOver = 총 용량 : 100만건 -> 11MB, 1000만건 -> 120MB, 2000만건 -> 250MB
					cw.writeNext((m));
				}*/
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				cw.close();
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


/**
 * 회사에서 신청을 11월 11일로 해줬으나, 승인은 16일날 됬으므로, (16-1)일날 끝남
 * 기업 지원금과 취업지원금은 6개월단위가 지나고 월금을 7개월째받고나서 신청이 가능하다,
 * 신청순서는 회사->중소기업벤처기업진흥공단->고용센터 순으로 처리가 완료되어야한다.
 * 기업 지원금은 회당 60만원, 취업지원금은 4회, 5회는 140만원씩이다.
 * 만기일은 2023/11/15이고, 돈빠져나가는건 2023/10/15일날 끝난다.
 * 만기일을 기준으로 하기때문에 중간에 그만두면 중도해지처리가 된다, 반드시 만기일기준이여야한다.
 * 만일 결격사유로서 15일이상 무급으로 일한 상황이 있다면, 15일이상의 만기일이 연장된다.
 * 내채공의 신청후 환급받는대까지 보통 15일 이내로 안내하고 있다.
 * 청내채공은 퇴사해도 만기일 기준이라 받을 수 있다.
 * 내일배움카드는 5년동안 300만원지원이고, 학원에따라 전액지원이 가능할 수있는데, 이건 기회가 1번뿐이다.
 * 현재2023-06-05 재직중인데 발급신청이 가능하고, 퇴사해도 월 300만원이 넘지 않아서 신청가능 하다.
 * 청년성공패키지는 없어지고 국민취업지원제도로 바뀌었다, 2019년 청년성공패키지가 끝나고, 조기취업한곳에서도 퇴사를해서 대상이 됬다.
 * 그런데 국비지원학원을 다닐 수 있는지 몰랐다.
 * => 국가사업에서도 주최하는측이 다르면 신청이 가능하다. 회사에서 일반 교육을 들을 수잇는것도 그 이유 때문 = 결국 생돈 날림
 * 취업성공패키지와 내일배움카드는 같은곳에서 주최됬기때문에 동시에 시행할 수 없기에 신청이 안됬던 거다.
 * 어떤 교육을 듣거나 들을 계획이 있다면 주최측에 전화해서 내일배움카드와 같은 주최인지 확인해야 불미스러운일을 피할 수있다.
 * 모르고 신청했다가 나중에 불이익 겪는건 본인 책임이라고 한다. = 그럴려면 철저히 해야함.
 * 청내배카 고객센터 전화번호 1350 -> 음성ARS로 넘어가고 -> 3 -> 2
 * 청내채공 고객센터 전화번호 1350 -> 음성ARS로 넘어가고 -> 3 -> 8
 */