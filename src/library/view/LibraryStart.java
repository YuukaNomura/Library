package library.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import library.dto.User;

public class LibraryStart {

	public static void main(String[] args) {


		System.out.println("☆★☆---蔵書管理システムを起動します---☆★☆");

		try (InputStreamReader is = new InputStreamReader(System.in);
				BufferedReader reader = new BufferedReader(is);) {

			LoginView lv = new LoginView(is, reader);
			User user = lv.Login();

			OperationSelectView os = new OperationSelectView(is, reader, user);

			os.OperationSelection();


		} catch (IOException e) {
			e.printStackTrace();
		}

//		System.out.println("ID=" + user.getId());
//		System.out.println("pass=" + user.getPass());
//		System.out.println("user.getName()=" + user.getName());
//		System.out.println("sentaku1=" + sentaku1);

		System.out.println("☆★☆---蔵書管理システムを終了します---☆★☆");


	}

}
