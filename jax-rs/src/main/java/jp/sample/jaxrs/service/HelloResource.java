package jp.sample.jaxrs.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class HelloResource implements SampleResource {

	// Webサービス1
	public String sayHello(String message) {
		return String.format("Hello, %s", message);
	}

	// Webサービス2
    public iteminfo sayHello1() {
    	iteminfo item = new iteminfo();
    	item.name = "タンポポ";
    	item.price = 1000;

        return item;
    }

	// Webサービス3
	public iteminfo sayHello2(iteminfo item) {
		java.lang.System.out.println(item.name);
		java.lang.System.out.println(item.price);

		return item;
	}

	// Webサービス4(検索サンプル)
	public Result search(SearchItemInfo item) throws SQLException {
		// DBから値を取得
		ArrayList<Row> lst = HelloResource.getData(item.item);
		Result result = new Result();
        result.rows = lst;

        return result;
	}

	
	// Webサービス4(検索サンプル)
		public Result insert(SearchItemInfo item) throws SQLException {
			// DBから値を取得
			ArrayList<Row> lst = HelloResource.getData(item.item);
			Result result = new Result();
	        result.rows = lst;

	        return result;
		}

	// DBから値を取得
	public static ArrayList<Row> getData(String item){

		try{
			String address = "jdbc:linter:linapid:192.9.200.27:1070:local";
			String user = "SYSTEM";
			String password = "MANAGER";

			Class.forName("com.relx.jdbc.LinterDriver").newInstance();
			Connection con = DriverManager.getConnection(address, user, password);

			Statement stmt = con.createStatement();

			String strSql = "select * from hello";
			if (!item.equals("")) {
				strSql += " where message like '%" + item + "%'";
			}

			java.lang.System.out.println(strSql);

			ResultSet results;
			results = stmt.executeQuery(strSql);

			ArrayList<Row> lst = new ArrayList<Row>();

			while(results.next()){
				Row row = new Row();
				row.id = results.getInt(1);
				row.message = results.getString(2);
				lst.add(row);
			}

			return lst;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

}