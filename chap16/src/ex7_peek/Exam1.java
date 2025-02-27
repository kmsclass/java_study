package ex7_peek;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/*
 * 1 ~ 100사이의 임의의 수 10개를 Stream으로 생성하여, 홀수요소와 홀수의 합을 출력하기  
 */
public class Exam1 {
	public static void main(String[] args) {
		int arr[] = new int[10];
		for(int i=0;i<10;i++) {
			arr[i] = ((int)(Math.random()*100) + 1);
		}
		System.out.println(Arrays.toString(arr));
		int tot = Arrays.stream(arr).filter(a->a%2==1)
				 .peek(i->System.out.println(i)).sum();
		System.out.println("홀수의 합:"+tot);
		
		//===================================
		//Random 클래스를 이용하여 Stream 생성하기
		//ints(난수의갯수,시작숫자,종료숫자) : 
		IntStream isr = new Random().ints(10,1,100);
		tot = isr.filter(a->a%2==1)
				 .peek(i->System.out.println(i)).sum();
		System.out.println("홀수의 합:"+tot);
	}
}
