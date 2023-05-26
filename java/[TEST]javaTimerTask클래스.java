import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Javaschedule {
	public static void main(String[] args) {
		Timer timer1 = new Timer();
		TimerTask timetask = new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				long nowtime = System.currentTimeMillis();
				Date date = new Date(nowtime);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String now = sdf.format(date);
				
				System.out.println("타이머 테스트 : " + now);
			}
		};
		
		TimerTask timetask2 = new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				long nowtime = System.currentTimeMillis();
				Date date = new Date(nowtime);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String now = sdf.format(date);
				
				System.out.println("타이머 테스트 : " + now);
			}
		};
		timer1.schedule(timetask, 1000,3000);//계속
		//timer1.schedule(timetask2, 1000); 단일
	}
}
