package library.bl;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import library.dao.BookDao;
import library.dao.BookUserDao;
import library.dto.Book;
import library.dto.User;
import library.exception.BookIsEmptyException;
import library.exception.BookPublisherException;
import library.exception.BookRegisteredException;
import library.exception.BookTitleException;
import library.exception.BookWriterException;
import library.exception.BookYearException;
import library.util.CheckUtil;
import library.util.ExceptionEnum;

/**
 * 蔵書データ操作のビジネスロジック<br>
 *
 * @author nomura
 * @version 2020.6.1
 */
public class BookBL {
	private BookDao bDao = new BookDao();

	public BookBL() {
		bDao.getConnection();
	}

	/** 蔵書データ登録処理 <br>
	 *
	 * @param book 蔵書データ
	 * @param userId 所有者ID
	 * @exception BookRegisteredException
	 * @throws SQLException
	 */
	public void insert(Book book, String userId) throws BookRegisteredException, SQLException {
		try {
			String key;
			do {
				key = UUID.randomUUID().toString();
			} while (bDao.findByKey(key) != null);

			if (findByConditionsPerfectMatching(book).isEmpty()) {
				try {
					bDao.insert(book, key);
					new BookUserDao().insert(key, userId);
					bDao.commit();
				} catch (Exception e) {
					bDao.rollback();
					e.printStackTrace();
					throw e;
				}
			} else {
				throw new BookRegisteredException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/** 蔵書データ更新処理 <br>
	 *
	 * @param oldUser 更新前蔵書データ
	 * @param newUser 更新後蔵書データ
	 * @exception BookRegisteredException
	 * @throws SQLException
	 */
	public void update(Book oldBook, Book newBook) throws BookRegisteredException, SQLException {
		try {
			if (findByConditionsPerfectMatching(newBook).isEmpty()) {
				try {
					bDao.update(oldBook, newBook);
					new BookUserDao().update(oldBook.getBookKey(), newBook.getUser().getId());
					bDao.commit();
				} catch (Exception e) {
					bDao.rollback();
					e.printStackTrace();
					throw e;
				}
			} else {
				User userTemp = new BookUserDao().findUserByBookKey(oldBook.getBookKey());
				if (userTemp.getId().equals(newBook.getUser().getId())) {
					throw new BookRegisteredException();
				} else {
					try {
						bDao.update(oldBook, newBook);
						new BookUserDao().update(oldBook.getBookKey(), newBook.getUser().getId());
						bDao.commit();
					} catch (Exception e) {
						bDao.rollback();
						e.printStackTrace();
						throw e;
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/** 蔵書データ削除処理 <br>
	 *
	 * @param book 蔵書データ
	 * @exception BookIsEmptyException
	 * @throws SQLException
	 */
	public void remove(Book book) throws BookIsEmptyException, SQLException {
		try {
			if (!findByConditionsPerfectMatching(book).isEmpty()) {
				try {
					new BookUserDao().remove(book.getBookKey());
					bDao.remove(book);
					bDao.commit();
				} catch (Exception e) {
					bDao.rollback();
					e.printStackTrace();
					throw e;
				}
			} else {
				throw new BookIsEmptyException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/** 蔵書データ削除処理 <br>
	 *
	 * @param bookKey 蔵書キー
	 * @exception BookIsEmptyException
	 * @throws SQLException
	 */
	public void remove(String bookKey) throws BookIsEmptyException, SQLException {
		try {
			if (!(findByKey(bookKey) == null)) {
				try {
					new BookUserDao().remove(bookKey);
					bDao.remove(bookKey);
					bDao.commit();
				} catch (Exception e) {
					bDao.rollback();
					e.printStackTrace();
					throw e;
				}
			} else {
				throw new BookIsEmptyException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 条件指定による蔵書データ検索処理 <br>
	 *
	 * @param book 蔵書データ
	 * @return 蔵書リスト
	 * @throws Exception
	 */
	public List<Book> findByConditions(Book book) throws Exception {
		try {
			List<Book> resultList = bDao.findByConditions(book);
			BookUserDao bud = new BookUserDao();
			for (Book b : resultList) {
				b.setUser(bud.findUserByBookKey(b.getBookKey()));
			}
			return resultList;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	/** タイトルによる蔵書データ検索処理 <br>
	 *
	 * @param title タイトル
	 * @return 蔵書データ
	 * @throws Exception
	 */
	public Book findByTitle(String title) throws Exception {
		try {
			Book book = bDao.findByTitle(title);
			book.setUser(new BookUserDao().findUserByBookKey(book.getBookKey()));
			return book;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	/** 蔵書キーによる蔵書データ検索処理 <br>
	 *
	 * @param key 蔵書キー
	 * @return 蔵書データ
	 * @throws Exception
	 */
	public Book findByKey(String key) throws Exception {
		try {
			Book book = bDao.findByKey(key);
			User user = new BookUserDao().findUserByBookKey(key);
			book.setUser(user);
			return book;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/** 条件指定による蔵書データ検索処理（完全一致）<br>
	 *
	 * @param book 蔵書データ
	 * @return 蔵書リスト
	 */
	public List<Book> findByConditionsPerfectMatching(Book book) {
		try {
			return bDao.findByConditionsPerfectMatching(book);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 入力値チェック <br>
	 *
	 * @param title タイトル
	 * @param writer 著者
	 * @param publisher 出版社
	 * @param year 出版年
	 * @exception  BookTitleException, BookWriterException, BookPublisherException, BookYearException
	 * @return 蔵書データ
	 */
	public Book checkBook(String title, String writer, String publisher, String year)
			throws BookTitleException, BookWriterException, BookPublisherException, BookYearException {
		checkTitle(title);
		checkWriter(writer);
		checkPublisher(publisher);
		checkYear(year);
		return createBook(title, writer, publisher, year);
	}

	/**
	 * タイトルチェック <br>
	 *
	 * @param title タイトル
	 * @exception  BookTitleException
	 */
	private void checkTitle(String title) throws BookTitleException {
		if (!CheckUtil.checkNullBlank(title)) {
			throw new BookTitleException(ExceptionEnum.NullBlank.getErrMsg());
		}
		if (!(title.length() < 101)) {
			throw new BookTitleException(ExceptionEnum.LongLength.getErrMsg());
		}
	}

	/**
	 * 著者チェック <br>
	 *
	 * @param writer 著者
	 * @exception BookWriterException
	 */
	private void checkWriter(String writer) throws BookWriterException {
		if (false) {
			throw new BookWriterException();
		}
		if (!(writer.length() < 101)) {
			throw new BookWriterException(ExceptionEnum.LongLength.getErrMsg());
		}
	}

	/**
	 * 出版社チェック <br>
	 *
	 * @param publisher 出版社
	 * @exception BookPublisherException
	 */
	private void checkPublisher(String publisher) throws BookPublisherException {
		if (false) {
			throw new BookPublisherException();
		}
		if (!(publisher.length() < 101)) {
			throw new BookPublisherException(ExceptionEnum.LongLength.getErrMsg());
		}
	}

	/**
	 * 出版年チェック <br>
	 *
	 * @param publisher 出版年
	 * @exception BookYearException
	 */
	public void checkYear(String year) throws BookYearException {
		if (!CheckUtil.checkYearFormat(year)) {
			throw new BookYearException();
		}
	}

	/**
	 * 蔵書データ作成（入力値にエラーがある場合、初期値設定） <br>
	 *
	 *
	 * @param title タイトル
	 * @param writer 著者
	 * @param publisher 出版社
	 * @param year 出版年
	 * @return 蔵書データ
	 */
	public Book createBook(String title, String writer, String publisher, String year) {
		String BookTitle;
		String BookWriter;
		String BookPublisher;
		int BookYear;
		if (CheckUtil.checkNullBlank(title)) {
			BookTitle = title;
		} else {
			BookTitle = "";
		}

		if (CheckUtil.checkNullBlank(writer)) {
			BookWriter = writer;
		} else {
			BookWriter = "";
		}

		if (CheckUtil.checkNullBlank(publisher)) {
			BookPublisher = publisher;
		} else {
			BookPublisher = "";
		}

		if (CheckUtil.checkYearFormat(year)) {
			if (CheckUtil.checkNullBlank(year)) {
				BookYear = Integer.parseInt(year);
			} else {
				BookYear = -1;
			}

		} else {
			return null;
		}

		return new Book(BookTitle, BookWriter, BookPublisher, BookYear);

	}

}
