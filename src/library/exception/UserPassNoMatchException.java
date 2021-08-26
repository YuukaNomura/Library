package library.exception;

/**
 * パスワードと確認用パスワードが一致しない場合にスローされる例外<br>
 *
 * @author nomura
 * @version 2020.6.1
 */
public class UserPassNoMatchException extends Exception {

	/**
	 * 詳細メッセージを指定しないでUserPassNoMatchExceptionを構築 <br><br>
	 *
	 */
	public UserPassNoMatchException() {
	}

	/**
	 * 指定された詳細メッセージを持つUserPassNoMatchExceptionを構築 <br><br>
	 *
	 * @param err 詳細メッセージ
	 */
	public UserPassNoMatchException(String err) {
		super(err);

	}

}
