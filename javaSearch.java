import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class javaSearch {

	public javaSearch() {
		Frame frame = new Frame("자바 정규식");
		frame.setBounds(700, 200, 420, 420);
		frame.setLayout(null);
		frame.setBackground(Color.LIGHT_GRAY);

		Button btn0 = new Button("날짜 (YYYY:mm:dd)");
		btn0.setBounds(140, 80, 140, 40);

		Button btn1 = new Button("숫자만");
		btn1.setBounds(140, 140, 140, 40);

		Button btn2 = new Button("한글만");
		btn2.setBounds(140, 200, 140, 40);

		Button btn3 = new Button("영어만 (소문자)");
		btn3.setBounds(140, 260, 140, 40);

		Button btn4 = new Button("이메일 (naver, google)");
		btn4.setBounds(140, 320, 140, 40);

		frame.add(btn0);
		frame.add(btn1);
		frame.add(btn2);
		frame.add(btn3);
		frame.add(btn4);

		// 화면출력
		frame.setResizable(false);
		frame.setVisible(true);

		//입력창으로 이동
		btn0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                frame.dispose();
			}
		});

		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                frame.dispose();
			}
		});

		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                frame.dispose();
			}
		});

		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                frame.dispose();
			}
		});

		btn4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                frame.dispose();
			}
		});

		// 종료
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.dispose();
			}
		});
	}// 생성자
}