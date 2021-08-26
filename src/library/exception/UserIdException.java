package library.exception;

/**
 * ユーザIDに関する例外<br>
 *
 * @author nomura
 * @version 2020.6.1
 */
public class UserIdException extends Exception {

	/**
	 * 詳細メッセージを指定しないでUserIdExceptionを構築 <br><br>
	 *
	 */
	public UserIdException() {
	}

	/**
	 * 指定された詳細メッセージを持つUserIdExceptionを構築 <br><br>
	 *
	 * @param err 詳細メッセージ
	 */
	public UserIdException(String err) {
		super(err);

	}

}
