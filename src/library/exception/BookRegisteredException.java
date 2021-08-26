package library.exception;

/**
 * 蔵書データがすでに登録されている場合にスローされる例外<br>
 *
 * @author nomura
 * @version 2020.6.1
 */
public class BookRegisteredException extends Exception {

	/**
	 * 詳細メッセージを指定しないでBookRegisteredExceptionを構築 <br><br>
	 *
	 */
	public BookRegisteredException() {

	}

	/**
	 * 指定された詳細メッセージを持つBookRegisteredExceptionを構築 <br><br>
	 *
	 * @param err 詳細メッセージ
	 */
	public BookRegisteredException(String err) {
		super(err);

	}


}
