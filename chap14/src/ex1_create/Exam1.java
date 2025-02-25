package ex1_create;
/*
 * 람다방식을 이용하여, RunnableEx1과 같은 결과가 나오도록 프로그램 구현하기
 */
public class Exam1 {
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + " 스레드 시작");
		Runnable r = ()->{
			for(int i=1;i<=5;i++) {
				System.out.println(i+"="+Thread.currentThread().getName());
				try {
					Thread.sleep(1000);
				} catch(InterruptedException e) {}
			}
		};
		Thread t1 = new Thread(r,"First");
		Thread t2 = new Thread(r,"Second");
		t1.start();
		t2.start();
		System.out.println(Thread.currentThread().getName() + " 스레드 종료");
	}
}
