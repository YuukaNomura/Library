package library.exception;

/**
 * 蔵書の出版社に関する例外<br>
 *
 * @author nomura
 * @version 2020.6.1
 */
public class BookPublisherException extends Exception {

	/**
	 * 詳細メッセージを指定しないでBookPublisherExceptionを構築 <br><br>
	 *
	 */
	public BookPublisherException() {
	}

	/**
	 * 指定された詳細メッセージを持つBookPublisherExceptionを構築 <br><br>
	 *
	 * @param err 詳細メッセージ
	 */
	public BookPublisherException(String err) {
		super(err);

	}

}
