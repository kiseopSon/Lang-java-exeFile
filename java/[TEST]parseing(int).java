
public class parse인트 {
	public static void main(String[] args) {
		String str = "152.00";
		System.out.println(str);
		float f = Float.parseFloat(str);
		System.out.println(f);
		System.out.println((int)f);
	}
}
