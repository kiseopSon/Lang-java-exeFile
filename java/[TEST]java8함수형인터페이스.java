import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.stream.Collectors.maxBy;

import java.util.Map;

import static java.util.Comparator.comparing;

public class 함수형인터페이스 {
	static class Fruit{
		public String id;
		public String name;
		public Fruit(String id, String name) {
			this.id = id;
			this.name = name;
		}
		
		public String getId() {
			return id;
		}
		public String getName() {
			return name;
		}
	}
	public static void main(String[] args) {
		Stream<String> fruit = Stream.of("banana", "apple", "mango", "kiwi", "peach", "cherry", "lemon");
//		Set<String> HASHset = fruit.collect(Collectors.toSet());
//		for(String s : HASHset) {
//			System.out.println(s);
//		}
//		String result = fruit.collect(Collectors.joining(", "));
//		System.out.println(result);
		
		Function<String, Integer> getCount = fruits-> fruits.length();
		System.out.println(fruit + " , " + getCount);
		
		//Optional<String> result = fruit.map(Object::toString).collect(maxBy(comparing(getCount)));
		//System.out.println("result: " + result.orElse("no item"));
		//Collectors.averagingInt(v ->v*2)
		Stream<Fruit> fruits2 = Stream.of(new Fruit("1", "banana"), new Fruit("2", "apple"),
		        new Fruit("3", "mango"), new Fruit("4", "kiwi"),
		        new Fruit("5", "peach"), new Fruit("6", "cherry"),
		        new Fruit("7", "lemon"));

		Map<String, String> map = fruits2.collect(Collectors.toMap(Fruit::getId, Fruit::getName));
		for(String key : map.keySet()) {
			System.out.println("key0 : " + key + " , value0 : " + map.get(key) );
		}
		//중복 예외발생 방지 먼저 입력된 값이 우선순위
		//중복 mergeSort
		//(existingValue, newValue) -> {String concat = existingValue + ", " + newValue;return concat;}));
		Map<String, String> mapexception = fruits2.collect(Collectors.toMap(item -> item.getId(), item -> item.getName(),(existringValue, newValue) -> existringValue));//오류남
		for (String key : mapexception.keySet()) {
		    System.out.println("key1: " + key + ", value1: " + map.get(key));
		}
	}
}
