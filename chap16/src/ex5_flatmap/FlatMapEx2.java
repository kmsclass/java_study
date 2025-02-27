package ex5_flatmap;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

/*
 * IntStream flatMapToInt() : Stream을 IntStream 으로 변경.  
 */
public class FlatMapEx2 {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("10,20,30","40,50,60");
		//list을 IntStream 으로 변경하기
		Function<String, IntStream> f =data->{
			String[] strArr = data.split(",");
			int[] intArr = new int[strArr.length];
			for(int i=0;i<strArr.length;i++) {
				intArr[i] = Integer.parseInt(strArr[i].trim());
			}
			return Arrays.stream(intArr);
		}; 
		IntStream isr = list.stream()   //Stream<String>
				//Stream<String> => IntStream
				.flatMapToInt(f);
		isr.forEach(System.out::println);
		//숫자들의 갯수,합과,평균 출력하기
		System.out.println("숫자들의 갯수:" + list.stream().flatMapToInt(f).count());
		System.out.println("숫자들의 합:" + list.stream().flatMapToInt(f).sum());
		System.out.println("숫자들의 평균:" +
		                   list.stream().flatMapToInt(f).average().getAsDouble());
		
	}
}
