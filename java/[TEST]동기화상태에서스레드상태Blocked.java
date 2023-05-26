import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class 동기화블럭 {
	//스레드 블럭으로 스레드의 세이프를 지켜주는데 대신 블럭이 끝날때까지 대기해야한다.
	//ExecutorService : 스레드 추적용 클래스
//	static long num = 0;
	private long num = 0;
	private Object lock = new Object();
	public void increase() {
		long temp = 0;
		
		synchronized (lock) {
			num++;
			temp = num;
		}
		System.out.println(temp);
	}
	public synchronized void decrease() {
		num--;
	}
	
	public static void main(String[] args) {
//		ExecutorService service = Executors.newCachedThreadPool();
//		
//		for (int i = 0; i < 1000; i++) {
//			service.submit(() -> {
//				synchronized(동기화블럭.class) {
//				num++;
//				System.out.println(num);
//				}
//			});
//		}
		
		//표시가 되긴함.
		동기화블럭 동기화 = new 동기화블럭();
		동기화.increase();
		//동기화.decrease();
	}
}
