package jp.co.brycen.m2m.webservice;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Path("/pool")
@Component
public class PoolResource  {

	@GET
	@Path("/pool")
	public String sayHello() {

		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/LinterDB");
			Connection con;

			try {
				con = ds.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from hello;");

				while(rs.next()){
					System.out.println(rs.getString(2));
				}

				con.close();

				return String.format("Thank you !");

			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				return "SQLException.....................";
			}

		} catch (NamingException e) {
			e.printStackTrace();
			return "NamingException.....................";
		}
	}

}