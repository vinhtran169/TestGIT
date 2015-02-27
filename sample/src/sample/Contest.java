package sample;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Contest {

	public static void main(String[] args){

		try{
			String address = "jdbc:linter:linapid:192.9.200.27:1070:local";
			String user = "SYSTEM";
			String password = "MANAGER";

			Driver d = (Driver)Class.forName("com.relx.jdbc.LinterDriver").newInstance();

			Connection con = DriverManager.getConnection(address, user, password);

			Statement stmt = con.createStatement();

			ResultSet results;
			results = stmt.executeQuery("select * from test");

			while(results.next()){
				System.out.println(results.getInt(1) + ":" + results.getString(2));
			}


		} catch (Exception e){
			e.printStackTrace();
			return;
		}
	}

}
