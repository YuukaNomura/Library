package library.view.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserOperationView {

		private InputStreamReader is;
		private BufferedReader reader;

		private int sentaku;

		public UserOperationView(InputStreamReader is, BufferedReader reader) {
			this.is = is;
			this.reader = reader;

		}

		public int UserOperationSelect(){
			try {
				// 操作選択
				do {

					System.out.println("操作を選択してください");
					System.out.println("1: ユーザ登録\n2: ユーザ更新・削除");
					try {
						sentaku = Integer.parseInt(reader.readLine());
					} catch (NumberFormatException e) {
						sentaku = -1;
					}

					if (sentaku == 1){
						UserInsert ui = new UserInsert(is, reader);
						ui.Insert();

						break;

					}else if(sentaku == 2) {
						UserUpdate uu = new UserUpdate(is, reader);
						uu.UpdateUser();
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
