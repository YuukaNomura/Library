package library.exception;

/**
 * ユーザデータが存在しない場合スローされる例外<br>
 *
 * @author nomura
 * @version 2020.6.1
 */
public class UserIsEmptyException extends Exception {
	/**
	 * 詳細メッセージを指定しないでUserIsEmptyExceptionを構築 <br><br>
	 *
	 */
	public UserIsEmptyException() {
	}

	/**
	 * 指定された詳細メッセージを持つUserIsEmptyExceptionを構築 <br><br>
	 *
	 * @param err 詳細メッセージ
	 */
	public UserIsEmptyException(String err) {
		super(err);

	}
}
