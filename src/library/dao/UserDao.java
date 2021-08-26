package library.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library.dto.User;

/**
 * ユーザデータアクセス操作
 *
 * @author nomura
 * @version 2020.6.1
 */
public class UserDao extends AbstractDao{

	/** 引数なしコンストラクタ */
	public UserDao(){

	}

	/** ユーザ情報登録処理 <br><br>
	 *
	 * ＜各テーブルに対する操作＞<br>
	 *   ユーザ情報テーブル：insert<br>
	 *
	 * @param user ユーザ情報
	 * @throws SQLException
	 *
	 */
	public void insert(User user) throws SQLException {

		final String SQL = "insert into user values(?, ?, ?)";

        try(/*Connection conn =
                DriverManager.getConnection(URL, USER, PASS);*/
            PreparedStatement ps = getConn().prepareStatement(SQL)){

        	if(user.getId() != null){
                ps.setString(1,user.getId());
    		}else {
                ps.setString(1,"");
    		}

        	if(user.getPass() != null){
                ps.setString(2,user.getPass());
    		}else {
                ps.setString(2,"");
    		}

    		if(user.getName() != null){
                ps.setString(3,user.getName());
    		}else {
                ps.setString(3,"");
    		}

    		int col = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //System.out.println("処理が完了しました");
        }
	}

	/** ユーザデータ更新処理<br><br>
	 *
	 * ＜各テーブルに対する操作＞<br>
	 *   ユーザ情報テーブル：update<br>
	 *
	 * @param oldUser 更新前ユーザ情報
	 * @param newUser 更新後ユーザ情報
	 * @throws SQLException
	 *
	 */
	public void update(User oldUser, User newUser) throws SQLException {

		final String SQL = "update user set id=?, pass=?, name=? where id=? and pass=? and name=?";

        try(/*onnection conn =
                DriverManager.getConnection(URL, USER, PASS);*/
            PreparedStatement ps = getConn().prepareStatement(SQL)){

        	if(newUser.getId() != null){
                ps.setString(1,newUser.getId());
    		}else {
                ps.setString(1,"");
    		}

        	if(newUser.getPass() != null){
                ps.setString(2,newUser.getPass());
    		}else {
                ps.setString(2,"");
    		}

    		if(newUser.getName() != null){
                ps.setString(3,newUser.getName());
    		}else {
                ps.setString(3,"");
    		}

        	if(oldUser.getId() != null){
                ps.setString(4,oldUser.getId());
    		}else {
                ps.setString(4,"");
    		}

        	if(oldUser.getPass() != null){
                ps.setString(5,oldUser.getPass());
    		}else {
                ps.setString(5,"");
    		}

    		if(oldUser.getName() != null){
                ps.setString(6,oldUser.getName());
    		}else {
                ps.setString(6,"");
    		}
    		int col = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //System.out.println("処理が完了しました");
        }

	}

	/** ユーザIDによるユーザ情報削除処理 <br><br>
	 *
	 * ＜各テーブルに対する操作＞<br>
	 *   ユーザ情報テーブル：delete<br>
	 *
	 * @param userId 削除するユーザID
	 * @throws SQLException
	 */
	public void remove(String userId) throws SQLException {

		final String SQL = "delete from user where id=?";

       try(/*Connection conn =
               DriverManager.getConnection(URL, USER, PASS);*/
           PreparedStatement ps = getConn().prepareStatement(SQL)){

    	   ps.setString(1,userId);
    	   int col = ps.executeUpdate();

       } catch (SQLException e) {
           e.printStackTrace();
           throw e;
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           //System.out.println("処理が完了しました");
       }
	}


	/** ユーザ情報削除処理 <br><br>
	 *
	 * ＜各テーブルに対する操作＞<br>
	 *   ユーザ情報テーブル：delete<br>
	 *
	 * @param user 削除するユーザ情報
	 * @throws SQLException
	 */
	public void remove(User user) throws SQLException {

		final String SQL = "delete from user where id=? and pass=? and name=?";

        try(/*Connection conn =
                DriverManager.getConnection(URL, USER, PASS);*/
            PreparedStatement ps = getConn().prepareStatement(SQL)){

        	if(user.getId() != null){
                ps.setString(1,user.getId());
    		}else {
                ps.setString(1,"");
    		}

        	if(user.getPass() != null){
                ps.setString(2,user.getPass());
    		}else {
                ps.setString(2,"");
    		}

    		if(user.getName() != null){
                ps.setString(3,user.getName());
    		}else {
                ps.setString(3,"");
    		}

    		int col = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //System.out.println("処理が完了しました");
        }
	}

	/**
	 * IDによるユーザ情報検索処理
	 *
	 * @param id ユーザID
	 * @return ユーザ情報
	 * @throws SQLException
	 */
	public User findById(String id) throws SQLException{

		User resultUser = null;

        final String SQL = "select * from user where id=?;";

        try(/*Connection conn =
                DriverManager.getConnection(URL, USER, PASS);*/
            PreparedStatement ps = getConn().prepareStatement(SQL)){

        	if(id != null){
                ps.setString(1,id);
    		}else {
                ps.setString(1,"");
    		}

            try(ResultSet rs = ps.executeQuery()){
            	while(rs.next()) {
                	resultUser = new User(rs.getString("id"), rs.getString("pass"),rs.getString("name"));
            	}
            };
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //System.out.println("処理が完了しました");
        }

		return resultUser;

	}

	/**
	 * 条件指定によるユーザデータ検索処理 <br><br>
	 *
	 * 以下の条件（AND指定）に合致する蔵書データを検索<br>
	 *  １、ユーザIDの前方一致<br>
	 *  ２、ユーザの名前の前方一致<br>
	 *
	 * ＜各テーブルに対する操作＞<br>
	 *   ユーザ情報テーブル：select<br>
	 *
	 * @param user ユーザ情報
	 * @return ユーザリスト
	 * @throws SQLException
	 */
	public List<User> findByConditions(User user) throws SQLException {

		List<User> resultlist = new ArrayList<>();
        final String SQL = "select * from user where id like? and name like?;";

        try(/*Connection conn =
                DriverManager.getConnection(URL, USER, PASS);*/
            PreparedStatement ps = getConn().prepareStatement(SQL)){

        	if(user.getId() != null){
                ps.setString(1,user.getId() + "%");
    		}else {
                ps.setString(1,"%");
    		}

        	if(user.getName() != null){
                ps.setString(2,user.getName() + "%");
    		}else {
                ps.setString(2,"%");
    		}


            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                	resultlist.add(new User(rs.getString("id"), rs.getString("pass"),rs.getString("name")));
                }
            };
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //System.out.println("処理が完了しました");
        }

		return resultlist;

	}

}
