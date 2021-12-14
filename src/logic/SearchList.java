package logic;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class SearchList{
	
	private String name = "";
	private String[] exe = {};
	private char mark;
	
	public SearchList(String name , String type) {
		this.name = name;
		
		if(name.length() > 60) {
			mark = name.charAt(26);
			List<Integer> colBox = new ArrayList<Integer>();
			
			if(!(name.indexOf(".") == -1)) {
				for (int i = 0; i < name.length(); i++) {
					if(name.charAt(i) == 46) colBox.add(i);
				}
			} else if(!(name.indexOf(":") == -1)) {
				for (int i = 0; i < name.length(); i++) {
					if(name.charAt(i) == 58) colBox.add(i);
				}
			} else if(!(name.indexOf("-") == -1)) {
				colBox.add(26);
				colBox.add(41);
			} else {
				//예외처리 아직 안함
				colBox.add(26);
				colBox.add(41);
			}
			
			exe = new String[colBox.size()+1];
			
			for (int i = 0; i < colBox.size(); i++) {
				if(i == 0) exe[i] = name.substring(i,colBox.get(i));
				else exe[i] = name.substring(colBox.get(i-1)+1, colBox.get(i));
			}
			exe[exe.length-1] = name.substring(colBox.get(colBox.size()-1)+1, name.length());
		}
		
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
		
		if(exe.length == 0) {
			Label lb1 = new Label(this.name);
			lb1.setBounds(135, 185, 200, 30);
			lb1.setFont(font);
			frame.add(lb1);
			
		} else {
			String mark = "";
			
			if(this.mark == 40) {
				mark = "";
				Label lb2 = new Label(exe[1]+mark);
				lb2.setBounds(105, 175, 220, 25);
				lb2.setFont(font3);
				frame.add(lb2);
			} 
			else if(this.mark == 45) {
				mark = "-";
				Label lb2 = new Label(exe[1]);
				lb2.setBounds(105, 175, 220, 25);
				lb2.setFont(font3);
				frame.add(lb2);
			} 
			else if(this.mark == 46) {
				mark = ".";
				Label lb2 = new Label(exe[1]+mark);
				lb2.setBounds(105, 175, 220, 25);
				lb2.setFont(font3);
				frame.add(lb2);
			}
			else if(this.mark == 58) {
				 mark = ":";
				Label lb2 = new Label(exe[1]+mark);
				lb2.setBounds(105, 175, 220, 25);
				lb2.setFont(font3);
				frame.add(lb2);
			}
			
			Label lb1 = new Label(exe[0]+mark);
			lb1.setBounds(105, 150, 220, 25);
			lb1.setFont(font3);
			Label lb3 = new Label(exe[2]);
			lb3.setBounds(105, 200, 220, 25);
			lb3.setFont(font3);
			
			frame.add(lb1);
			frame.add(lb3);
		}
		
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