package ex6_sort;
import java.util.*;
import java.util.stream.*;

import chap16.Student;

class SortedEx3 {
	public static void main(String[] args) {
	     Stream<Student> studentStream = Stream.of(
					new Student("김삿갓",1,85,88,80),
	    		    new Student("김길동",1,89,56,66),
					new Student("김삿갓",1,80,88,80),
					new Student("김삿갓",1,90,88,80),
					new Student("이몽룡",2,90,78,95),
					new Student("임꺽정",3,60,56,88),
					new Student("임꺽정",1,90,72,72));
		  studentStream.sorted(
		   Comparator.comparing(Student::getBan) // 반별 정렬. 1차정렬. 메서드 참조 방식
		   .thenComparing(Comparator.naturalOrder())) //기본정렬 2차정렬
		  .forEach(System.out::println);
		  //예외 발생 : Stream은 한번 조회하고나면 close 됨. 
	     System.out.println("반별 총점순으로 내림 정렬하기========");
	     studentStream.sorted(
			   Comparator.comparing(Student::getBan) // 반별 정렬. 1차정렬. 메서드 참조 방식
			  .thenComparing(s->(s.getEng()+s.getMath()+s.getKor()) * -1)) //총점순 2차정렬
			  .forEach(System.out::println);
	}
}

