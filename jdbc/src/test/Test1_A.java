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
	static {
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
			String sql = scan.nextLine();
			if (sql.equals("exit"))  break;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			if(pstmt.execute())
				selectRtn(pstmt,sql);
			else
				updateRtn(pstmt);
			} catch (SQLException e) {
				System.out.println("구문 실행시 오류가 발생했습니다." + e.getMessage());
			}
		}
		System.out.println("프로그램 종료");
	}
	static void selectRtn(PreparedStatement pstmt,String sql) {
		try {
			PreparedStatement pstmt2 = conn.prepareStatement("select count(*) from ("
				+ sql + ") a");
			ResultSet rs2 = pstmt2.executeQuery();
			rs2.next();
			System.out.println("조회된 레코드 수 : "+rs2.getString(1) + "\n");
			ResultSet rs = pstmt.getResultSet();
			int colcnt = rs.getMetaData().getColumnCount();
			ResultSetMetaData rsmd = rs.getMetaData();
			for(int i=1; i<= colcnt;i++) {
				System.out.print(rsmd.getColumnName(i) + "\t");
			}
			System.out.println();
			
			while(rs.next()) {
				for(int i=1; i<= colcnt;i++) {
					System.out.print(rs.getString(i) + "\t");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	static  void updateRtn(PreparedStatement pstmt) {
		try {
			System.out.println("처리된 레코드 :" + pstmt.getUpdateCount());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
}
