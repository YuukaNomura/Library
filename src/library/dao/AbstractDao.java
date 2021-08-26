package library.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * データアクセス抽象クラス
 *
 * @author nomura
 */
public abstract class AbstractDao {

//	final String URL = "jdbc:mysql://localhost:3306/LIBRARY?serverTimezone=JST";
//	final String USER = "user";
//	final String PASS = "pass";

	public void getConnection() {
		AccessDB.getConnection();
	}

	public void closeConnection() {
		AccessDB.closeConnection();
	}

	public void rollback() throws SQLException {
		getConn().rollback();
	}

	public void commit() throws SQLException {
		getConn().commit();
	}

	public Connection getConn() {
		return AccessDB.conn;
	}

}
