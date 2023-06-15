import java.util.StringTokenizer;

class box{
	String item;
	box next;
	void store(String item) {this.item = item;}
	String pullOut() { return item; }
}
public class StringTokenizer {
	public static int sumInt(String strInt) {
		StringTokenizer st = new StringTokenizer(strInt," @");
		int sum = 0;
		while(st.hasMoreTokens()) {
			sum += Integer.parseInt(st.nextToken());
		}
		return sum;
	}
	
	public static void main(String[] args)  {
		int[] arr = new int[] {0,1,2,3,4};
		for (int i = 1; i <=arr.length-1; i++) {
			int maxium = 0;
			for (int j = 0; j <= i; j++) {
				if(arr[j]>arr[maxium]) {
					maxium= j;
				}
				int temp = arr[maxium];
				arr[maxium] = arr[i];
				arr[i] = temp;
			}
		}
		for(int e : arr) {System.out.println(e + " ");}
		//
		String strInput = "34 @ 1 @100@ 5";
		System.out.println("sumint : " + strInput +" = " + sumInt(strInput));
		
		int[] arr2 = {10,20,30,40,50};
		int[] arr3 = new int[6];
		for (int i = 0; i <=4; i++) {
			arr3[i] = arr2[i];
		}
		for (int i = 5; i >=2; i--) {
			arr3[i] = arr3[i-1];
			arr3[1] = 15;
			arr2 = arr3;
		}
		for(int e : arr2)System.out.println(e+ " ");
		
		box boxhead = new box();
		boxhead.store("one");
		boxhead.next = new box();
		boxhead.next.store("two");
		boxhead.next.next = new box();
		boxhead.next.next.store("three");

		System.out.println();
		box temp = boxhead.next;
		boxhead.next = new box();
		boxhead.next.store("TT");
		boxhead.next.next = temp;

		box p = boxhead;
		while(p != null) {
			System.out.println(p.pullOut());
			p = p.next;
		}
	}
}
