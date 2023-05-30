import testBox.enumTest2.fruit;

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


class enumTest1 {

	public enum fruit{
		Banana("생성자 생성?"),
		apple(2),
		orange('a');
		
		private String color1;
		private int color2;
		private char color3;

		fruit(String string) {
			this.color1 = string;
		}
		fruit(int inter) {
			this.color2 = inter;
		}
		fruit(char chars) {
			this.color3 = chars;
		}
		
		public String getColor1() {
			return color1;
		}
		public int getColor2() {
			return color2;
		}
		public char getColor3() {
			return color3;
		}
		
		public void setColor1(String color1) {
			this.color1 = color1;
		}
		public void setColor2(int color2) {
			this.color2 = color2;
		}
		public void setColor3(char color3) {
			this.color3 = color3;
		}
	}
}

class enumTest2 {

	public enum fruit{
		Banana("생성자 생성?"),
		apple(1),
		orange('a');
		
		private final String color1;
		private final int color2;
		private final char color3;

		fruit(String string) {
			this.color1 = string;
			this.color2 = 0;
			this.color3 = 'a';
		}
		fruit(int inter) {
			this.color1 = "";
			this.color2 = inter;
			this.color3 = 'a';
		}
		fruit(char chars) {
			this.color1 = "";
			this.color2 = 0;
			this.color3 = chars;
		}
		
		public String getColor1() {
			return color1;
		}
		public int getColor2() {
			return color2;
		}
		public char getColor3() {
			return color3;
		}
	}
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
	
	
	//enumTest1
	//enumTest1.fruit f = enumTest1.fruit.Banana;
	//	System.out.println(f); // 출력결과: 생성자 생성?
	//    System.out.println(f.getColor1()); // 출력결과: 생성자 생성?
	//    
	//    f = fruit.apple;
	//    System.out.println(f); // 출력결과: 1
	//    System.out.println(f.getColor2()); // 출력결과: 1
	//    
	//    f = fruit.orange;
	//    System.out.println(f); // 출력결과: a
	//    System.out.println(f.getColor3()); // 출력결과: a
	
	//enumTest2
	enumTest2.fruit f2 = enumTest2.fruit.Banana;
	System.out.println(f2);
	System.out.println(f2.getColor1());
	System.out.println(f2.getColor2());
	System.out.println(f2.getColor3());
	
	char defaultCharTest = '(';
	System.out.println((int)defaultCharTest);
	
	/* 결과
	Banana
	생성자 생성?
	0
	a
	40
	 */
}
}
