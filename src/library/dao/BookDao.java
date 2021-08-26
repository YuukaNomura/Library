package library.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library.dto.Book;

/**
 * 蔵書データアクセス操作
 *
 * @author nomura
 * @version 2020.6.1
 */
public class BookDao extends AbstractDao {

	/** 引数なしコンストラクタ 　*/
	public BookDao() {


	}

	/** 蔵書情報登録処理 <br><br>
	 *
	 * ＜各テーブルに対する操作＞<br>
	 *   蔵書情報テーブル：insert<br>
	 *
	 * @param book 蔵書情報
	 * @param key 蔵書キー
	 * @throws SQLException
	 */
	public void insert(Book book, String key) throws SQLException {

		final String SQL = "insert into book values(?, ?, ?, ?, ?)";

		try (PreparedStatement ps = getConn().prepareStatement(SQL)) {

			if (book.getTitle() != null) {
				ps.setString(1, book.getTitle());
			} else {
				ps.setString(1, "");
			}

			if (book.getWriter() != null) {
				ps.setString(2, book.getWriter());
			} else {
				ps.setString(2, "");
			}

			if (book.getPublisher() != null) {
				ps.setString(3, book.getPublisher());
			} else {
				ps.setString(3, "");
			}

			if (book.getYear() > -1) {
				ps.setInt(4, book.getYear());
			} else {
				ps.setInt(4, -1);
			}
			ps.setString(5, key);
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

	/** 蔵書データ更新処理<br><br>
	 *
	 * ＜各テーブルに対する操作＞<br>
	 *   蔵書情報テーブル：update<br>
	 *
	 * @param oldBook 更新前蔵書情報
	 * @param newBook 更新後蔵書情報
	 * @throws SQLException
	 *
	 */
	public void update(Book oldBook, Book newBook) throws SQLException {

		final String SQL = "update book set title=?, writer=?, publisher=?, year=? where title=? and writer=? and publisher=? and year=?";

		try (/*Connection conn = DriverManager.getConnection(URL, USER, PASS);*/
				PreparedStatement ps = getConn().prepareStatement(SQL)) {

			if (newBook.getTitle() != null) {
				ps.setString(1, newBook.getTitle());
			} else {
				ps.setString(1, "");
			}

			if (newBook.getWriter() != null) {
				ps.setString(2, newBook.getWriter());
			} else {
				ps.setString(2, "");
			}

			if (newBook.getPublisher() != null) {
				ps.setString(3, newBook.getPublisher());
			} else {
				ps.setString(3, "");
			}

			if (newBook.getYear() > -1) {
				ps.setInt(4, newBook.getYear());
			} else {
				ps.setInt(4, -1);
			}

			if (oldBook.getTitle() != null) {
				ps.setString(5, oldBook.getTitle());
			} else {
				ps.setString(5, "");
			}

			if (oldBook.getWriter() != null) {
				ps.setString(6, oldBook.getWriter());
			} else {
				ps.setString(6, "");
			}

			if (oldBook.getPublisher() != null) {
				ps.setString(7, oldBook.getPublisher());
			} else {
				ps.setString(7, "");
			}

			if (oldBook.getYear() > -1) {
				ps.setInt(8, oldBook.getYear());
			} else {
				ps.setInt(8, -1);
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

	/** 蔵書キーによる蔵書情報削除処理 <br><br>
	 *
	 * ＜各テーブルに対する操作＞<br>
	 *   蔵書情報テーブル：delete<br>
	 *
	 * @param BookKey 蔵書キー
	 * @throws SQLException
	 */
	public void remove(String bookKey) throws SQLException {

		final String SQL = "delete from book where bookKey=?";

		try (/*Connection conn = DriverManager.getConnection(URL, USER, PASS);*/
				PreparedStatement ps = getConn().prepareStatement(SQL)) {

			ps.setString(1, bookKey);
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

	/** 条件指定による蔵書情報削除処理 <br><br>
	 *
	 * 以下の条件（AND指定）に合致する蔵書データを削除<br>
	 *  １、タイトルの完全一致<br>
	 *  ２、著者の完全一致<br>
	 *  ３、出版社の完全一致<br><br>
	 *  ４、出版年の完全一致<br><br>
	 *
	 * ＜各テーブルに対する操作＞<br>
	 *   蔵書情報テーブル：delete<br>
	 *
	 * @param book 蔵書情報
	 */
	public void remove(Book book) {
		final String SQL = "delete from book where title=? and writer=? and publisher=? and year=?";

		try (/*Connection conn = DriverManager.getConnection(URL, USER, PASS);*/
				PreparedStatement ps = getConn().prepareStatement(SQL)) {

			if (book.getTitle() != null) {
				ps.setString(1, book.getTitle());
			} else {
				ps.setString(1, "");
			}

			if (book.getWriter() != null) {
				ps.setString(2, book.getWriter());
			} else {
				ps.setString(2, "");
			}

			if (book.getPublisher() != null) {
				ps.setString(3, book.getPublisher());
			} else {
				ps.setString(3, "");
			}

			if (book.getYear() > -1) {
				ps.setInt(4, book.getYear());
			} else {
				ps.setInt(4, -1);
			}

			int col = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//System.out.println("処理が完了しました");
		}
	}

	/**
	 * 条件指定による蔵書データ検索処理 <br><br>
	 *
	 * 以下の条件（AND指定）に合致する蔵書データを検索<br>
	 *  １、タイトルの前方一致<br>
	 *  ２、著者の前方一致<br>
	 *  ３、出版社の前方一致<br><br>
	 *  ４、出版年の完全一致(出版年の指定がない場合、-1～10000の期間指定)<br><br>
	 *
	 * ＜各テーブルに対する操作＞<br>
	 *   蔵書情報テーブル：select<br>
	 *
	 * @param book 蔵書情報
	 * @return 蔵書リスト
	 */
	public List<Book> findByConditions(Book book) {

		final String SQL = "select * from book where title like? and writer like? and publisher like? and year between ? and ?;";

		List<Book> resultlist = new ArrayList<>();

		try (/*Connection conn = DriverManager.getConnection(URL, USER, PASS);*/
				PreparedStatement ps = getConn().prepareStatement(SQL)) {

			if (book.getTitle() != null) {
				ps.setString(1, book.getTitle() + "%");
			} else {
				ps.setString(1, "%");
			}

			if (book.getWriter() != null) {
				ps.setString(2, book.getWriter() + "%");
			} else {
				ps.setString(2, "%");
			}

			if (book.getPublisher() != null) {
				ps.setString(3, book.getPublisher() + "%");
			} else {
				ps.setString(3, "%");
			}

			if (book.getYear() > -1) {
				ps.setInt(4, book.getYear());
				ps.setInt(5, book.getYear());
			} else {
				//すべての範囲(出版年は4桁の自然数のみ)
				ps.setInt(4, -1);
				ps.setInt(5, 10000);
			}

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					resultlist.add(new Book(rs.getString("title"), rs.getString("writer"), rs.getString("publisher"),
							rs.getInt("year"), rs.getString("bookKey")));

				}
			}
			;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//System.out.println("処理が完了しました");
		}

		return resultlist;
	}

	/**
	 * 条件指定による蔵書データ検索処理（完全一致） <br><br>
	 *
	 * 以下の条件（AND指定）に合致する蔵書データを検索<br>
	 *  １、タイトルの完全一致<br>
	 *  ２、著者の完全一致<br>
	 *  ３、出版社の完全一致<br><br>
	 *  ４、出版年の完全一致<br><br>
	 *
	 * ＜各テーブルに対する操作＞<br>
	 *   蔵書情報テーブル：select<br>
	 *
	 * @param book 蔵書情報
	 * @return 蔵書リスト
	 */
	public List<Book> findByConditionsPerfectMatching(Book book) {

		final String SQL = "select * from book where title=? and writer=? and publisher=? and year=?;";

		List<Book> resultlist = new ArrayList<>();

		try (/*Connection conn = DriverManager.getConnection(URL, USER, PASS);*/
				PreparedStatement ps = getConn().prepareStatement(SQL)) {

			if (book.getTitle() != null) {
				ps.setString(1, book.getTitle());
			} else {
				ps.setString(1, "");
			}

			if (book.getWriter() != null) {
				ps.setString(2, book.getWriter());
			} else {
				ps.setString(2, "");
			}

			if (book.getPublisher() != null) {
				ps.setString(3, book.getPublisher());
			} else {
				ps.setString(3, "");
			}

			if (book.getYear() > -1) {
				ps.setInt(4, book.getYear());
			} else {
				ps.setInt(4, -1);
			}

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					resultlist.add(new Book(rs.getString("title"), rs.getString("writer"), rs.getString("publisher"),
							rs.getInt("year"), rs.getString("bookKey")));

				}
			}
			;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//System.out.println("処理が完了しました");
		}

		return resultlist;
	}

	/**
	 * タイトルによる蔵書データ検索処理 <br><br>
	 *
	 * ＜各テーブルに対する操作＞<br>
	 *   蔵書情報テーブル：select<br>
	 *
	 * @param title タイトル
	 * @return 蔵書情報
	 */
	public Book findByTitle(String title) {

		Book resultBook = null;

		final String SQL = "select * from book where title like?;";

		try (/*Connection conn = DriverManager.getConnection(URL, USER, PASS);*/
				PreparedStatement ps = getConn().prepareStatement(SQL)) {

			if (title != null) {
				ps.setString(1, title + "%");
			} else {
				ps.setString(1, "%");
			}

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					resultBook = new Book(rs.getString("title"), rs.getString("writer"), rs.getString("publisher"),
							rs.getInt("year"), rs.getString("bookKey"));
				}
			}
			;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//System.out.println("処理が完了しました");
		}

		return resultBook;
	}

	/**
	 * 蔵書キーによる蔵書データ検索処理 <br><br>
	 *
	 * ＜各テーブルに対する操作＞<br>
	 *   蔵書情報テーブル：select<br>
	 *
	 * @param key 蔵書キー
	 * @return 蔵書情報
	 */
	public Book findByKey(String key) {

		Book resultBook = null;

		final String SQL = "select * from book where bookKey=?;";

		try (/*Connection conn = DriverManager.getConnection(URL, USER, PASS);*/
				PreparedStatement ps = getConn().prepareStatement(SQL)) {

			ps.setString(1, key);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					resultBook = new Book(rs.getString("title"), rs.getString("writer"), rs.getString("publisher"),
							rs.getInt("year"), rs.getString("bookKey"));
				}
			}
			;

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//System.out.println("処理が完了しました");
		}
		return resultBook;
	}

}
