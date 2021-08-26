package library.view.book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import library.bl.BookBL;
import library.bl.UserBL;
import library.dto.Book;
import library.dto.User;
import library.exception.BookIsEmptyException;
import library.exception.BookRegisteredException;
import library.exception.BookYearException;

public class BookUpdate {

	private InputStreamReader is;
	private BufferedReader reader;
	private Book updateBook = null;

	private int sentaku = -1, kakutei = -1;

	private User user;

	public BookUpdate(InputStreamReader is, BufferedReader reader, User user) {
		this.is = is;
		this.reader = reader;
		this.user = user;

	}

	public void UpdateBook() {
		do {
			try {
				System.out.println("操作を選択してください\n1: 新規登録\n2: 情報更新・削除");
				sentaku = Integer.parseInt(reader.readLine());

				if (sentaku == 1) {
					insert();
					break;
				} else if (sentaku == 2) {
					update();
					break;
				}

			} catch (NumberFormatException e) {
				kakutei = -1;
			}

			catch (IOException e) {
				e.printStackTrace();
			}


		} while (true);
	}

	private void insert() {

		do {
			try {
				System.out.println("登録情報を入力してください（空欄可）");

				System.out.print("タイトル：");
				updateBook = new Book();

				updateBook.setTitle(reader.readLine());

				System.out.print("著者：");
				updateBook.setWriter(reader.readLine());

				System.out.print("出版社：");
				updateBook.setPublisher(reader.readLine());

				do {
					try {
						System.out.print("出版年：");
						int year = Integer.parseInt(reader.readLine());
						if(String.valueOf(year).length() < 5) {
							updateBook.setYear(year);
							break;
						}else {
							System.out.println("4桁以下の数字を入力してください。");
						}
					}catch(NumberFormatException e) {
						updateBook.setYear(-1);
						break;
					}

				}while(true);


				System.out.println("以下の情報で登録します。(1:登録、1以外:やり直す)");
				System.out.println("タイトル：" + updateBook.getTitle());
				System.out.println("著者：" + updateBook.getWriter());
				System.out.println("出版社：" + updateBook.getPublisher());
				System.out.println("出版年：" + updateBook.getYear());

				kakutei = Integer.parseInt(reader.readLine());

			} catch (NumberFormatException e) {
				kakutei = -1;
			}

			catch (IOException e) {
				e.printStackTrace();
			}

			if (kakutei == 1) {
				BookBL bBl = new BookBL();
				try {
					bBl.insert(updateBook, user.getId());
				} catch (BookRegisteredException e) {

					System.out.println("すでに登録されています。入力情報を確認してください。");
					//e.printStackTrace();
				} catch (SQLException e) {
					System.out.println("システムエラーが発生しました。");
					e.printStackTrace();
				}
				break;

			}
		} while (true);
		System.out.println("登録しました。");

	}

	private void update() {


			try {
				System.out.println("操作する蔵書の情報を入力してください（空欄可）");

				updateBook = new Book();
				System.out.print("タイトル：");
				updateBook.setTitle(reader.readLine());

				System.out.print("著者：");
				updateBook.setWriter(reader.readLine());

				System.out.print("出版社：");
				updateBook.setPublisher(reader.readLine());

				do {
					try {
						System.out.print("出版年：");
						String year = reader.readLine();
						try {
							new BookBL().checkYear(year);
							updateBook.setYear(Integer.parseInt(year));
							break;
						} catch (BookYearException e) {
							System.out.println("4桁以下の数字を入力してください。");
							//e.print
						}
//						int year = Integer.parseInt(reader.readLine());
//						if(String.valueOf(year).length() < 5) {
//
//							break;
//						}else {
//							System.out.println("4桁以下の数字を入力してください。");
//						}
					}catch(NumberFormatException e) {
						updateBook.setYear(-1);
						break;
					}

				}while(true);


				// 検索
				BookBL bBl = new BookBL();
				List<Book> resultBookList = bBl.findByConditions(updateBook);

				if (resultBookList.isEmpty()) {
					System.out.println("該当データはありません。検索条件を変更して最初からやり直してみてください。");

				} else {

					System.out.println("以下のデータから操作するデータの番号を選択してください。");
					int i = 1;
					Map<Integer, Book> BookMap = new HashMap<>();

					System.out.println("タイトル : 著者 : 出版社 : 出版年");
					for (Book resultBook : resultBookList) {
						BookMap.put(i, resultBook);
						System.out.println(i +" : " +  resultBook.getTitle() + " : "
								+ resultBook.getWriter() + " : "
								+ resultBook.getPublisher() + " : "
								+ resultBook.getYear());
						i++;
					}
					do {
						try {
							updateBook = BookMap.get(Integer.parseInt(reader
									.readLine()));
							if (updateBook != null) {
								break;
							}
						} catch (NumberFormatException e) {

						}
						System.out.println("一覧に存在する番号を選択してください。");
					} while (true);

					do {
						try {
							System.out
									.println("以下のデータに対して行う操作を選択してください。(1:更新、2:削除)");
							System.out.println("タイトル：" + updateBook.getTitle());
							System.out.println("著者：" + updateBook.getWriter());
							System.out.println("出版社："
									+ updateBook.getPublisher());
							System.out.println("出版年："
									+ updateBook.getYear());
							System.out.println("所有者："
									+ updateBook.getUser().getId() + " : " + updateBook.getUser().getName());
							sentaku = Integer.parseInt(reader.readLine());

						} catch (NumberFormatException e) {
							sentaku = -1;
						}
						if (sentaku == 1) {

							Book newBook = new Book();

							System.out.println("データを入力してください。");
							System.out.print("タイトル：");
							newBook.setTitle(reader.readLine());

							System.out.print("著者：");
							newBook.setWriter(reader.readLine());

							System.out.print("出版社：");
							newBook.setPublisher(reader.readLine());

							do {
								try {
									System.out.print("出版年：");
									String year = reader.readLine();
									try {
										bBl.checkYear(year);
										newBook.setYear(Integer.parseInt(year));
										break;
									} catch (BookYearException e) {
										System.out.println("4桁以下の数字を入力してください。");
										//e.printStackTrace();
									}
//									int year = Integer.parseInt(reader.readLine());
//									if(String.valueOf(year).length() < 5) {
//										newBook.setYear(year);
//										break;
//									}else {
//										System.out.println("4桁以下の数字を入力してください。");
//									}
								}catch(NumberFormatException e) {
									newBook.setYear(-1);
									break;
								}

							}while(true);

							do {
									System.out.print("所有者のID：");
									String userId = reader.readLine();
									if((userId == null) || ("".contentEquals(userId))) {
										newBook.setUser(user);
										break;
									}else {
										UserBL uBL = new UserBL();
										if((uBL.findById(userId) == null)){
											System.out.println("ユーザが存在しません。");
											//throw new Exception("ユーザが存在しません");
										}else {
											newBook.setUser(uBL.findById(userId));
											break;
										}
									}
							}while(true);

							System.out.println("更新しますか？(1:更新、1以外:操作せず終了)");
							try{
							sentaku = Integer.parseInt(reader.readLine());
							}catch (NumberFormatException e) {
								sentaku = -1;
							}
							if(sentaku == 1){
								try {
									bBl.update(updateBook, newBook);
									System.out.println("更新しました。");
								} catch (BookRegisteredException e) {
									System.out.println("すでに登録されています。入力情報を確認してください。");
									//e.printStackTrace();
								}

							}else{
								System.out.println("操作を中断しました。最初からやり直してください。");
							}

							break;

						} else if (sentaku == 2) {
							System.out.println("削除しますか？(1:削除、1以外:操作せず終了)");
							try{
							sentaku = Integer.parseInt(reader.readLine());
							}catch (NumberFormatException e) {
								sentaku = -1;
							}
							if(sentaku == 1){
								try {
									bBl.remove(updateBook);
									System.out.println("削除しました。");
								} catch (BookIsEmptyException e) {
									System.out.println("データが存在しません。");
									//e.printStackTrace();
								}


							}else{
								System.out.println("最初からやり直してください。");

							}

							break;
						}
					} while (true);
				}

			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e1) {
				System.out.println("システムエラーが発生しました。");
				e1.printStackTrace();
			}

	}

}
