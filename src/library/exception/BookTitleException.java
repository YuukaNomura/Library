package library.exception;

/**
 * 蔵書のタイトルに関する例外<br>
 *
 * @author nomura
 * @version 2020.6.1
 */
public class BookTitleException extends Exception {

	/**
	 * 詳細メッセージを指定しないでBookTitleExceptionを構築 <br><br>
	 *
	 */
	public BookTitleException() {

	}

	/**
	 * 指定された詳細メッセージを持つBookTitleExceptionを構築 <br><br>
	 *
	 * @param err 詳細メッセージ
	 */
	public BookTitleException(String err) {
		super(err);

	}


}
