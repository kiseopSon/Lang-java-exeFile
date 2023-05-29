import java.util.function.IntConsumer;

//함수형 프로그래밍
public class FunctionalInterface어노테이션{
    public static void main(String[] args) {

        String password = "123456";
        System.out.println(password.toCharArray());
        //방식1
        StringToDog dog1 = (name, species, price) -> Dog.introduce(name, species, price);
        //방식2
        StringToDog dog2 = Dog::introduce;    
        
        System.out.println(dog1.convert("백구", "순종", 100));
        System.out.println(dog2.convert("누렁이", "잡종", 1000));

    }
}
class Dog{
    // private String name;
    // private String species;
    // private int price;

    public static String introduce(String name, String species, int price){
        return name + " : " + species + " : " + price;
    }
}

@FunctionalInterface
interface StringToDog{
    public String convert(String name, String species, int price);
}

@FunctionalInterface
interface Supplier<T>{//Callable과 같음 단지 쓰이는곳에 따라 이름만 다름
    T get() throws Exception;
}

@FunctionalInterface
interface Function<Integer, String>{
    String apply(Integer t);
}

// @FunctionalInterface
// public interface Runnable{//단순 실행
//     public abstract void run();
// }

// @FunctionalInterface
// public interface Callable<V>{
//     V call() throws Exception;
// }