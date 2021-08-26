package library.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library.dto.Book;
import library.dto.User;

/**
 * 蔵書所有者データアクセス操作
 *
 * @author nomura
 * @version 2020.6.1
 */
public class BookUserDao extends AbstractDao{

	/** 引数なしコンストラクタ 　*/
	public BookUserDao() {

	}

	/** 蔵書所有者データ登録処理 <br><br>
	 *
	 * ＜各テーブルに対する操作＞<br>
	 *   蔵書所有者テーブル：insert<br>
	 *
	 * @param Bookkey 蔵書キー
	 * @param userId 所有者ID
	 * @throws SQLException
	 */
	public void insert(String Bookkey, String userId) throws SQLException {
		final String SQL = "insert into book_user values(?, ?)";

        try(/*Connection conn =
                DriverManager.getConnection(URL, USER, PASS);*/
            PreparedStatement ps = getConn().prepareStatement(SQL)){

        	ps.setString(1,userId);
    		ps.setString(2,Bookkey);
    		int col = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            //System.out.println("処理が完了しました");
        }
	}

	/** 蔵書所有者データ更新処理 <br><br>
	 *
	 * ＜各テーブルに対する操作＞<br>
	 *   蔵書所有者テーブル：update<br>
	 *
	 * @param Bookkey 蔵書キー
	 * @param userId 新所有者ID
	 * @throws SQLException
	 */
	public void update(String Bookkey, String userId) throws SQLException {
		final String SQL = "update book_user set userId=? where Bookkey=?";

        try(/*Connection conn =
                DriverManager.getConnection(URL, USER, PASS);*/
            PreparedStatement ps = getConn().prepareStatement(SQL)){

        	ps.setString(1,userId);
    		ps.setString(2,Bookkey);
    		int col = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            //System.out.println("処理が完了しました");
        }
	}

	/** 蔵書所有者データ削除処理 <br><br>
	 *
	 * ＜各テーブルに対する操作＞<br>
	 *   蔵書所有者テーブル：delete<br>
	 *
	 * @param Bookkey 蔵書キー
	 * @throws SQLException
	 */
	public void remove(String Bookkey) throws SQLException {
		final String SQL = "delete from book_user where bookKey=?";

        try(/*Connection conn =
                DriverManager.getConnection(URL, USER, PASS);*/
            PreparedStatement ps = getConn().prepareStatement(SQL)){

    		ps.setString(1,Bookkey);
    		int col = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            //System.out.println("処理が完了しました");
        }
	}

	/** 蔵書キーによる蔵書所有者データ検索処理 <br><br>
	 *
	 * ＜各テーブルに対する操作＞<br>
	 *   ユーザ情報テーブル：select<br>
	 *
	 * @param bookkey 蔵書キー
	 * @return ユーザ情報
	 * @throws SQLException
	 */
	public User findUserByBookKey(String bookKey) throws SQLException {
		User resultUser = null;

        final String SQL = "select * from user LEFT JOIN book_user on user.id=book_user.userId where book_user.bookKey=?;";

        try(/*Connection conn =
                DriverManager.getConnection(URL, USER, PASS);*/
            PreparedStatement ps = getConn().prepareStatement(SQL)){

        	ps.setString(1,bookKey);
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
            throw e;
        } finally {
        }

		return resultUser;

	}

	/** ユーザIDによる蔵書データ検索処理 <br><br>
	 *
	 * ＜各テーブルに対する操作＞<br>
	 *   蔵書情報テーブル：select<br>
	 *
	 * @param userID ユーザID
	 * @return 蔵書リスト
	 * @throws SQLException
	 */
	public List<Book> findBookByUserId(String userId) throws SQLException {
		List<Book> resultlist = new ArrayList<>();

        final String SQL = "select * from book LEFT JOIN book_user on book.bookKey=book_user.bookKey where book_user.userId=?;";

        try(/*Connection conn =
                DriverManager.getConnection(URL, USER, PASS);*/
            PreparedStatement ps = getConn().prepareStatement(SQL)){

        	ps.setString(1,userId);
            try(ResultSet rs = ps.executeQuery()){
            	while (rs.next()) {
            		resultlist.add(new Book(rs.getString("title"), rs.getString("writer"),rs.getString("publisher"),rs.getInt("year"),rs.getString("bookKey")));
            	}

            };
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            //System.out.println("処理が完了しました");
        }

		return resultlist;

	}

}
