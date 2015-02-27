package sample;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class updtest {
	/**
	 * updateおよびcommit用のソース
	 */
	public static void main(String[] args){
		Connection con = null;
		//Statement stmt = null;
		PreparedStatement ps = null;
		try{
			Driver d = (Driver)Class.forName("com.relx.jdbc.LinterDriver").newInstance();

			String address = "jdbc:linter:linapid:192.9.200.27:1070:local";
			String user = "SYSTEM";
			String password = "MANAGER";

			con = DriverManager.getConnection(address, user, password);
			// 自動コミット解除
			con.setAutoCommit(false);

			//実行するSQL文とパラメータを指定する
			String sql = "UPDATE test SET NAME = ? WHERE ID=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, "hello");
            ps.setInt(2, 3);

            //UPDATE文を実行する
            int i = ps.executeUpdate();

            //処理件数を表示する
            System.out.println("結果：" + i);
			// コミット
			con.commit();
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
		        if (ps != null) {
		        	ps.close();
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
