package com.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomException {
	//멀티 스레드와 CustomException체크하는데 스레드 이슈가 있어서, vsCode로 변환하지않고, jUnit5형태 그대로 옮김.
	volatile int i = 99; // 체크할 변수
	
	@Test
	public void ThreadTest() {
		try {
			 Thread test1 = new Thread(new Runnable() {
				@Override
				public void run() {
					synchronized (CustomException.this) {
						while(true) {
	                        i--;
	                        if (i <= 0)
	                            throw new SoldOutException("재고가 없습니다." + i);
						}
					}
				}
			});
			
			Thread test2 = new Thread(new Runnable() {
				@Override
				public void run() {
					synchronized (CustomException.this) {
						while(true) {
	                        i--;
	                        if (i <= 0)
	                            throw new SoldOutException("재고가 없습니다." + i);
						}
					}
				}
			});
			test1.start();
			test2.start();
		} catch (Exception e) {
			System.out.println("그외 에러 발생");
		}
	}
	
	class SoldOutException extends RuntimeException{
		public SoldOutException(String msg) {
			super(msg);
		}
	}
}
