package logic;

import java.awt.*;
import java.awt.event.*;

class Main{
    public Main(){
        Frame frame = new Frame("정규 표현식 찾기");
        frame.setBounds(700,200,400,300);
        frame.setLayout(null);
        frame.setBackground(Color.LIGHT_GRAY);
        Font font = new Font("맑은 고딕", Font.PLAIN, 14);

        Button btn0 = new Button("1. 자바");
        btn0.setBounds(80, 135, 75, 50);
        btn0.setFont(font);
        frame.add(btn0);
        
        Button btn1 = new Button("2. 자바스크립트");
        btn1.setBounds(200, 135, 120, 50);
        btn1.setFont(font);
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
         new Main();
    }
}