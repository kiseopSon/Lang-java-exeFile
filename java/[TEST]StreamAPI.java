import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamAPI {
    public static void main(String[] args) {
        collectionTest coll = new collectionTest();
        coll.test();
    }
}

class collectionTest{
    public void test(){
        // ArrayList list = new ArrayList<>();
        // list.add("다이너마이트");
        // list.add("붐1");
        // list.add("붐2");
        // list.add("붐3");
        // list.add("붐4");
        // list.add("붐5");
        // Stream str = list.stream();
        // String[] arr = new String[]{"하나1","하나2","하나3","하나4"};
        // String[] arrclear = arr.clone();
        // System.out.println("arr 힙 주소 : " + arr.hashCode());
        // System.out.println("arrClear 힙 주소 : " + arrclear.hashCode());
        // Stream str = Arrays.stream(arrclear);
        // str.forEach(System.out::println);

        // Stream stream2 = Stream.of(4.2, 2.5, 3.2, 1.9);//정적 데이터
        // stream2.forEach(System.out::println);
        
        // IntStream ints = IntStream.range(1, 4).parallel();//3까지
        // ints.forEach(System.out::println);
        // IntStream ints2 = new Random().ints(4);//9 자리에서 11자리 난수, -포함
        // ints2.forEach(System.out::println);
        // IntStream ints3 = IntStream.rangeClosed(1, 4);//4까지
        // ints3.forEach(System.out::println);

        // IntStream iterates = IntStream.iterate(2, n -> n+2); //람다식
        // iterates.forEach(System.out::println);

        Stream emptys = Stream.empty();//빈 스트림 생성 = new 인스턴스 생성하는것과 비스무리한 효과
        System.out.println(emptys.count());

        IntStream intstreamRandom = new Random().ints(4);
        intstreamRandom.forEach(System.out::println);

        IntStream intstreamfilter = IntStream.of(7,65,5,4,5,6,77,4,2,2,4,4,2);
        intstreamfilter.filter(n -> n % 2 != 0).forEach(System.out::println);
    }
}