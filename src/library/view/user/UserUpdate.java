package library.view.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import library.bl.UserBL;
import library.dto.User;
import library.exception.UserIsEmptyException;

public class UserUpdate {
	private InputStreamReader is;
	private BufferedReader reader;

	private int sentaku = -1;

	public UserUpdate(InputStreamReader is, BufferedReader reader) {
		this.is = is;
		this.reader = reader;
	}

	public void UpdateUser() {
		do {
			try {
				System.out.println("操作を選択してください\n1: ユーザ情報更新\n2: ユーザ情報削除");
				sentaku = Integer.parseInt(reader.readLine());

				if (sentaku == 1) {
					update();
					break;
				} else if (sentaku == 2) {
					remove();
					break;
				}

			} catch (NumberFormatException e) {

			}

			catch (IOException e) {
				e.printStackTrace();
			}

		} while (true);

	}

	private void update() {

		try {
			System.out.println("更新するユーザのIDを入力してください");

			System.out.print("ID：");

			// 検索
			UserBL uBL = new UserBL();
			User user = uBL.findById(reader.readLine());

			if (user == null) {
				System.out.println("該当データはありません。IDを変更して最初からやり直してください。");
				System.exit(0);

			}

			User newUser = new User();

			System.out.println("更新するデータを入力してください。");

			System.out.println("ID:" + user.getId());
			newUser.setId(user.getId());

			do {
			System.out.print("現在のパスワード：");
			String oldPassword = (reader.readLine());
			if(!oldPassword.equals(user.getPass())) {
				System.out.println("パスワードが一致しません。");
			}else {
				break;
			}
			}while(true);

			System.out.print("名前：");
			newUser.setName(reader.readLine());

			do {
				System.out.print("新しいパスワード：");
				String pass1 = reader.readLine();

				System.out.print("新しいパスワード（確認用）：");
				String pass2 = reader.readLine();
				if (pass1.isEmpty()) {
					System.out.println("パスワードを入力してください。");
				}
				if (pass1.equals(pass2)) {
					newUser.setPass(pass1);
					break;
				} else {
					System.out.println("パスワードが一致しません。");
				}

			} while (true);

			System.out.println("更新しますか？(1:更新、1以外:操作せず終了)");
			try {
				sentaku = Integer.parseInt(reader.readLine());
			} catch (NumberFormatException e) {
				sentaku = -1;
			}
			if (sentaku == 1) {
				uBL.update(user, newUser);
				System.out.println("更新しました。");


			} else {
				System.out.println("操作を中断しました。最初からやり直してください。");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (UserIsEmptyException e) {
			System.out.println("ユーザデータが存在しません。");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("システムエラーが発生しました。");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("システムエラーが発生しました。");
			e.printStackTrace();
		}

	}

	private void remove() {

		try {
			System.out.println("更新するユーザのIDを入力してください");

			System.out.print("ID：");

			// 検索
			UserBL uBL = new UserBL();
			User user = uBL.findById(reader.readLine());

			if (user == null) {
				System.out.println("該当データはありません。IDを変更して最初からやり直してください。");
				System.exit(0);

			}

			System.out.println("以下のデータを削除しますか？(1:削除、1以外:操作せず終了)");

			System.out.println("ID：" + user.getId());

			System.out.println("パスワード：******");

			System.out.println("名前：" + user.getName());

			try {
				sentaku = Integer.parseInt(reader.readLine());
			} catch (NumberFormatException e) {
				sentaku = -1;
			}
			if (sentaku == 1) {

				try {
					uBL.remove(user.getId());
					System.out.println("削除しました。");
				} catch (UserIsEmptyException e) {
					System.out.println("データが存在しません。");
					//e.printStackTrace();
				} catch (Exception e) {
					System.out.println("予期せぬエラーが発生しました。");
					e.printStackTrace();
				}


			} else {
				System.out.println("操作を中断しました。最初からやり直してください。");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			System.out.println("システムエラーが発生しました。");
			e1.printStackTrace();
		}

	}

}
