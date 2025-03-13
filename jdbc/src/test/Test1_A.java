package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

// 모든 sql구문을 처리하기
public class Test1_A {
	static Connection conn;
	static {  //static 초기화블럭
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection
					("jdbc:mariadb://localhost:3306/gdjdb","gduser","1234");
		} catch(Exception e) {
			System.out.println("DB 연결 오류");
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while(true) {
		  try {
			System.out.println("sql 구문을 입력하세요(종료:exit)");
			String sql = scan.nextLine(); //sql 문장을 저장
			if (sql.equals("exit"))  break;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			if(pstmt.execute()) //execute() : 문장 실행. 오류발생시
				selectRtn(pstmt,sql); //select 문장 처리
			else
				updateRtn(pstmt);  //select 문장 이외의 문장 처리
		  } catch (SQLException e) {
				System.out.println("구문 실행시 오류가 발생했습니다." + e.getMessage());
		  }
		}
		System.out.println("프로그램 종료");
	}
	static void selectRtn(PreparedStatement pstmt,String sql) throws SQLException {
			PreparedStatement pstmt2 = conn.prepareStatement("select count(*) from ("
				+ sql + ") a");
			ResultSet rs2 = pstmt2.executeQuery();
			rs2.next(); //레코드가 1개
			System.out.println("조회된 레코드 수 : "+rs2.getString(1) + "\n");
			//조회된 데이터를 화면 출력
			ResultSet rs = pstmt.getResultSet();
			ResultSetMetaData rsmd = rs.getMetaData();
			int colcnt = rsmd.getColumnCount();
			//Column 이름 화면에 출력
			for(int i=1; i<= colcnt;i++) {
				System.out.print(rsmd.getColumnName(i) + "\t");
			}
			System.out.println();
			//조회된 레코드 출력
			while(rs.next()) {
				for(int i=1; i<= colcnt;i++) {
					System.out.print(rs.getString(i) + "\t");
				}
				System.out.println();
			}
	}
	static  void updateRtn(PreparedStatement pstmt) throws SQLException {
		System.out.println("처리된 레코드 :" + pstmt.getUpdateCount());
	}	
}
