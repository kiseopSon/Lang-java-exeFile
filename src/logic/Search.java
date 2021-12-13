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

public class Search{

	public Search() {
		Frame frame = new Frame("검색");
		frame.setBounds(700, 200, 430, 430);
		frame.setLayout(null);
		frame.setBackground(Color.LIGHT_GRAY);

		Label lb0 = new Label("only Number");
		lb0.setBounds(170, 140, 140, 20);

		TextField ta0 = new TextField();
		ta0.setBounds(110, 260, 125, 20);
		
		Button btn0 = new Button("Search");
		btn0.setBounds(240,260,70,20);

		frame.add(lb0);
		frame.add(ta0);
		frame.add(btn0);

		// 화면출력
		frame.setResizable(false);
		frame.setVisible(true);

		//입력창으로 이동
		btn0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new SearchList();
			}
		});
		
		ta0.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// 엔터키 입력시, 작동
				frame.dispose();
				new SearchList();
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