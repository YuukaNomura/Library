package library.bl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library.dao.BookUserDao;
import library.dao.UserDao;
import library.dto.Book;
import library.dto.User;
import library.exception.UserIdException;
import library.exception.UserIsEmptyException;
import library.exception.UserLoginException;
import library.exception.UserNameException;
import library.exception.UserPassException;
import library.exception.UserPassNoMatchException;
import library.exception.UserRegisteredException;
import library.util.CheckUtil;
import library.util.ExceptionEnum;

/**
 * ユーザデータ操作のビジネスロジック<br>
 *
 * @author nomura
 * @version 2020.6.1
 */
public class UserBL{
	private UserDao uDao = new UserDao();

	public UserBL() {
		uDao.getConnection();
	}

	/** ユーザデータ登録処理 <br>
	 *
	 * @param user ユーザデータ
	 * @exception UserIdException,	UserPassException, UserPassNoMatchException, UserNameException, UserRegisteredException
	 * @throws SQLException
	 */
	public void insert(User user) throws UserIdException,
			UserPassException, UserPassNoMatchException, UserNameException, UserRegisteredException, SQLException, Exception {
		try {
			if (findById(user.getId()) == null) {
				try {
					checkUser(user.getId(), user.getPass(), user.getPass(), user.getName());
					uDao.insert(user);
					uDao.commit();
				} catch (Exception e) {
					uDao.rollback();
					e.printStackTrace();
					throw e;
				}
			} else {
				throw new UserRegisteredException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/** ユーザデータ更新処理 <br>
	 *
	 * @param oldUser 更新前ユーザデータ
	 * @param newUser 更新後ユーザデータ
	 * @exception  UserIsEmptyException
	 * @throws SQLException
	 */
	public void update(User oldUser, User newUser) throws UserIsEmptyException, SQLException, Exception {
		try {
			try {
				if (findByConditions(oldUser).isEmpty()) {
					throw new UserIsEmptyException();
				}
				uDao.update(oldUser, newUser);
				uDao.commit();
			} catch (Exception e) {
				uDao.rollback();
				e.printStackTrace();
				throw e;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/** ユーザデータ更新処理 <br>
	 *
	 * @param id ユーザID
	 * @param newUser 更新後ユーザデータ
	 * @exception  UserIsEmptyException
	 * @throws SQLException
	 */
	public void update(String id, User newUser) throws UserIsEmptyException, SQLException, Exception {

		try {
			try {
				User oldUser = findById(id);
				if (oldUser == null) {
					throw new UserIsEmptyException();
				}
				uDao.update(oldUser, newUser);
				uDao.commit();
			} catch (Exception e) {
				uDao.rollback();
				e.printStackTrace();
				throw e;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/** ユーザデータ削除処理 <br>
	 *
	 * @param id ユーザID
	 * @exception  UserIsEmptyException, Exception
	 */
	public void remove(String id) throws UserIsEmptyException, Exception {
		try {
			if (!(findById(id) == null)) {
				try {
					BookUserDao bud = new BookUserDao();
					List<Book> resultList = bud.findBookByUserId(id);
					if (uDao.findById("removeUser") == null) {
						try {
							insert(new User("removeUser", "removeUser", "削除されたユーザ"));
						} catch (UserRegisteredException e) {
							e.printStackTrace();
						}
					}
					for (Book book : resultList) {
						bud.update(book.getBookKey(), "removeUser");
					}
					uDao.remove(id);
					uDao.commit();
				} catch (Exception e) {
					uDao.rollback();
					e.printStackTrace();
					throw e;
				}
			} else {
				throw new UserIsEmptyException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/** 条件指定によるユーザ情報検索処理 <br>
	 *
	 * @param user ユーザ情報
	 * @return ユーザリスト
	 * @throws Exception
	 */
	public List<User> findByConditions(User user) throws Exception {
		if ("removeUser".equals(user.getId())) {
			return new ArrayList<>();
		}
		try {
			return uDao.findByConditions(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * IDによるユーザ情報検索処理 <br>
	 *
	 * @param id ユーザID
	 * @return ユーザ情報
	 * @throws Exception
	 */
	public User findById(String id) throws Exception {
		if ("removeUser".equals(id)) {
			return null;
		}
		try {
			return uDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * ログイン処理 <br>
	 *
	 * @param id ユーザID
	 * @param pass パスワード
	 * @exception  UserLoginException
	 * @return ログインユーザ情報
	 */
	public User login(String id, String pass) throws UserLoginException, Exception {
		User LoginUser = findById(id);
		if (LoginUser != null) {
			if (LoginUser.getPass().equals(pass)) {
				LoginUser.setPass("");
				return LoginUser;
			}
		}
		throw new UserLoginException();
	}

	/**
	 * 入力値チェック <br>
	 *
	 * @param id ユーザID
	 * @param pass1 パスワード
	 * @param pass2 パスワード（確認用）
	 * @param name 名前
	 * @exception  UserIdException, UserPassException, UserPassNoMatchException, UserNameException
	 * @return ユーザデータ
	 */
	public User checkUser(String id, String pass1, String pass2, String name) throws UserIdException,
			UserPassException, UserPassNoMatchException, UserNameException {
		checkId(id);
		checkPass(pass1, pass2);
		checkName(name);

		return new User(id, pass1, name);
	}

	/**
	 * IDチェック <br>
	 *
	 * @param id ユーザID
	 * @exception  UserIdException
	 */
	private void checkId(String id) throws UserIdException {
		if (!CheckUtil.checkNullBlank(id)) {
			throw new UserIdException(ExceptionEnum.NullBlank.getErrMsg());
		}
		if (!(id.length() < 11)) {
			throw new UserIdException(ExceptionEnum.LongLength.getErrMsg());
		}
		if (!CheckUtil.checkAlphaNumFormat(id)) {
			throw new UserIdException(ExceptionEnum.FormatErr.getErrMsg());
		}
		if ("removeUser".equals(id)) {
			throw new UserIdException("\"removeUser\"はIDに設定できません。");
		}
	}

	/**
	 * パスワードチェック <br>
	 *
	 * @param pass1 パスワード
	 * @param pass2 パスワード（確認用）
	 * @exception UserPassException, UserPassNoMatchException
	 */
	private void checkPass(String pass1, String pass2) throws UserPassException, UserPassNoMatchException {
		if (!CheckUtil.checkNullBlank(pass1)) {
			throw new UserPassException(ExceptionEnum.NullBlank.getErrMsg());
		}
		if (!(pass1.equals(pass2))) {
			throw new UserPassNoMatchException();
		}
		if (!(pass1.length() < 31)) {
			throw new UserPassException(ExceptionEnum.LongLength.getErrMsg());
		}
		if (!CheckUtil.checkAlphaNumFormat(pass1)) {
			throw new UserPassException(ExceptionEnum.FormatErr.getErrMsg());
		}

	}

	/**
	 * 名前チェック <br>
	 *
	 * @param name 名前
	 * @exception UserNameException
	 */
	private void checkName(String name) throws UserNameException {
		if (!CheckUtil.checkNullBlank(name)) {
			throw new UserNameException(ExceptionEnum.NullBlank.getErrMsg());
		}
		if (!(name.length() < 101)) {
			throw new UserNameException(ExceptionEnum.LongLength.getErrMsg());
		}
	}


}
