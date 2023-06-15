/*
 * [동기화 정리]
 * 1. 메서드 전체를 동기화 --> 리턴 타입 앞에 'synchronized'을 붙임. = 그 문장만 해당이 아니고, 해당되는 객체까지 묶여버림.
 * 2. { }만을 동기화. 'synchronized block' 라 불림. = 부분 싱크로를 걸어버림. 단, 키값 이름은 같아야함.
 */
//1. wait(), notify(), notifyAll() 사용하는 객체를 동기화 처리 해야함.
//2. wait(), notify(), notifyAll() 은 싱크로의 락을 소유한 스레드가 수행한다. 단, 락이 없는 스레드가 수행 할 경우 'llegalMonitorStateException' 이 발생한다.
//3, synchronized코드블럭("key")를 사용하여 동기화 할 수 있습니다. synchronized로 지정된 임계영역은 한 스레드가 이 영역에 접근하여 사용할때 lock이 걸림으로써 다른 스레드가 접근할 수 없게 됩니다.
//4. 코드블럭의 값을 문자열정의는 전역적으로 공유되는 객체이다, 그래서 타 클래스에서 동일한 키값을 저장하면 문제가 발생할 수 있다, private static final Object key = new Object();를 사용하는것을 권장한다. 
class calculator{int count = 0;
	private static final Object key = new Object();//현재 사용안함, "key"의 선언의 문제점을 지적하기 위해서.
	public int add(int n1, int n2) {
		synchronized ("key"){
			count++;
		}
		return n1 + n2;
	}
	public int minus(int n1, int n2) {synchronized ("key") {count++;}return n1 - n2;}
	public void showCount() {System.out.println("all Count : " + count);}}

class AddThread extends Thread{ calculator cal; public AddThread(calculator cal) {this.cal=cal;}
//Runnable(스케쥴러)은(는) Thread를 직접 접속시켜줘야하는 부분만 기억하면됨.
public void run() {for (int i = 0; i <5000; i++) {cal.add(1, 2);}}}

class MinusThread extends Thread{ calculator cal; public MinusThread(calculator cal) {this.cal=cal;}
public void run() {for (int i = 0; i <5000; i++) {cal.minus(1, 2);}}}

class AdderTwo{int num1,num2;
	public void fNum1 () {num1 += 1;}
	public void gNum1() {num1 -= 1;}
	public void fNum2() {num2 += 2;}
	public void gNum2() {num2 -= 2;}
	public void showAll() {System.out.println("num1 : " + num1); System.out.println("num2 : " + num2);}}

class AdderTwoThread extends Thread{AdderTwo inst; public AdderTwoThread(AdderTwo inst) {this.inst = inst;}
public void run() { for (int i = 0; i <=100; i++) {
	synchronized ("+") {inst.fNum1(); inst.gNum1();} //위쪽이나 여기에 해도 상관없다.
	synchronized ("-") {inst.fNum2(); inst.gNum2();}}};}

class Data{
	String data;
	public String getData() {
		try {
			synchronized (this) {//객체의 참조값.
				wait(); //기다려...
				}
			}
		catch(InterruptedException e) {
			e.printStackTrace();
		} 
		return data;
	}
	
	public void setData(String str) {
		data = str; 
		synchronized (this) {
			notifyAll();//일어나!
		}
	}
} 

class DataReaderThread extends Thread{
	Data data; 
	public DataReaderThread (Data data) {
		this.data = data;
	}
	public void run() {
		System.out.println("Reader1 : " + data.getData());
		}
	}

class DataWriterThread extends Thread{
	Data data;
	public DataWriterThread(Data data) {
		this.data = data;
	}
	public void run() {
		data.setData("it Durn!");
		}
	}

public class Synchronized_Ex {
	public static void main(String[] args) {
		calculator cal = new calculator();
		AddThread at = new AddThread(cal);
		MinusThread mt = new MinusThread(cal);
		at.start();
		mt.start();
		
		try {
			at.join();//at스레드가 종료될때까지 대기한다.
			mt.join();//mt스레드가 종료될때까지 대기한다.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//at와 mt는 new를 해서 서로 다른객체라서 count값은 공유되지 않고, 오로지 마지막에 끝나는 스레드의 값이 출력된다.
		cal.showCount();
		
		AdderTwo numInst = new AdderTwo();
		AdderTwoThread at2 = new AdderTwoThread(numInst);
		AdderTwoThread mt2 = new AdderTwoThread(numInst);
		at2.start();
		mt2.start();
		
		try {
			at2.join();
			mt2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		numInst.showAll();
		
		Data data = new Data();
		DataReaderThread reader1 = new DataReaderThread(data);
		DataReaderThread reader2 = new DataReaderThread(data);
		DataWriterThread writer = new DataWriterThread(data);
		try {
		reader1.start();
		reader2.start();
		System.out.println("reader wait....");
		
		Thread.sleep(3000);
		writer.start();
		System.out.println("writer is start.");
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
