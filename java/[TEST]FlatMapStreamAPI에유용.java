import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Flat맵 {
	static List<String> lists = Arrays.asList("a1","a2","c1","c2");
	static Stream<String> stream1 = lists.stream();
	static Stream<String> stream2 = lists.stream();
	static Stream<String> filter1 = stream1.filter(s -> s.startsWith("c"));
	private static String[][] arrays = new String[][] {{"a1","a2"},{"c1","c2"}};
	private static Stream<String[]> stream3;
	private static Stream<String> stream4;
	public static void main(String[] args) {
		filter1.forEach(System.out::println);
		stream3 = Arrays.stream(arrays);
		stream4 = stream3.flatMap(s -> Arrays.stream(s));
		System.out.println("----------------------------");
		stream2.map(String::toUpperCase).forEach(System.out::println);
		System.out.println("----------스트림에 다시담아서 플랫으로 줄여버리기------------------");
		stream4.map(String::toUpperCase).forEach(System.out::println);
	}
}
