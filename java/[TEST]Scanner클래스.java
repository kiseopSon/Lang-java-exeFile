import java.util.Scanner;

public class 반복적인이름생성기 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String a = "";
		a = sc.next();
		for (int i = 41; i < 53; i++) {
			System.out.print(a+i + ", ");
		}
	}
}
