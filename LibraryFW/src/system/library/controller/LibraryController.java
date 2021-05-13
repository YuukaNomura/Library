package system.library.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import library.bl.BookBL;
import library.bl.UserBL;
import library.dto.Book;
import library.dto.User;
import library.exception.BookIsEmptyException;
import library.exception.BookPublisherException;
import library.exception.BookRegisteredException;
import library.exception.BookTitleException;
import library.exception.BookWriterException;
import library.exception.BookYearException;
import library.exception.UserIdException;
import library.exception.UserIsEmptyException;
import library.exception.UserLoginException;
import library.exception.UserNameException;
import library.exception.UserPassException;
import library.exception.UserPassNoMatchException;
import library.exception.UserRegisteredException;
import library.util.ExceptionEnum;

public class LibraryController {
	private HttpSession session;

	public String controller(HttpServletRequest request) {
		String url = "";
		String viewId = (String) request.getParameter("viewId");
		session = request.getSession(false);
		if (!("login".equals(viewId))) {
			if (session == null) {
				/* まだ認証されていない */
				return "Login.jsp";
			} else {
				Object loginCheck = session.getAttribute("loginUser");
				if (loginCheck == null) {
					/* まだ認証されていない */
					return "Login.jsp";
				}
			}
		}

		switch (viewId) {
		case "login":
			System.out.println("login");
			System.out.println("login");
			return Login(request);

		case "BookSearch":
			System.out.println("BookSearch");
			return BookSearch(request);

		case "BookInsert":
			System.out.println("BookInsert");
			return BookInsert(request);

		case "BookSearchResult":
			System.out.println("BookSearchResult");
			return BookInfo(request);

		case "BookInfo.update":
			System.out.println("BookInfo.update");
			return BookInfoUpdate(request);

		case "BookInfo.remove":
			System.out.println("BookInfo.remove");
			return BookInfoRemove(request);

		case "BookUpdate":
			System.out.println("BookUpdate");
			return BookUpdate(request);

		case "BookRemove":
			System.out.println("BookRemove");
			return BookRemove(request);

		case "UserInsert":
			System.out.println("UserInsert");
			return UserInsert(request);

		case "UserSearch":
			System.out.println("UserSearch");
			return UserSearch(request);

		case "UserSearch.update":
			System.out.println("UserSearchUpdate");
			return UserSearchUpdate(request);

		case "UserSearch.remove":
			System.out.println("UserSearchRemove");
			return UserSearchRemove(request);

		case "UserUpdate":
			System.out.println("UserUpdate");
			return UserUpdate(request);

		case "UserRemove":
			System.out.println("UserRemove");
			return UserRemove(request);
		case "logout":
			System.out.println("logout");
			return Logout(request);
		default:
			break;
		}

		return url;
	}

	private String Login(HttpServletRequest request) {

		String id = request.getParameter("id");
		String pass = request.getParameter("pass");

		//UserBLのloginメソッドを呼び出し
		try {
			User user = new UserBL().login(id, pass);
			session = request.getSession(true);
			session.setAttribute("loginUser", user);
			session.setAttribute("LoginUserName", user.getName());
			return "OperationSelect.jsp";

		} catch (UserLoginException e) {
			request.setAttribute("Msg", "ログインに失敗しました。");
			e.printStackTrace();

		} catch (Exception e) {
			request.setAttribute("Msg", "システムエラーが発生しました。");
			e.printStackTrace();
		}
		return "Login.jsp";
	}

	private String BookSearch(HttpServletRequest request) {

		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String publisher = request.getParameter("publisher");
		String year = request.getParameter("year");

		BookBL bBL = new BookBL();
		List<Book> resultList = null;

		try {
			//出版年が正しい値か確認
			bBL.checkYear(year);

			resultList = bBL.findByConditions(
					bBL.createBook(title, writer, publisher, year));
			request.setAttribute("resultList", resultList);

			for (Book b : resultList) {
				System.out.println(b);
			}

			if (!resultList.isEmpty()) {
				return "/book/BookSearchResult.jsp";

			} else {
				request.setAttribute("Msg", "検索結果は0件です。条件を変更してください。");
			}
		} catch (BookYearException e1) {
			request.setAttribute("Msg", "出版年は4桁以下の正の数値で入力してください。");
			e1.printStackTrace();
		} catch (Exception e) {
			request.setAttribute("Msg", "システムエラーが発生しました。");
			e.printStackTrace();
		}
		return "/book/BookSearch.jsp";

	}

	private String BookInsert(HttpServletRequest request) {

		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String publisher = request.getParameter("publisher");
		String year = request.getParameter("year");
		String userId = request.getParameter("userId");

		BookBL bBL = new BookBL();
		Book book = null;
		UserBL uBL = new UserBL();
		try {
			book = bBL.checkBook(title, writer, publisher, year);
			if ((userId == null) || ("".contentEquals(userId))) {
				userId = ((User) session.getAttribute("loginUser")).getId();
			} else {
				if ((uBL.findById(userId) == null)) {
					request.setAttribute("Msg", "ユーザが存在しません。");
					throw new Exception("ユーザが存在しません");
				}
			}

			bBL.insert(book, userId);

			request.setAttribute("Msg", "登録完了しました。");
			request.setAttribute("Complete", true);
			//return "/book/BookInsert.jsp";

		} catch (BookTitleException e1) {
			request.setAttribute("Msg", "タイトルを100文字以内で入力してください。");
			e1.printStackTrace();
		} catch (BookWriterException e1) {
			request.setAttribute("Msg", "著者名を100文字以内で入力してください。");
			e1.printStackTrace();
		} catch (BookPublisherException e1) {
			request.setAttribute("Msg", "出版社名を100文字以内で入力してください。");
			e1.printStackTrace();
		} catch (BookYearException e1) {
			request.setAttribute("Msg", "出版年は4桁以下の数値で入力してください。");
			e1.printStackTrace();
		} catch (BookRegisteredException e1) {
			request.setAttribute("Msg", "すでに登録されています。入力情報を確認してください。");
			e1.printStackTrace();
		} catch (SQLException e) {
			request.setAttribute("Msg", "システムエラーが発生しました。");
			e.printStackTrace();
		} catch (Exception e) {
			//request.setAttribute("Msg", "システムエラーが発生しました。");
			e.printStackTrace();
		}

		return "/book/BookInsert.jsp";

	}

	private String BookInfo(HttpServletRequest request) {
		String bookKey = request.getParameter("bookKey");
		BookBL bBL = new BookBL();
		Book book;
		try {
			book = bBL.findByKey(bookKey);

			String title = book.getTitle();
			String writer = book.getWriter();
			String publisher = book.getPublisher();
			String year = "";
			String user = "";

			if (!(book.getYear() == -1)) {
				year = book.getYear() + "年";
			}

			if (book.getUser() != null) {
				if ("removeUser".equals(book.getUser().getId())) {
					user = book.getUser().getName();
				} else {
					user = book.getUser().getId() + " : " + book.getUser().getName();
				}
			}

			request.setAttribute("book", book);
			request.setAttribute("title", title);
			request.setAttribute("writer", writer);
			request.setAttribute("publisher", publisher);
			request.setAttribute("year", year);
			request.setAttribute("user", user);

		} catch (Exception e) {
			request.setAttribute("Msg", "システムエラーが発生しました。");
			e.printStackTrace();
		}
		return "/book/BookInfo.jsp";
	}

	private String BookInfoUpdate(HttpServletRequest request) {

		Book resultBook = (Book) session.getAttribute("oldBook");

		//System.out.println(resultBook);

		String title = resultBook.getTitle();
		String writer = resultBook.getWriter();
		String publisher = resultBook.getPublisher();
		String year = "";
		String userId = "";

		if (!(resultBook.getYear() == -1)) {
			year = year + resultBook.getYear();
		}

		if (resultBook.getUser() != null) {
			userId = resultBook.getUser().getId();
		}

		request.setAttribute("title", title);
		request.setAttribute("writer", writer);
		request.setAttribute("publisher", publisher);
		request.setAttribute("year", year);
		request.setAttribute("userId", userId);

		return "/book/BookUpdate.jsp";
	}

	private String BookInfoRemove(HttpServletRequest request) {
		Book resultBook = (Book) session.getAttribute("removeBook");
		//session.removeAttribute("removeBook");

		System.out.println(resultBook);

		String title = resultBook.getTitle();
		String writer = resultBook.getWriter();
		String publisher = resultBook.getPublisher();
		String year = "";
		String user = "";

		if (!(resultBook.getYear() == -1)) {
			year = year + resultBook.getYear() + "年";
		}

		if (resultBook.getUser() != null) {
			if ("removeUser".equals(resultBook.getUser().getId())) {
				user = resultBook.getUser().getName();
			} else {
				user = resultBook.getUser().getId() + " : " + resultBook.getUser().getName();
			}
		}

		request.setAttribute("BookKey", resultBook.getBookKey());

		request.setAttribute("title", title);
		request.setAttribute("writer", writer);
		request.setAttribute("publisher", publisher);
		request.setAttribute("year", year);
		request.setAttribute("user", user);

		return "/book/BookRemove.jsp";
	}

	private String BookUpdate(HttpServletRequest request) {

		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String publisher = request.getParameter("publisher");
		String year = request.getParameter("year");
		String userId = request.getParameter("userId");

		BookBL bBL = new BookBL();
		Book oldBook = (Book) session.getAttribute("oldBook");
		Book newBook = null;
		UserBL uBL = new UserBL();

		System.out.println(oldBook);

		//System.out.println(publisher);

		try {
			newBook = bBL.checkBook(title, writer, publisher, year);

			//			if((userId != null)) {
			//				if((uBL.findById(userId) == null)){
			//					request.setAttribute("Msg", "ユーザが存在しません。");
			//					throw new Exception("ユーザが存在しません");
			//				}
			//				newBook.setUser(uBL.findById(userId));
			//			}else {
			//				newBook.setUser((User) session.getAttribute("loginUser"));
			//			}

			if ((userId == null) || ("".contentEquals(userId))) {
				newBook.setUser((User) session.getAttribute("loginUser"));
			} else {
				if ((uBL.findById(userId) == null)) {
					request.setAttribute("Msg", "ユーザが存在しません。");
					throw new Exception("ユーザが存在しません");
				}
				newBook.setUser(uBL.findById(userId));
			}

			bBL.update(oldBook, newBook);

			request.setAttribute("Msg", "更新完了しました。");
			request.setAttribute("Complete", true);
			//return "/book/BookUpdate.jsp";

		} catch (BookTitleException e1) {
			request.setAttribute("Msg", "タイトルを100文字以内で入力してください。");
			e1.printStackTrace();
		} catch (BookWriterException e1) {
			request.setAttribute("Msg", "著者名を100文字以内で入力してください。");
			e1.printStackTrace();
		} catch (BookPublisherException e1) {
			request.setAttribute("Msg", "出版社名を100文字以内で入力してください。");
			e1.printStackTrace();
		} catch (BookYearException e1) {
			request.setAttribute("Msg", "出版年は4桁以下の数値で入力してください。");
			e1.printStackTrace();
		} catch (BookRegisteredException e1) {
			request.setAttribute("Msg", "すでに登録されています。入力情報を確認してください。");
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("title", title);
		request.setAttribute("writer", writer);
		request.setAttribute("publisher", publisher);
		request.setAttribute("year", year);
		request.setAttribute("userId", userId);

		return "/book/BookUpdate.jsp";
	}

	private String BookRemove(HttpServletRequest request) {

		String BookKey = request.getParameter("BookKey");

		BookBL bBL = new BookBL();
		try {
			bBL.remove(BookKey);
			request.setAttribute("Msg", "削除が完了しました。");
			request.setAttribute("Complete", true);
			//return "/book/BookRemove.jsp";
		} catch (BookIsEmptyException e) {
			request.setAttribute("Msg", "情報が存在しません。");
			e.printStackTrace();
		} catch (SQLException e) {
			request.setAttribute("Msg", "システムエラーが発生しました。");
			e.printStackTrace();
		}
		return "/book/BookRemove.jsp";
	}

	private String UserInsert(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String pass1 = request.getParameter("pass1");
		String pass2 = request.getParameter("pass2");
		String userName = request.getParameter("userName");

		UserBL uBl = new UserBL();
		User user = null;

		try {
			user = uBl.checkUser(userId, pass1, pass2, userName);
			uBl.insert(user);
			request.setAttribute("Msg", "登録完了しました。");
			request.setAttribute("Complete", true);
			//return "/user/UserInsert.jsp";
		} catch (UserIdException e1) {
			if (ExceptionEnum.NullBlank.getErrMsg().equals(e1.getMessage())) {
				request.setAttribute("Msg", "IDは必須項目です。");
			} else if (ExceptionEnum.LongLength.getErrMsg().equals(e1.getMessage())
					|| ExceptionEnum.FormatErr.getErrMsg().equals(e1.getMessage())) {
				request.setAttribute("Msg", "IDは10文字以内の半角英数で指定してください。");
			} else {
				request.setAttribute("Msg", "IDの値に間違いがあります。");
			}
			e1.printStackTrace();

		} catch (UserPassException e1) {
			if (ExceptionEnum.NullBlank.getErrMsg().equals(e1.getMessage())) {
				request.setAttribute("Msg", "パスワードは必須項目です。");
			} else if (ExceptionEnum.LongLength.getErrMsg().equals(e1.getMessage())
					|| ExceptionEnum.FormatErr.getErrMsg().equals(e1.getMessage())) {
				request.setAttribute("Msg", "パスワードは30文字以内の半角英数で指定してください。");
			} else {
				request.setAttribute("Msg", "パスワードの値に間違いがあります。");
			}
			e1.printStackTrace();
		} catch (UserPassNoMatchException e1) {
			request.setAttribute("Msg", "パスワードとパスワード（確認用）が一致しません。");
			e1.printStackTrace();
		} catch (UserNameException e1) {
			if (ExceptionEnum.NullBlank.getErrMsg().equals(e1.getMessage())) {
				request.setAttribute("Msg", "名前は必須項目です。");
			} else if (ExceptionEnum.LongLength.getErrMsg().equals(e1.getMessage())) {
				request.setAttribute("Msg", "名前が文字数の上限を超えています。");
			} else {
				request.setAttribute("Msg", "名前の値に間違いがあります。");
			}
			e1.printStackTrace();
		} catch (UserRegisteredException e1) {
			request.setAttribute("Msg", "すでに登録されているIDです。入力情報を確認してください。");
			e1.printStackTrace();
		} catch (Exception e) {

			request.setAttribute("Msg", "予期せぬエラーが発生しました。最初からやり直してください。");
			e.printStackTrace();
		}
		return "/user/UserInsert.jsp";
	}

	private String UserSearch(HttpServletRequest request) {
		String userId = request.getParameter("userId");

		if (!(userId == "")) {
			try {
				User resultUser;

				resultUser = new UserBL().findById(userId);

				if (!(resultUser == null)) {
					request.setAttribute("userId", resultUser.getId());
					request.setAttribute("userName", resultUser.getName());
					request.setAttribute("user", resultUser);
					return "/user/UserSearchResult.jsp";
				} else {
					request.setAttribute("Msg", "ユーザが存在しません。");
				}
			} catch (Exception e) {
				request.setAttribute("Msg", "システムエラーが発生しました。");
				e.printStackTrace();
			}
		} else {
			request.setAttribute("Msg", "IDを入力してください。");
		}
		return "/user/UserSearch.jsp";
	}

	private String UserSearchUpdate(HttpServletRequest request) {
		User oldUser = (User) session.getAttribute("oldUser");
		request.setAttribute("userId", oldUser.getId());
		request.setAttribute("userName",oldUser.getName());
		return "/user/UserUpdate.jsp";
	}

	private String UserSearchRemove(HttpServletRequest request) {
		User oldUser = (User) session.getAttribute("oldUser");
		request.setAttribute("userId", oldUser.getId());
		request.setAttribute("userName",oldUser.getName());
		return "/user/UserRemove.jsp";
	}

	private String UserUpdate(HttpServletRequest request) {
		String pass = request.getParameter("pass");
		String newPass1 = request.getParameter("newPass1");
		String newPass2 = request.getParameter("newPass2");
		String newName = request.getParameter("userName");

		User oldUser = (User) session.getAttribute("oldUser");
		UserBL uBl = new UserBL();
		try {
			oldUser = uBl.login(oldUser.getId(), pass);
			User newUser = uBl.checkUser(oldUser.getId(), newPass1, newPass2, newName);
			uBl.update(oldUser.getId(), newUser);
			request.setAttribute("Msg", "更新完了しました。");
			request.setAttribute("Complete", true);
			//session.removeAttribute("oldUser");
			session.setAttribute("oldUser", newUser);
		} catch (UserLoginException e) {

			request.setAttribute("Msg", "パスワードが違います。");
			e.printStackTrace();
		} catch (UserIdException e) {
			// ありえない
			request.setAttribute("Msg", "IDの値に間違いがあります。");
			e.printStackTrace();
		} catch (UserPassException e) {
			if (ExceptionEnum.NullBlank.getErrMsg().equals(e.getMessage())) {
				request.setAttribute("Msg", "パスワードは必須項目です。");
			} else if (ExceptionEnum.LongLength.getErrMsg().equals(e.getMessage())
					|| ExceptionEnum.FormatErr.getErrMsg().equals(e.getMessage())) {
				request.setAttribute("Msg", "パスワードは30文字以内の半角英数で指定してください。");
			} else {
				request.setAttribute("Msg", "パスワードの値に間違いがあります。");
			}
			e.printStackTrace();
		} catch (UserPassNoMatchException e) {
			request.setAttribute("Msg", "パスワードとパスワード（確認用）が一致しません。");
			e.printStackTrace();
		} catch (UserNameException e) {
			if (ExceptionEnum.NullBlank.getErrMsg().equals(e.getMessage())) {
				request.setAttribute("Msg", "名前は必須項目です。");
			} else if (ExceptionEnum.LongLength.getErrMsg().equals(e.getMessage())) {
				request.setAttribute("Msg", "名前が文字数の上限を超えています。");
			} else {
				request.setAttribute("Msg", "名前の値に間違いがあります。");
			}
			e.printStackTrace();
		} catch (Exception e) {
			request.setAttribute("Msg", "予期せぬエラーが発生しました。最初からやり直してください。");
			e.printStackTrace();
		}

		return "/user/UserUpdate.jsp";
	}

	private String UserRemove(HttpServletRequest request) {
		String userId = ((User) session.getAttribute("user")).getId();

		UserBL uBl = new UserBL();
		try {
			uBl.remove(userId);
			request.setAttribute("Msg", "削除が完了しました。");
			request.setAttribute("Complete", true);
		} catch (UserIsEmptyException e) {
			request.setAttribute("Msg", "情報が存在しません。");
			e.printStackTrace();
		} catch (Exception e) {
			request.setAttribute("Msg", "予期せぬエラーが発生しました。最初からやり直してください。");
			e.printStackTrace();
		}

		return "/user/UserRemove.jsp";
	}

	private String Logout(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.invalidate();
		return "Login.jsp";
	}
}
