package library.exception;

/**
 * 蔵書の出版年に関する例外<br>
 *
 * @author nomura
 * @version 2020.6.1
 */
public class BookYearException extends Exception {
	/**
	 * 詳細メッセージを指定しないでBookYearExceptionを構築 <br><br>
	 *
	 */
	public BookYearException() {
	}

	/**
	 * 指定された詳細メッセージを持つBookYearExceptionを構築 <br><br>
	 *
	 * @param err 詳細メッセージ
	 */
	public BookYearException(String err) {
		super(err);

	}


}
