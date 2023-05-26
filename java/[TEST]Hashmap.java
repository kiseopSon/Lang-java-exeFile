import java.util.HashMap;

public class mapTest {
	public static void main(String[] args) {
		HashMap<String, Object> empty = new HashMap<>();
		System.out.println(empty.isEmpty());
		empty.put("test", "aa");
		System.out.println(empty.isEmpty());
		System.out.println(empty.get("test").toString().equals("aa") == false);
		System.out.println("before : "+ empty.get("test"));
		empty.replace("test", "bb");
		System.out.println("after : " + empty.get("test"));
		
		System.loadLibrary("meCab");
	}
}
