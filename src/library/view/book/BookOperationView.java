package library.view.book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import library.dto.User;

public class BookOperationView {
	private InputStreamReader is;
	private BufferedReader reader;

	private int sentaku;

	private User user;

	public BookOperationView(InputStreamReader is, BufferedReader reader, User user) {
		this.is = is;
		this.reader = reader;
		this.user = user;

	}

	public int LibraryOperationSelect(){
		try {
			// 操作選択
			do {

				System.out.println("操作を選択してください");
				System.out.println("1: 蔵書検索\n2: 蔵書登録・更新");
				try {
					sentaku = Integer.parseInt(reader.readLine());
				} catch (NumberFormatException e) {
					sentaku = -1;
				}

				if (sentaku == 1){
					BookSearch ls = new BookSearch(is, reader);
					ls.Search();

					break;

				}else if(sentaku == 2) {
					BookUpdate lu = new BookUpdate(is, reader, user);
					lu.UpdateBook();
					break;
				}

				System.out.println("数字の\"1\"か\"2\"を入力してください\n");

			} while (true);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sentaku;
	}

}
