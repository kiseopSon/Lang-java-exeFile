import java.util.ArrayList;
import java.util.List;

public class 강제OutOfMemory {
	public static void main(String[] args) {
		new ArrayList<>(Integer.MAX_VALUE);
		
//		List<byte[]> list= new ArrayList<>();
//		int index = 1;
//		while(true) {
//			byte[] b = new byte[1048576];
//			list.add(b);
//			Runtime rt = Runtime.getRuntime();
//			System.out.println("free memory : "+ index++ + rt.freeMemory());
//		}
	}
}
