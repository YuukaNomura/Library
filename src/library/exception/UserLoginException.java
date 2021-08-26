package library.exception;

/**
 * ログインに失敗した際にスローされる例外<br>
 *
 * @author nomura
 * @version 2020.6.1
 */
public class UserLoginException extends Exception {

	/**
	 * 詳細メッセージを指定しないでUserLoginExceptionを構築 <br><br>
	 *
	 */
	public UserLoginException() {
	}

	/**
	 * 指定された詳細メッセージを持つUserLoginExceptionを構築 <br><br>
	 *
	 * @param err 詳細メッセージ
	 */
	public UserLoginException(String err) {
		super(err);

	}

}
