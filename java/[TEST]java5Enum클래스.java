enum 클래스비교2 {
	left("왼쪽"),right("오른쪽");
	private String name;
	private 클래스비교2(String name) {this.name = name;}
	public String get클래스비교2() {return name;}
}

class day{
	public static final day d1 = new day();
	public static final day d2 = new day();
	public static final day d3 = new day();
	public static final day d4 = new day();
}
class month{
	public static final month mon1 = new month();
	public static final month mon2 = new month();
	public static final month mon3 = new month();
	public static final month mon4 = new month();
}
enum D_ay{
	mon,tue,wed,thurs;
}
enum mon_th{
	jan,feb,mar,ap;
}
public class 클래스비교 {
public static void main(String[] args) {
	//Incompatible operand types day and month : 호환되지 않는 피연산자 유형(일 및 월)
//	if(day.d1 == month.mon1) System.out.println("같은 상수"); 
	day d = day.d1;
	//Cannot switch on a value of type day. Only convertible int values, strings or enum variables are permitted
	// : type day 값을 켤 수 없습니다. 변환 가능한 int 값, 문자열 또는 열거형 변수만 허용됩니다.
	
	D_ay day = D_ay.mon;
	switch(day) {
	default:
		System.out.println("default0");
		break;} //가능
		
	클래스비교2 day2 = 클래스비교2.right;
	System.out.println("day2 : "+day2.get클래스비교2());
	switch(day2.get클래스비교2()) {
	case "왼쪽":
			System.out.println("왼(left)쪽");
		break;
	case "오른쪽":
			System.out.println("오른(right)쪽");
		break;
	default:
		System.out.println("default1");
		break;}
}
}
