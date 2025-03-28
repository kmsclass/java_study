package ex3_string;
/*
 * count(문자열,찾는문자열) 구현하기
 * => 문자열에서 찾는 문자열의 갯수 리턴 
 */
public class Exam1 {
	public static void main(String[] args) {
		System.out.println(count("12345AB12AB45AB", "12"));// 2
		System.out.println(count("12345AB12AB45AB", "AB"));// 3
		System.out.println(count("12345", "AB"));  //0
	}

	private static int count(String s1, String s2) {
		int cnt=0;
		int index = 0;
/*		
		while(true) {
			index = s1.indexOf(s2,index);
			if(index < 0) break;
			cnt++;
			index++;
		}
*/
		for(int i=0;i<s1.length();i++) {
			index = s1.indexOf(s2,index);
			if(index < 0) break;
			cnt++;
			index++;
		}
		return cnt;
	}
}
