package ex4_operator;

import java.util.function.IntBinaryOperator;

/*
 * Operator 인터페이스
 *  - Function 인터페이스의 하위 인터페이스
 *  - 매개변수,리턴값 모두 존재. 
 *  - T applyXXXX(T) 
 *  
 *  -종류
 *    UnaryOperator<T> : T apply(T)          => Function 인터페이스의 하위 인터페이스
 *    BinaryOperator<T> : T apply(T ,T) => BiFunction 인터페이스의 하위 인터페이스
 *    DoubleUnaryOperator : double applyAsDouble(double)
 *    DoubleBinaryOperator : double applyAsDouble(double,double)
 *    IntUnaryOperator : int applyAsInt(int)
 *    IntBinaryOperator : int applyAsInt(int,int)
 *    LongUnaryOperator : long applyAsInt(long)
 *    LongBinaryOperator : long applyAsInt(long,long)
 */
public class OperatorEx1 {
	private static int[] score= {92,95,87,100,55};
	
	public static void main(String[] args) {
		System.out.print("최대값:");
		int max=score[0],min=score[0];
		for(int i : score) max = (i>max)?i:max;
		System.out.println(max);
		System.out.print("최소값:");
		for(int i : score) min = (i<min)?i:min;
		System.out.println(min);
		System.out.println("최대값:" + maxOrMin((a,b)->(a>b)?a:b));
		System.out.println("최소값:" + maxOrMin((a,b)->(a<b)?a:b));
	}
	private static int maxOrMin(IntBinaryOperator op ) {
		int result = score[0];
		for(int s : score) result = op.applyAsInt(result,s);
		return result;
	}
}
