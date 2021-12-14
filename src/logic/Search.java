package logic;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextComponent;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Search{
	
	private String name= "";
	
	public Search(String name , String type) {
		this.name = name;

		Frame frame = new Frame("검색");
		frame.setBounds(700, 200, 430, 360);
		frame.setLayout(null);
		frame.setBackground(Color.LIGHT_GRAY);
		Font font = new Font("맑은 고딕", Font.PLAIN, 20);

		Label lb0 = new Label(this.name);
		lb0.setBounds(102, 140, 300, 30);
		lb0.setFont(font);

		TextField ta0 = new TextField();
		ta0.setBounds(110, 250, 145, 30);
		ta0.setFont(font);
		
		Button btn0 = new Button("Search");
		btn0.setBounds(260,250,90,30);
		btn0.setFont(font);

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
                
                switch(name) {
                case "Date (YYYY.mm.dd)":
                	try {
                		final String text = ta0.getText();

                		if(!text.equals("")) {
								if(!(text.indexOf(".") == -1)) {
									new SearchList("^([0][1-9]|[12][0-9])\\d{2}.(0[1-9]|1[012]).(0[1-9]|[12][0-9]|3[01])$", type); 
									return;
								} else if(!(text.indexOf("-") == -1)) {
									new SearchList("^([0][1-9]|[12][0-9])\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$", type); 
									return;
								} else if(!(text.indexOf(":") == -1)) {
									new SearchList("^([0][1-9]|[12][0-9])\\d{2}:(0[1-9]|1[012]):(0[1-9]|[12][0-9]|3[01])$", type); 
									return;
								} else {//아무것도 없거나 그외인 경우
									new SearchList("^([0][1-9]|[12][0-9])\\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$", type); 
									return;
								}
							
                		} else {
                			System.out.println("Data null");
                		}
						
					} catch (NumberFormatException e2) {
						new SearchList("데이터 입력 오류", type);
					}
                	break;
                	
                case "only Number":
                	try {
                		new SearchList("^[0-9]{"+ta0.getText().length()+"}$", type);
						
					} catch (NumberFormatException e2) {
						new SearchList("데이터 입력 오류", type);
					}
                	break;
                	
                case "only Korean":
                	try {
                		new SearchList("^[가-힣]{"+ta0.getText().length()+"}$", type);
						
					} catch (NumberFormatException e2) {
						new SearchList("데이터 입력 오류", type);
					}
                	break;
                	
                case "only English (LowerCase)":
                	try {
                		new SearchList("^[a-z]{"+ta0.getText().length()+"}$", type);
						
					} catch (NumberFormatException e2) {
						new SearchList("데이터 입력 오류", type);
					}
                	break;
                	
                case "Email (naver, google)":
                	new SearchList("no data", type);
                	break;
                }
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
				if(e.getKeyCode() == e.VK_ENTER) {
					frame.dispose();
					
					 switch(name) {
					 case "Date (YYYY.mm.dd)":
		                	try {
		                		final String text = ta0.getText();

		                		if(!text.equals("")) {
										if(!(text.indexOf(".") == -1)) {
											new SearchList("^([0][1-9]|[12][0-9])\\d{2}.(0[1-9]|1[012]).(0[1-9]|[12][0-9]|3[01])$", type); 
											return;
										} else if(!(text.indexOf("-") == -1)) {
											new SearchList("^([0][1-9]|[12][0-9])\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$", type); 
											return;
										} else if(!(text.indexOf(":") == -1)) {
											new SearchList("^([0][1-9]|[12][0-9])\\d{2}:(0[1-9]|1[012]):(0[1-9]|[12][0-9]|3[01])$", type); 
											return;
										} else {//아무것도 없거나 그외인 경우
											new SearchList("^([0][1-9]|[12][0-9])\\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$", type); 
											return;
										}
									
		                		} else {
		                			System.out.println("Data null");
		                			Dialog log = new Dialog(frame, "빈값으로 검색할 수 없습니다.", true);
		                			log.setSize(140,90);
		                			log.show();
		                		}
								
							} catch (NumberFormatException e2) {
								new SearchList("데이터 입력 오류", type);
							}
		                	break;
		                	
		                case "only Number":
		                	try {
		                		new SearchList("^[0-9]{"+ta0.getText().length()+"}$", type);
								
							} catch (NumberFormatException e2) {
								new SearchList("데이터 입력 오류", type);
							}
		                	break;
		                	
		                case "only Korean":
		                	try {
		                		new SearchList("^[가-힣]{"+ta0.getText().length()+"}$", type);
								
							} catch (NumberFormatException e2) {
								new SearchList("데이터 입력 오류", type);
							}
		                	break;
		                	
		                case "only English (LowerCase)":
		                	try {
		                		new SearchList("^[a-z]{"+ta0.getText().length()+"}$", type);
								
							} catch (NumberFormatException e2) {
								new SearchList("데이터 입력 오류", type);
							}
		                	break;
		                	
		                case "Email (naver, google)":
		                	new SearchList("no data", type);
		                	break;
		                }
				}
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