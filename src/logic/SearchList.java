package logic;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class SearchList{
	
	private String name;
	
	public SearchList(String name , String type) {
		this.name = name;
		
		Frame frame = new Frame("검색 결과");
		frame.setBounds(700, 200, 430, 360);
		frame.setLayout(null);
		frame.setBackground(Color.LIGHT_GRAY);
		Font font = new Font("맑은 고딕", Font.PLAIN, 28);
		Font font2 = new Font("굴림체", Font.PLAIN, 21);
		Font font3 = new Font("맑은 고딕", Font.PLAIN, 20);
		
		Label lb0 = new Label("Result : ");
		lb0.setBounds(170, 110, 150, 45);
		lb0.setFont(font2);
		
		Label lb1 = new Label(this.name);
		lb1.setBounds(135, 185, 140, 30);
		lb1.setFont(font);
		
		Button btn0 = new Button("Back");
		btn0.setBounds(85, 260, 80, 30);
		btn0.setFont(font3);
		
		Button btn1 = new Button("Exit");
		btn1.setBounds(175,260,60,30);
		btn1.setFont(font3);
		
		Button btn2 = new Button("First Page");
		btn2.setBounds(245,260,110,30);
		btn2.setFont(font3);
		
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
				if(type.equals("java")) new javaSearch();
				else if(type.equals("javascript")) new javaScriptSearch();
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
	}
}