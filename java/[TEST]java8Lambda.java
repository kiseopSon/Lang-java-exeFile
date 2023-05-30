package java;

import java.util.List;

public class java8Lambda {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		 List<String> list = List.of("Peter", "Thomas", "Edvard", "Gerhard");

		    // print using lambda
		    list.forEach(item -> System.out.println(item));// 파라미터의 사용을 간략화한 기능
		    list.forEach(System.out::println);//기존 람다식의 동일한 메서드를 사용한다면 new로 대체 가능
	}
	
}
