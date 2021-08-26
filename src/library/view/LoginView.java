package library.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import library.bl.UserBL;
import library.dto.User;
import library.exception.UserLoginException;

public class LoginView {
	private InputStreamReader is;
	private BufferedReader reader;

	private String id = "", pass = "";
	private User user;

	public LoginView(InputStreamReader is, BufferedReader reader) {
		this.is = is;
		this.reader = reader;

	}

	public User Login() {

		try {
			// ログイン


			for (int flag = 1;; flag++) {

				System.out.println("☆★☆ログイン☆★☆");

				System.out.print("IDを入力してください\nID : ");
				id = reader.readLine();

				System.out.print("パスワードを入力してください\nパスワード : ");
				pass = reader.readLine();
				try {
					user = new UserBL().login(id, pass);
					break;

				} catch (UserLoginException e) {
					if (flag >= 3) {
						System.out.println("ログインに3回失敗しました。\n最初からやり直してください。");
						System.exit(0);
					}else{
						System.out.println("ログインに失敗しました。\nもう一度入力してください");
					}
					//e.printStackTrace();
				}catch(Exception e) {
					System.out.println("システムエラーが発生しました。");
					System.exit(0);
				}


//				user = new Login().loginLibrary(id, pass);
//
//				if (user != null) {
//					break;
//				} else {
//
//					if (flag > 3) {
//						System.out.println("ログインに3回失敗しました。\n最初からやり直してください。");
//						System.exit(0);
//					}else{
//						System.out.println("ログインに失敗しました。\nもう一度入力してください");
//					}
//				}
			}



		} catch (IOException e) {
			e.printStackTrace();
		}
		return user;

	}

}
