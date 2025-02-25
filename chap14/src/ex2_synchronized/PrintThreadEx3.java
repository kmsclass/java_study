package ex2_synchronized;
/*
 * 동기화 메서드 방식
 * 
 * 동기화 영역.임계영역. synchronized 예약어 사용
 * - 동기화 블럭
 *    블럭에 synchronized(lock 객체) 예약어 사용 
 *    lock 객체는 공유객체여야함. 모든 스레드가 하나의 lock 객체를 공유해야함
 * - 동기화 메서드 : thread safe메서드
 *    메서드에 synchronized 예약어 사용. 메서드를 lock으로 사용함
 *    동기화 메서드도 유일해야 함. 공유객체의 메서드여야함.
 */
class PrintThread3 extends Thread {
	Printer prt;
	char ch;
	PrintThread3(Printer prt,char ch) {
		this.prt = prt;
		this.ch = ch;
	}
	public void run() {
		for(int i=0;i<20;i++) {
			prt.printChar(ch);
		}
	}
}
class Printer {
	public synchronized void printChar(char ch) { //동기화 메서드
		for(int i=0;i<80;i++) System.out.print(ch);
		System.out.println();
	}
}
public class PrintThreadEx3 {
	public static void main(String[] args) {
		Printer ptr = new Printer();
		Printer ptr2 = new Printer();
		Printer ptr3 = new Printer();
		PrintThread3 t1 = new PrintThread3(ptr,'A');
		PrintThread3 t2 = new PrintThread3(ptr2,'B');
		PrintThread3 t3 = new PrintThread3(ptr3,'C');
		t1.start();t2.start();t3.start();
	}
}
