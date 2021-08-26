package library.exception;

/**
 * ユーザデータがすでに登録されている場合にスローされる例外<br>
 *
 * @author nomura
 * @version 2020.6.1
 */
public class UserRegisteredException extends Exception {

	/**
	 * 詳細メッセージを指定しないでUserRegisteredExceptionを構築 <br><br>
	 *
	 */
	public UserRegisteredException() {
	}

	/**
	 * 指定された詳細メッセージを持つUserRegisteredExceptionを構築 <br><br>
	 *
	 * @param err 詳細メッセージ
	 */
	public UserRegisteredException(String err) {
		super(err);

	}

}
