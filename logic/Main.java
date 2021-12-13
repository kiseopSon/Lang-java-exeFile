package logic;

import java.awt.*;
import java.awt.event.*;

class Main{
    public Main(){
        Frame frame = new Frame("정규 표현식 찾기");
        frame.setBounds(700,200,420,300);
        frame.setLayout(null);
        frame.setBackground(Color.LIGHT_GRAY);

        Button btn0 = new Button("1. Java");
        btn0.setBounds(85, 135, 75, 50);
        frame.add(btn0);
        
        Button btn1 = new Button("2. JavaScript");
        btn1.setBounds(225, 135, 100, 50);
        frame.add(btn1);
        
        frame.setResizable(false);
        frame.setVisible(true);

        // 자바형식으로 찾기
		btn0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                frame.dispose();
				new javaSearch();
			}
		});

		// 자바스크립트형식으로 찾기
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                frame.dispose();
				new javaScriptSearch();
			}
		});

        // x버튼 종료
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
    }
    public static void main(String[] args) {
         System.out.println("main 시작");
         new Main();
    }
}