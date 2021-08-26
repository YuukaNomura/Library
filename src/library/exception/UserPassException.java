package library.exception;

/**
 * ユーザパスワードに関する例外<br>
 *
 * @author nomura
 * @version 2020.6.1
 */
public class UserPassException extends Exception {

	/**
	 * 詳細メッセージを指定しないでUserPassExceptionを構築 <br><br>
	 *
	 */
	public UserPassException() {

	}

	/**
	 * 指定された詳細メッセージを持つUserPassExceptionを構築 <br><br>
	 *
	 * @param err 詳細メッセージ
	 */
	public UserPassException(String err) {
		super(err);

	}

}
