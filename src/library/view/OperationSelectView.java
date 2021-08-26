package library.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import library.dto.User;
import library.view.book.BookOperationView;
import library.view.user.UserOperationView;

public class OperationSelectView {
	private InputStreamReader is;
	private BufferedReader reader;
	private User user;

	private int sentaku;

	public OperationSelectView(InputStreamReader is, BufferedReader reader, User user) {
		this.is = is;
		this.reader = reader;
		this.user = user;

	}

	public void OperationSelection() {
		try {
			// 操作選択
			do {

				System.out.println("操作を選択してください");
				System.out.println("1: 蔵書管理\n2: ユーザ操作");
				try {
					sentaku = Integer.parseInt(reader.readLine());
				} catch (NumberFormatException e) {
					sentaku = -1;
				}

				if (sentaku == 1 || sentaku == 2) {
					break;
				}

				System.out.println("数字の\"1\"か\"2\"を入力してください\n");

			} while (true);
		} catch (IOException e) {
			e.printStackTrace();
		}

		switch (sentaku) {
		case 1:
			BookOperationView lov = new BookOperationView(is, reader, user);
			lov.LibraryOperationSelect();

			break;

		case 2:
			UserOperationView uov = new UserOperationView(is, reader);
			uov.UserOperationSelect();

			break;

		default:
			// ありえない
			break;

		}

	}

}
