package logic;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SearchList{

	public SearchList() {
		Frame frame = new Frame("검색 결과");
		frame.setBounds(700, 200, 430, 430);
		frame.setLayout(null);
		frame.setBackground(Color.LIGHT_GRAY);

		Label lb0 = new Label("Result : ");
		lb0.setBounds(185, 140, 140, 20);
		
		Label lb1 = new Label("~~~~~~~~~~~~~~~~~~~~");
		lb1.setBounds(135, 190, 140, 20);

		Button btn0 = new Button("ReSearch");
		btn0.setBounds(90, 260, 80, 20);
		
		Button btn1 = new Button("Exit");
		btn1.setBounds(180,260,55,20);
		
		Button btn2 = new Button("First Page");
		btn2.setBounds(245,260,90,20);

		frame.add(lb0);
		frame.add(lb1);
		frame.add(btn0);
		frame.add(btn1);
		frame.add(btn2);

		// 화면출력
		frame.setResizable(false);
		frame.setVisible(true);

		//입력창으로 이동
		btn0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Search();
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
                new Main();
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