import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class automaticArray {
	public static void main(String[] args) {
		AtomicIntegerArray array = new AtomicIntegerArray(5);
		System.out.println("i == 3 : " + array.getAndIncrement(2));//위치
		System.out.println("i == 3 : " + array.getAndAdd(3, 3));//전위치, 값변경ㄷ
		System.out.println("i == 3 : " + array.addAndGet(1, 3));//후위치, 값변경ㄷ
		System.out.println(array);
		
		System.out.println("success ? : " + array.compareAndSet(3, 3, 2));//전위치, 전값, true == 변경, false == 유지
		System.out.println(array);


		int check = 2;
		AtomicReference<Integer> atomic = new AtomicReference<>(10);
		System.out.println("success1 ? :"+ atomic.compareAndSet(check, 100));
		System.out.println("value1 : " + atomic.get());
		//System.out.println("before value : " + atomic.getAndSet(20));
		
		atomic.set(2);
		System.out.println("success2 ? : " + atomic.compareAndSet(check, 100));
		System.out.println("value2 : " + atomic.get());
	}
}
