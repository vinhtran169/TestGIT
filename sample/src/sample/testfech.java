package sample;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class testfech {
	public static void main(String[] args){

		try{
			Driver d = (Driver)Class.forName("com.relx.jdbc.LinterDriver").newInstance();

			String address = "jdbc:linter:linapid:192.9.200.27:1070:local";
			String user = "SYSTEM";
			String password = "MANAGER";

			Connection con = DriverManager.getConnection(address, user, password);

			Statement stmtC = con.createStatement();
			Statement stmtP = con.createStatement();

			ResultSet resultsP;
			ResultSet resultsC;

			String sqlP = "select * from test";
			String sqlC = "select * from hello where id = 1";

			resultsP = stmtC.executeQuery(sqlP);
			while(resultsP.next()){
				System.out.println( "Parent:"+ resultsP.getInt(1) + ":" + resultsP.getString(2));

				resultsC = stmtP.executeQuery(sqlC);
				while(resultsC.next()){
					System.out.println("Child:"+ resultsC.getInt(1) + ":" + resultsC.getString(2));
				}
				resultsC.close();
			}
			resultsP.close();
		} catch (Exception e){
			e.printStackTrace();
			return;
		}
	}
}
