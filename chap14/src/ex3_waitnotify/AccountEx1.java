package ex3_waitnotify;
/*
 * 생산자, 소비자 스레드 
 * 
 * mother, son 스레드는 하나 계좌를 공유한다.
 * 계좌에 잔액이 없으면 son은 wait() 상태
 * 계좌에 잔액이 있으면 mother wait()
 * mother : 1만원 ~ 3만원중 한개를 계좌에 입금하고 son 깨움
 * som    : 잔액을 0으로 만들고 mother 깨움
 * 
 * wait(), notify(), notifyAll()
 * 1. 동기화 영역(동기화 블럭, 동기화메서드)에서만 사용가능
 *    IllegalMonitorStateException 예외 발생
 * 2. Object 클래스의 멤버임 => 모든 객체들은 사용 가능   
 * 3. wait() : 현재 실행 중인 스레드를 대기상태로 변환. lock 해제함. 
 *             notify,notifyAll 메서드로만 해제 가능
 * 4. notify() : wait() 상태인 스레드 한개를 Runnable 상태로 변경. 
 *             한개를 개발자가 지정 불가. 시스템의 선택            
 * 5. notifyAll() : wait() 상태 스레드 전부를 Runnable 상태로 변경             
 */
class Account { //공유객체
	int money;
	synchronized void output() {  //Son Thread만 호출
		while(money == 0) {
			try {
				wait(); //동기화영역에서만 사용 가능. lock 해제
			} catch(InterruptedException e) {}
		}
		//notify() : wait() 상태의 스레드 중 시스템이 정해 주는 한개만 Runnable 상태로 변경. 
		notifyAll(); //동기화영역에서만 사용 가능. wait() 상태인 모든 스레드를 Runnable 상태로 변경
		System.out.println(Thread.currentThread().getName() + ":" + money + "원 출금");
		money = 0;
	}
	synchronized void input() { //Mother Thread만 호출 
		while(money > 0) {
			try {
				wait();
			}catch(InterruptedException e) {}
		}
		money = ((int)(Math.random() * 3) + 1) * 10000;
		notifyAll();
		System.out.println(Thread.currentThread().getName() + ":" + money + "원 입금");
	}
}
class Mother extends Thread {
	Account account;
	Mother(Account account) {
		super("Mother");
		this.account = account;
	}
	public void run() {
		for(int i=0;i<20;i++) {
			account.input();
			try {
				sleep((int)Math.random() * 3000); //0 ~ 3초까지 램덤하게 sleep
			}catch(InterruptedException e) {}
		}
	}
}
class Son extends Thread {
	Account account;
	Son(Account account) {
		super("Son");
		this.account = account;
	}
	public void run() {
		for(int i=0;i<20;i++) {
			account.output();
			try {
				sleep((int)(Math.random() * 1000)); //0~1초 램덤하게 sleep
			}catch(InterruptedException e) {}
		}
	}
}
public class AccountEx1 {
	public static void main(String[] args) {
		Account acc = new Account();
		Thread m = new Mother(acc);
		Thread s = new Son(acc);
		s.start(); m.start();
	}
}
