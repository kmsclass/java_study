package ex5_flatmap;

import java.util.Arrays;
import java.util.List;

/*
 실행 결과가 다음과 같이 나오도록 프로그램을 완성하시오
This
is
a
Java
book
Lambda
Expressions
Java8
supports
lambda
Expressions
 */
public class Exam1 {
	public static void main(String[] args) {
		List<String> list = Arrays.asList(
				"This is a Java book",
				"Lambda Expressions",
				"Java8 supports lambda Expressions");
		list.stream().flatMap(s-> {
//		    String[] strarr = s.split(" ");
//		    return Arrays.stream(strarr);
			return Arrays.stream(s.split(" "));
		}).forEach(s->System.out.println(s));
	}
}
