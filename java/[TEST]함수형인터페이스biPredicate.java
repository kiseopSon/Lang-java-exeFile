import java.util.function.BiPredicate;

public class biprodicate {
	public static void main(String[] args) {
		//2개의 인자를 받아 boolean비교해주는 java8 - BiPredicate
		BiPredicate<Integer, Integer> biPredicate = (n1,n2) -> n1 > n2;
		BiPredicate<Integer, Integer> biPredicate2 = (n1,n2) -> n1 * n2 > 100;
		boolean result = biPredicate.test(10, 100);
		boolean result2 = biPredicate2.and(biPredicate).test(11, 10);
		boolean result3 = biPredicate2.or(biPredicate).test(10, 9);
		boolean result4 = biPredicate2.or(biPredicate).test(9, 10);
		
		BiPredicate<Integer, Integer> biPredicate3 = biPredicate.negate();
		boolean result5 = biPredicate.test(10, 9);//result6을 비교하기위한 더미
		boolean result6 = biPredicate3.test(10, 9);
		
		System.out.println(result);//false = 비교
		
		System.out.println(result2);//true = and연산
		
		System.out.println(result3);// =true or연산
		System.out.println(result4);// =false or연산

		System.out.println(result5);// =true not연산
		System.out.println(result6);// =false not연산
		
		
		
		
		
	}
}
