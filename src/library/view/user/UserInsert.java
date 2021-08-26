package library.view.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import library.bl.UserBL;
import library.dto.User;
import library.exception.UserIdException;
import library.exception.UserNameException;
import library.exception.UserPassException;
import library.exception.UserPassNoMatchException;
import library.exception.UserRegisteredException;

public class UserInsert {
	private InputStreamReader is;
	private BufferedReader reader;
	private int kakutei = -1;
	private User newUser = null;;

	public UserInsert(InputStreamReader is, BufferedReader reader) {
		this.is = is;
		this.reader = reader;

	}

	public void Insert() {
		UserBL uBL = new UserBL();
		newUser = new User();

		do {
			try {
				System.out.println("登録情報を入力してください（空欄不可）");

				do {
					System.out.print("Id：");

					String ID = reader.readLine();

					if (ID.isEmpty()) {
						System.out.println("入力してください。");
						continue;
					}
					if (ID.length() > 10) {
						System.out.println("10文字以下の文字列を入力してください。");
						continue;
					}

					User user = uBL.findById(ID);
					if (user != null) {
						System.out.println("このIDは使用されています。別のIDを入力してください。");
						continue;
					}

					newUser.setId(ID);
					break;

				} while (true);

				do {
					System.out.print("登録名：");
					newUser.setName(reader.readLine());
					if (newUser.getName().isEmpty()) {
						System.out.println("入力してください。");
					} else {
						break;
					}
				} while (true);

				do {
					System.out.print("パスワード：");
					String pass1 = reader.readLine();

					System.out.print("パスワード（確認用）：");
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

				System.out.println("以下の情報で登録します。(1:登録、1以外:やり直す)");
				System.out.println("ID：" + newUser.getId());
				System.out.println("登録名：" + newUser.getName());
				System.out.println("パスワード：" + "********");

				kakutei = Integer.parseInt(reader.readLine());

			} catch (NumberFormatException e) {
				kakutei = -1;
			}

			catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

			if (kakutei == 1) {
				UserBL uBl = new UserBL();
				try {
					uBl.insert(newUser);
					break;
				} catch (UserRegisteredException e) {
					System.out.println("すでに登録されているIDです。入力情報を確認してください。");
					//e.printStackTrace();
				} catch (UserIdException e) {
					System.out.println("IDの値が不正です。入力情報を確認してください。");
					//e.printStackTrace();
				} catch (UserPassException e) {
					System.out.println("パスワードの値が不正です。入力情報を確認してください。");
					//e.printStackTrace();
				} catch (UserPassNoMatchException e) {
					System.out.println("パスワードが一致しません。入力情報を確認してください。");
					//e.printStackTrace();
				} catch (UserNameException e) {
					System.out.println("登録名が不正です。入力情報を確認してください。");
					//e.printStackTrace();
				} catch (SQLException e) {
					System.out.println("システムエラーが発生しました。");
					e.printStackTrace();
				} catch (Exception e) {
					System.out.println("システムエラーが発生しました。");
					e.printStackTrace();
				}


			}
		} while (true);

		System.out.println("登録しました。");
	}

}
