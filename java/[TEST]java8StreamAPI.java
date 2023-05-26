import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class java8StreamAPI {
	public static void main(String args[]) {

        Stream<Integer> stream5 = Stream.iterate(0, n -> n + 2).limit(5);
        System.out.println("stream5");
        stream5.forEach(System.out::println);


        Stream<String> stream = Stream.of("java","and","stream");//string[]의 split을 다르게 분할하는방법.

        List<String> list1 = stream.collect(Collectors.toList());
		for (String string : list1) {
			System.out.println(string);
		}
		//int
		IntStream intstream = IntStream.range(1, list1.size()+1);//1,4 = 1~3
		Stream<Integer> stream2 = intstream.boxed();
		List<Integer> list2 = stream2.collect(Collectors.toList());
		
		System.out.println(list2);

        List<String> list =
                Arrays.asList("a1", "a2", "b1", "b2", "c2", "c1");
        Stream<String> stream6 = list.stream();
        System.out.println("stream6");
        stream6.forEach(System.out::print);
        System.out.println();
        
        String[] array = new String[]{"a1", "a2", "b1", "b2", "c2", "c1"};
        Stream<String> stream7 = Arrays.stream(array);
        System.out.println("stream7");
        stream7.forEach(System.out::println);
        //System.out.println("test :" +Runtime.getRuntime().availableProcessors());//현재 사용가능한 프로세스 갯수
 
        List<MyString> MyStrings = Arrays.asList(new MyString("google"),new  MyString("apple"),
                new MyString("google"), new MyString("apple"), new MyString("samsung"));
		
		Stream<MyString> stream1 = MyStrings.stream();
		Stream<MyString> stream3 = stream1.distinct();
		stream3.forEach(System.out::println);


         //함수형 인터페이스 + dog interface파일
         dog Stringdog = (name, spec, price) -> dogg.introduce(name, spec, price);
         dog Stringdog2 = dogg::introduce;
         
         System.out.println(Stringdog.convert("밥", "믹스", 3000));
         System.out.println(Stringdog2.convert("스미스", "퓨어", 33000));
    }

}

@FunctionalInterface
interface dog {
	String convert(String name, String spec, int price);
}

class dogg {
	private String name;
	private String spec;
	private int price;
	
	public static String introduce(String name, String spec, int price) {
		return name + " : " + spec + " : " + price;
	}
}


class MyString{
    public String str;
    public MyString(String str) {
        this.str = str;
    }
//이부분 중요.
@Override
public boolean equals(Object obj) {
    if(obj instanceof MyString) {
        return str.equals(((MyString) obj).str);
    }
    return false;
}//equals

@Override
public int hashCode() {
    return str.hashCode();
}//hashcode

@Override
public String toString() {
    // TODO Auto-generated method stub
    return str;
}
}