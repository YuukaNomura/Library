package library.dto;


/**
 * 蔵書情報
 * @author nomura
 */
public class Book {

	/** タイトル */
	private String title;

	/** 著者 */
	private String writer;

	/** 出版社 */
	private String publisher;

	/** 出版年 */
	private int year;

	/** 蔵書キー */
	private String bookKey;

	/** 所有者*/
	private User user;

	/** 引数なしコンストラクタ 　*/
	public Book(){

	}

	/**
	 *  基本情報設定コンストラクタ
	 *
	 *  @param title タイトル
	 *  @param writer 著者
	 *  @param publisher 出版社
	 *  @param year 出版年
	 */
	public Book(String title,String writer,String publisher, int year){
		this.title = title;
		this.writer = writer;
		this.publisher = publisher;
		this.year = year;
	}

	/**
	 *  情報設定コンストラクタ
	 *
	 * 	@param title タイトル
	 *  @param writer 著者
	 *  @param publisher 出版社
	 *  @param year 出版年
	 *  @param bookKey 蔵書キー
	 */
	public Book(String title,String writer,String publisher, int year, String bookKey){
		this.title = title;
		this.writer = writer;
		this.publisher = publisher;
		this.year = year;
		this.bookKey = bookKey;
	}


	/**
	 * タイトル取得
	 * @return タイトル
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * タイトル設定
	 * @param title タイトル
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 著者取得
	 * @return 著者
	 */
	public String getWriter() {
		return writer;
	}

	/**
	 * 著者設定
	 * @param writer 著者
	 */
	public void setWriter(String writer) {
		this.writer = writer;
	}

	/**
	 * 出版社取得
	 * @return 出版社
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * 出版社設定
	 * @param publisher 出版社
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * 出版年取得
	 * @return 出版年
	 */
	public int getYear() {
		return year;
	}

	/**
	 * 出版年設定
	 * @param year 出版年
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * 蔵書キー取得
	 * @return 蔵書キー
	 */
	public String getBookKey() {
		return bookKey;
	}

	/**
	 * 蔵書キー設定
	 * @param bookKey 蔵書キー
	 */
	public void setBookKey(String bookKey) {
		this.bookKey = bookKey;
	}

	/**
	 * 所有者取得
	 * @return 所有者
	 */
	public User getUser() {
		return user;
	}

	/**
	 * 所有者設定
	 * @param user 所有者
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 文字列化
	 * @return 文字列
	 */
	@Override
	public String toString() {
		return "Book[title: " + this.title + ", writer: " + this.writer + ", punlisher: " + this.publisher + ", year:" + this.year + ", user: " + this.user + ", bookKey: " + this.bookKey;

	}

}
