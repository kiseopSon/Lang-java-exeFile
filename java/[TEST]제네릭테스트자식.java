import java.util.ArrayList;
import java.util.List;

public class 제네릭테스트자식 {
	static int a;
	public static void main(String[] args) {
		List<제네릭테스트부모> 부모 = new ArrayList<제네릭테스트부모>();
		제네릭테스트부모 부 = new 제네릭테스트부모();
		부모.add(0, 부);
		부모.get(0).setNum(20);
		System.out.println(부모.get(0).getStr());
		System.out.println(부모.get(0).getNum());//설정되있지 않은  int의 변수값은 0으로 되어있다.
		
	}
}
