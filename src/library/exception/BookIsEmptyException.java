package library.exception;

/**
 * 蔵書データが存在しない場合スローされる例外<br>
 *
 * @author nomura
 * @version 2020.6.1
 */
public class BookIsEmptyException extends Exception {

	/**
	 * 詳細メッセージを指定しないでBookIsEmptyExceptionを構築 <br><br>
	 *
	 */
	public BookIsEmptyException() {
	}

	/**
	 * 指定された詳細メッセージを持つBookIsEmptyExceptionを構築 <br><br>
	 *
	 * @param err 詳細メッセージ
	 */
	public BookIsEmptyException(String err) {
		super(err);

	}
}
