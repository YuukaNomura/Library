package library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SampleDB {

	public static void main(String[] args) {

        final String URL
        = "jdbc:mysql://localhost:3306/LIBRARY?serverTimezone=JST";
        final String USER = "user";
        final String PASS = "pass";
        final String SQL = "select * from user where id =? and pass=? and name like?;";

        try(Connection conn =
                DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = conn.prepareStatement(SQL)){

            ps.setInt(1,123);
            ps.setString(2,"passv");
            ps.setString(3,"test%");

            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    System.out.println(
                    	rs.getInt("id") + " " +
                    	rs.getString("pass") + " " +
                        rs.getString("name"));
                }
            };
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("処理が完了しました");
        }
    }


}
