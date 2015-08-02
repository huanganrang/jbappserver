package jb;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, UnsupportedEncodingException {
		String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=D:\\workspace\\jbappserver\\src\\main\\resources\\KW.mdb";
	    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
	    Connection connection = DriverManager.getConnection(url);
	    Statement statement = connection.createStatement();
	    String sql = "select * from car";
	    ResultSet rs =statement.executeQuery(sql);
	    while(rs.next()){
	    	System.out.println(new String(rs.getString(3).getBytes("unicode"),"GBK"));	    	
	    }

	}
	public static String UnicodeToChinese(String string) {
		String str = string.replace("\\u", ",");
		String[] s2 = str.split(",");
		String s1 = "";
		for (int i = 1; i < s2.length; i++) {
		s1 = s1 + (char) Integer.parseInt(s2[i], 16);
		}
		return s1;
		}
}
