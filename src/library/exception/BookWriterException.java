package library.exception;

/**
 * 蔵書の著者に関する例外<br>
 *
 * @author nomura
 * @version 2020.6.1
 */
public class BookWriterException extends Exception {

	/**
	 * 詳細メッセージを指定しないでBookWriterExceptionを構築 <br><br>
	 *
	 */
	public BookWriterException() {

	}

	/**
	 * 指定された詳細メッセージを持つBookWriterExceptionを構築 <br><br>
	 *
	 * @param err 詳細メッセージ
	 */
	public BookWriterException(String err) {
		super(err);

	}


}
