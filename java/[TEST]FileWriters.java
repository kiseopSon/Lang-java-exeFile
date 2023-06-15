import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

public class FileWriters {
	public static void main(String[] args) {
		String j = null;
		try {
			FileWriter fw = new FileWriter("[TEST]FileWriters.txt",true);
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY MM dd HH:mm:ss");
			j = JOptionPane.showInputDialog("어떤내용을 추가하시겠습니까??\n");
			String timeStamp = sdf.format(System.currentTimeMillis());
			fw.write(j);
			fw.write(" : "+timeStamp+"\n");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
