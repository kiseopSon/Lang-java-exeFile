import java.util.ArrayList;
import java.util.List;

public class 문자열자르기 {
	public static void main(String[] args) {
		//split은 .을 구분하지 못한다.
		String result = "12..33";
		
		String[] va = result.split("\\..");
		for (String string : va) {
			System.out.println(string);
		}
//		List<Integer> colBox = new ArrayList<Integer>();
//		for (int i = 0; i < result.length(); i++) {
//			if(result.charAt(i) == 46) colBox.add(i);
//		}
//		
//		System.out.println(colBox);
//		String[] exe = new String[3];
//		for (int i = 0; i < colBox.size(); i++) {
//			if(i == 0) exe[i] = result.substring(i,colBox.get(i));
//			else exe[i] = result.substring(colBox.get(i-1)+1, colBox.get(i));
//		}
//		exe[2] = result.substring(colBox.get(colBox.size()-1)+1, result.length());
//		
//		for (String string : exe) {
//			System.out.println(string);
//		}
	}
}
