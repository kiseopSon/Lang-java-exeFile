import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class replace함수테스트 {
	public static void main(String[] args) {
		String s = "[\"123\",\"123\"]";
		String s2 = s.replace("[", "{").replace("]", "}");
		System.out.println(s);
	}
}
