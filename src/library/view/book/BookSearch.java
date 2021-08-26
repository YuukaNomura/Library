package library.view.book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import library.bl.BookBL;
import library.dto.Book;
import library.dto.User;
import library.util.CheckUtil;

public class BookSearch {
	private InputStreamReader is;
	private BufferedReader reader;
	private Book searchBook = null;
	private List<Book> resultBookList = null;

	private String id = "", pass = "";
	private User user;

	public BookSearch(InputStreamReader is, BufferedReader reader) {
		this.is = is;
		this.reader = reader;

	}

	public void Search() {
		try {
			// 操作選択

			System.out.println("検索情報を入力してください（空欄可）");

			System.out.print("タイトル：");
			searchBook = new Book();
			searchBook.setTitle(reader.readLine());

			System.out.print("著者：");
			searchBook.setWriter(reader.readLine());

			System.out.print("出版社：");
			searchBook.setPublisher(reader.readLine());

			do {
				try {
					System.out.print("出版年：");
					String year = reader.readLine();
					if(CheckUtil.checkYearFormat(year)) {
						searchBook.setYear(Integer.parseInt(year));
						break;
					}else {
						System.out.println("4桁以下の数字を入力してください。");
					}
				}catch(NumberFormatException e) {
					searchBook.setYear(-1);
					break;
				}

			}while(true);

		} catch (IOException e) {
			e.printStackTrace();
		}

		// 検索
		BookBL bBl = new BookBL();
		try {
			resultBookList = bBl.findByConditions(searchBook);


		if (resultBookList.isEmpty()) {
			System.out.println("検索結果は0件です");

		} else {

			System.out.println("☆★☆検索結果一覧☆★☆");

			for (Book resultBook : resultBookList) {
				System.out.println(resultBook.getTitle() + " : "
						+ resultBook.getWriter() + " : "
						+ resultBook.getPublisher() + " : "
						+ resultBook.getYear()/* + " : "
						+ resultBook.getUser().getName() + "(" + resultBook.getUser().getId() + ")"*/);
			}
		}
		} catch (Exception e) {
			System.out.println("システムエラーが発生しました。");
			e.printStackTrace();
		}
	}

}
