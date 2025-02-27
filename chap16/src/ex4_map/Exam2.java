package ex4_map;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;

/*
 * product.txt 파일에서 그랜저 반품목록 출력하기
 */
public class Exam2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("product.txt"));
		Function<String,Car> f = s -> {
			String[] str = s.split(",");
			String temp = "";
			try {
				temp = str[4];
			} catch (ArrayIndexOutOfBoundsException e) {
				temp = "";
			}
			return new Car(Integer.parseInt(str[0]), 
					Integer.parseInt(str[1]), str[2], 
					Integer.parseInt(str[3]), temp);
		}; 
		br.lines().map(f).filter(s -> s.getCar().equals("그랜저") && s.getCon() == 3).
		forEach(s -> System.out.println(s));
		//그랜저의 반품수량 출력하기
		br = new BufferedReader(new FileReader("product.txt"));
		int sum = br.lines().map(f)
				 .filter(s -> s.getCar().equals("그랜저") && s.getCon() == 3)
		         .mapToInt(s->s.getQty())  //Stream<Car> => 수량데이터 IntStream으로 변경
		         .sum(); //전체반품수량
		System.out.println("그랜저 반품 수량 :" + sum);
	}
}
