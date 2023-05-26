import java.util.Arrays;

public class 배열복사 {
	static int[] arr = {1,23,4,5};

	
	public static void main(String[] args) {
		int[] copy = new int[arr.length];
		
//		int[] copy = arr.clone();	
		System.arraycopy(arr, 0, copy, 0, copy.length);
		arr.clone();
		
		copy[2] = 10;
		
		System.out.println("arr : " + Arrays.toString(arr));
		System.out.println("copy : " + Arrays.toString(copy));
	}
}
