package logic;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class javaSearch {
	
	private final String name = "java";

	public javaSearch() {
		Frame frame = new Frame("자바 정규식");
		frame.setBounds(700, 200, 430, 430);
		frame.setLayout(null);
		frame.setBackground(Color.LIGHT_GRAY);
		Font font = new Font("맑은 고딕", Font.PLAIN, 12);

		Button btn0 = new Button("Date (YYYY.mm.dd)");
		btn0.setBounds(140, 80, 155, 40);
		btn0.setFont(font);

		Button btn1 = new Button("only Number");
		btn1.setBounds(140, 140, 155, 40);
		btn1.setFont(font);

		Button btn2 = new Button("only Korean");
		btn2.setBounds(140, 200, 155, 40);
		btn2.setFont(font);

		Button btn3 = new Button("only English (LowerCase)");
		btn3.setBounds(140, 260, 155, 40);
		btn3.setFont(font);

		Button btn4 = new Button("Email (naver, google)");
		btn4.setBounds(140, 320, 155, 40);
		btn4.setFont(font);

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
                new Search(btn0.getLabel(), name);
			}
		});

		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Search(btn1.getLabel(), name);
			}
		});

		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Search(btn2.getLabel(), name);
			}
		});

		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Search(btn3.getLabel(), name);
			}
		});

		btn4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Search(btn4.getLabel(), name);
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