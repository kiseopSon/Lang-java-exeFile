import java.text.DateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class 정규식테스트 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String regex0 = "[^0-9]";									                            								//기록지 상세 - 45.병리검사 시행 시점
		String regex1 = "^[1-2]{1}$";									                            								//기록지 상세 - 45.병리검사 시행 시점
		String regex2 = "^[0-1]{1}$";									                            								//기록지 상세 - 46.다병소성 유무
		String regex3 = "^[0-9]{1}$|^[0-9]{1}[0-9]{1}$";	                            								//기록지 상세 - 47.다병소성 종양의 개수
		String regex4 = "^[1-4]{1}$";  									                           									//기록지 상세 - 48.간암 분화정도 : worst
		String regex5 = "^[1-4]{1}$";  									                            								//기록지 상세 - 49.간암 분화정도 : major
		String regex6 = "^[1-6]{1}$";  									                            								//기록지 상세 - 50.간암 조직학적유형(Histologic type)
		String regex7 = "^[0-1]{1}$";  									                            								//기록지 상세 - 51.간암 절제연 침윤(Resection margin) 유무
		String regex8 = "^[0-1]{1}$";  									                            								//기록지 상세 - 52.간암 혈관 침윤(Bile duct invasion)유무
		String regex9 = "^[0-1]{1}$" ;									                            								//기록지 상세 - 53.간암 혈관 침윤(Microvessel invasion)유무
		String regex10 = "^[0-1]{1}$";									                            								//기록지 상세 - 54.간암 혈관 침윤(Portal vein invasion) 유무
		String regex11 = "^[0-1]{1}$";									                            								//기록지 상세 - 55.간내전이(Intrahepatic metastasis) 유무
		String regex13 = "^[0-1]{1}$";									                            								//기록지 상세 - 56.간암다중심적발생(Multicentric occurrence) 유무
		//free TEXT		   					   							                            										//기록지 상세 - 57.간암 Immunoexpression of tumor
		//free TEXT														                    												//기록지 상세 - 58.AJCC 원발부위
		//free TEXT														                            										//기록지 상세 - 59.AJCC 림프절
		//free TEXT														                            										//기록지 상세 - 60.AJCC 원격전이
		String regex14 = "^[0-4]{1}$";									                            								//기록지 상세 - 61.Other liver pathology-Cirrhosis

		String regex15 = "^[1-9]{1}$";									                            								//치료 - 62.비수술적 치료 : 간동맥치료 방법
		String regex16 = "^.{0,99}$";							                                    								//치료 - 63.비수술적 치료 : 간동맥치료 방법 기타
		String regex17 = "^[0-1]{1}$";							 		                            								//치료 - 64.비수술적 치료 : 간암 항암화학요법 시행여부
		String regex18 = "^[1-4]{1}$|^[9]{1}[9]{1}$";		                            								//치료 - 65.비수술적 치료 : 간암 항암화학요법 방법
		String regex19 = "^.{0,99}$";								                                								//치료 - 66.비수술적 치료, 간암 항암 화학요법 : 약물종류
		String regex20 = "^[0-1]{1}$";									                            								//치료 - 67.동일질환 과거 치료력 : 간암 수술력 유무
		String regex21 = "^[1-3]{1}$";									                            								//치료 - 68.동일질환 과거 치료력 : 간암 수술 종류
		String regex22 = "^[0-1]{1}$";									                            								//치료 - 69.동일질환 과거 치료력 : 비수술적 치료력 유무
		String regex23 = "^[1-4]{1}$";									                            								//치료 - 70.동일질환 과거 치료력 : 비수술적 치료 종류
		String regex24 = "^[0-1]{1}$";									                            								//증상 - 71.증상-간성혼수
		String regex25 = "^[1-4]{1}$";									                            								//증상 - 72.증상-간성혼수 발생시기
		String regex26 = "^[1-4]{1}$";									                            								//증상 - 73.증상-복수 증상 발생시기

		String regex27 = "^[0-1]{1}$";									                            								//추적관찰 - 74.추적관찰-재발여부
		String regex28 = "^([0][1-9]|[12][0-9])\\\\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$";	//추적관찰 - 75.추적관찰-재발발견일
		String regex29 = "^[1-2]{1}$";									                            								//추적관찰 - 76.추적관찰-재발부위
		String regex30 = "^[0-1]{1}$";									                           									//추적관찰 - 77.추적관찰-전이여부
		String regex31 = "^([0][1-9]|[12][0-9])\\\\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$";	//추적관찰 - 78.추적관찰-전이발견일
		//free TEXT																															//추적관찰 - 79.추적관찰-전이부위
		
		String txt = "  14.00";
		txt = txt.trim();
		//String val = txt.replaceAll("/[\\-0-9]+\\.[0-9]+/g", "");
		//System.out.println(val);
		//System.out.println(!val.equals("") ? Float.parseFloat(val) : Float.parseFloat("0"));
		System.out.println(Pattern.matches("^[0-9]*\\.[0-9]{2}$", txt));
		//System.out.println(sc.next().replaceAll(regex0, ""));
		//System.out.println(isDate(sc.next(), "yyyyMMdd"));
	}
	//날짜 정규식 함수 - (date : 실제 데이터)(format : yyyy-MM-dd, yyyy/MM/dd, yyyy.MM.dd, yyyyMMdd 중에 맞는 날짜포맷 선언)
	public static boolean isDate(String date, String format) {
		String pattern = "";
		switch (format) {
			case "yyyy-MM-dd":
				pattern = "^([0][1-9]|[12][0-9])\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
				break;
			case "yyyy/MM/dd":
				pattern = "^([0][1-9]|[12][0-9])\\d{2}/(0[1-9]|1[012])/(0[1-9]|[12][0-9]|3[01])$";
				break;
			case "yyyy.MM.dd":
				pattern = "^([0][1-9]|[12][0-9])\\d{2}.(0[1-9]|1[012]).(0[1-9]|[12][0-9]|3[01])$";
				break;
			case "yyyyMMdd":
				pattern = "^([0][1-9]|[12][0-9])\\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$";
				break;
		}
		return date.matches(pattern);
	}
}
