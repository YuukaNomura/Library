package library.exception;

/**
 * ユーザの名前に関する例外<br>
 *
 * @author nomura
 * @version 2020.6.1
 */
public class UserNameException extends Exception {

	/**
	 * 詳細メッセージを指定しないでUserNameExceptionを構築 <br><br>
	 *
	 */
	public UserNameException() {
	}

	/**
	 * 指定された詳細メッセージを持つUserNameExceptionを構築 <br><br>
	 *
	 * @param err 詳細メッセージ
	 */
	public UserNameException(String err) {
		super(err);

	}

}
