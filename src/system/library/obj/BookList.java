package system.library.obj;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import library.dto.Book;

public class BookList {

	@JsonProperty("bookList")
	private List<Book> bookList;

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}




}
