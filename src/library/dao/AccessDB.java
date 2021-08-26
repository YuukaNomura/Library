package library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccessDB {
	private final static String URL
    = "jdbc:mysql://localhost:3306/LIBRARY?serverTimezone=JST";
	private final static String USER = "user";
    private final static String PASS = "pass";

    public static Connection conn = null;

    public static void getConnection() {

    	try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			//自動コミットをオフ
			conn.setAutoCommit(false);
		} catch (SQLException e) {

			e.printStackTrace();
		}

    }

    public static void closeConnection() {

    	try {
    		if(conn != null && conn.isClosed()) {
    			conn.close();
    			conn = null;
    		}

		} catch (SQLException e) {

			e.printStackTrace();
		}

    }



}
