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
		
		//HashMap 테스트 replace하면 이전값이 먼저 출력되는 이슈가 있음, map이 원래 이럼 먼저빠져나오고 그다음 새로운게 들어감
		HashMap<String, Object>  hash = new HashMap<String, Object>();
		hash.put("test", 1);
		System.out.println("test1  :" + hash.get("test"));
		
		hash.put("test",2);//키를 동일하게 값 새로 추가
		System.out.println("test2  :" + hash.get("test"));
		
		hash.replace("test", 3);//키 동일하게 값 수정
		System.out.println("test3  :" + hash.get("test"));
		
		hash.replace("test", 3, 4);//키 동일하게 값 변경
		System.out.println("test4  :" + hash.get("test"));
		
		System.out.println("test5  :" + hash.put("test", 5));
		System.out.println("test5-2  :" + hash.get("test"));
		//여기서 5가 됬음. -> 결론 put 하기전에 프린트가 먼저 실행됨.
		System.out.println("test6  :" + hash.replace("test", 6));
		System.out.println("test6-2  :" + hash.get("test"));
		//여기서 6가 됬음. -> 결론 put 하기전에 프린트가 먼저 실행됨.
		System.out.println("test7-1  :" + hash.get("test"));
		//프린트가 먼저 실행되는 경우는 값도 추후 바뀌지만, 먼저 return값으로 true/false를 반환해줌, 반환이 성공적으로 이뤄졌는지, oldValue가 맞는지 부터 체크
		System.out.println("test7  :" + hash.replace("test", 6,7));
		System.out.println("test7-2  :" + hash.get("test"));
		
		System.loadLibrary("meCab");
	}
}
