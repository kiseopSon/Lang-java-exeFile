import java.util.ArrayList;
import java.util.List;

public class outofindex방지방법 {
	public static void main(String[] args) {
		//1. 맨위에서 아래로 삭제를하거나 명령을 수행한다.
		//2. 전부 탐색(for or iterator)후, 삭제명령같을걸 수행한다.
		//3. collection.removeIf는 함수형 인터페이스 predecate를 전달하여 조건에 맞는 아이템을 안전하게 제거한다.
		
		
		//3.
		List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("kiwi");
        list.add("melon");
        list.add("banana");

        list.removeIf(item -> item.equals("kiwi"));

        System.out.println("Result: " + list);
	}
}
