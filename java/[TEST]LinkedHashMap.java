import java.util.LinkedHashMap;
import java.util.Map;

public class 정렬된해쉬맵 {
	 public static void main(String[] args) {

		Map<String, Integer> map = new LinkedHashMap<>();
		
		for (int i = 0; i < 10; i++) {
			String key = "id_" + i;
			int value = i;
			System.out.println(value);
			map.put(key, value);
		}
		map.forEach((key
				,value) -> {
			System.out.println("key: "+ key + " , value : " + value);
		});
	}
}
