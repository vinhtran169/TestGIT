package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class rollbacktest {
	/**
	 * rollback用のソース
	 */
	public static void main(String[] args){
		Connection con = null;
		Statement stmt = null;
		try{
			Class.forName("com.relx.jdbc.LinterDriver").newInstance();

			String address = "jdbc:linter:linapid:192.9.200.27:1070:local";
			String user = "SYSTEM";
			String password = "MANAGER";

			con = DriverManager.getConnection(address, user, password);
			// 自動コミット解除
			con.setAutoCommit(false);
			String sql1 = "INSERT INTO test values(6,'bye1')";

			stmt = con.createStatement();
			//実行するSQL文とパラメータを指定する(ROLLBACK前)
			// 実行
			stmt.execute(sql1);
			stmt.close();

			con.rollback();

			stmt = con.createStatement();
			//実行するSQL文とパラメータを指定する(ROLLBACK前)
			String sql2 = "INSERT INTO test values(7,'bye2')";
			// 実行
			stmt.execute(sql2);
			con.commit();
			stmt.close();

		} catch (Exception e){
			try{
				System.out.println("実行失敗");
				con.rollback();
				e.printStackTrace();
			}catch(SQLException  se){
			}
		}finally{
			try {
		        // ステートメントのクローズ
		        if (stmt != null) {
		        	stmt.close();
		        }
		        // DB接続のクローズ
		        if (con != null) {
		        	con.close();
				}
		        System.out.println("処理終了");
			}catch(SQLException  se){
			}
		}
	}
}
