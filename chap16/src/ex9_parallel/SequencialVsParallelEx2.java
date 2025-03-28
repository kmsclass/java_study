package ex9_parallel;

import java.util.Arrays;
import java.util.List;

public class SequencialVsParallelEx2 {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(0,1,2,3,4,5,6,7,8,9);
		long start = System.nanoTime();  //1970년 이후 부터 현재까지 시간을 나노초 리턴
		//Thread.currentThread() : 현재 실행 중인 Thread 객체
		list.stream().forEach((a) -> System.out.println(Thread.currentThread()));
		long end = System.nanoTime();
		System.out.println("순차처리 시간 : " + (end - start));
		start = System.nanoTime();
		//parallelStream : 병렬로 데이터를 처리하는 스트림
		list.parallelStream().forEach
		        ((a) -> System.out.println(Thread.currentThread()));
		end = System.nanoTime();
		System.out.println("병렬처리 시간 : " + (end - start));
	}
}
