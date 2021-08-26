package library.windowsView.book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import library.bl.BookBL;
import library.bl.UserBL;
import library.dto.Book;
import library.dto.User;
import library.exception.BookPublisherException;
import library.exception.BookRegisteredException;
import library.exception.BookTitleException;
import library.exception.BookWriterException;
import library.exception.BookYearException;
import library.util.CheckUtil;
import library.util.ExceptionEnum;

public class BookUpdateInput extends JFrame {

	public BookUpdateInput(User loginUser, Book oldBook) {
		setTitle("蔵書管理システム");
		setBounds(100, 100, 450, 300);

		setLayout(null);

		JLabel Title = new JLabel("蔵書情報更新");
		Title.setBounds(10, 0, 150, 20);
		this.add(Title);

		JLabel ErrMsg = new JLabel("更新情報を入力してください。");
		ErrMsg.setBounds(10, 30, 400, 20);
		this.add(ErrMsg);


		String title = oldBook.getTitle();
		String writer = oldBook.getWriter();
		String publisher = oldBook.getPublisher();
		String year = "";
		if(!(oldBook.getYear() == -1)) {
			year += oldBook.getYear();
		}
		String userId = "";
		if(oldBook.getUser()!=null) {
			if(!("removeUser".equals(oldBook.getUser().getId()))) {
				userId = oldBook.getUser().getId();
			}
		}

		JLabel labelTitle = new JLabel("タイトル");
		JTextField textTitle = new JTextField(title);
		labelTitle.setBounds(10, 60, 200, 30);
		textTitle.setBounds(100, 60, 200, 30);
		this.add(labelTitle);
		this.add(textTitle);

		JLabel labelWriter = new JLabel("著者");
		JTextField textWriter = new JTextField(writer);
		labelWriter.setBounds(10, 90, 200, 30);
		textWriter.setBounds(100, 90, 200, 30);
		this.add(labelWriter);
		this.add(textWriter);

		JLabel labelPublisher = new JLabel("出版社");
		JTextField textPublisher = new JTextField(publisher);
		labelPublisher.setBounds(10, 120, 200, 30);
		textPublisher.setBounds(100, 120, 200, 30);
		this.add(labelPublisher);
		this.add(textPublisher);

		JLabel labelYear = new JLabel("出版年");
		JTextField textYear = new JTextField(year);
		labelYear.setBounds(10, 150, 200, 30);
		textYear.setBounds(100, 150, 200, 30);
		this.add(labelYear);
		this.add(textYear);

		JLabel labelUser = new JLabel("所有者（ID）");
		JTextField textUser = new JTextField(userId);
		labelUser.setBounds(10, 180, 200, 30);
		textUser.setBounds(100, 180, 200, 30);
		this.add(labelUser);
		this.add(textUser);

		JButton btnOK = new JButton("OK");
		btnOK.setBounds(100, 220, 100, 30);
		btnOK.setVisible(false);
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		this.add(btnOK);

		JButton btnNG = new JButton("キャンセル");
		btnNG.setBounds(220, 220, 100, 30);
		btnNG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		this.add(btnNG);

		JButton btnUpdate = new JButton("更新");
		btnUpdate.setBounds(100, 220, 100, 30);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookBL bBL = new BookBL();
				UserBL uBL = new UserBL();
				Book newBook = null;
				User bookUser = loginUser;
				try {
					//出版年が正しい値か確認
					newBook = bBL.checkBook(textTitle.getText(), textWriter.getText(), textPublisher.getText(), textYear.getText());

					if(CheckUtil.checkNullBlank(textUser.getText())) {
						bookUser = uBL.findById(textUser.getText());
						if(bookUser == null) {
							ErrMsg.setText("ユーザが存在しません。");
							throw new Exception("ユーザが存在しません");
						}
					}
					newBook.setUser(bookUser);

					bBL.update(oldBook, newBook);

					ErrMsg.setText("登録完了しました。");
					btnUpdate.setVisible(false);
					btnNG.setVisible(false);
					btnOK.setVisible(true);
				}catch(BookTitleException e1) {
					if(ExceptionEnum.NullBlank.getErrMsg().equals(e1.getMessage())) {
						ErrMsg.setText("タイトルは必須項目です。");
					}else if(ExceptionEnum.LongLength.getErrMsg().equals(e1.getMessage())){
						ErrMsg.setText("タイトルが文字数の上限を超えています。");
					}
					e1.printStackTrace();
				} catch (BookWriterException e1) {
					if(ExceptionEnum.LongLength.getErrMsg().equals(e1.getMessage())){
						ErrMsg.setText("著者名が文字数の上限を超えています。");
					}
					e1.printStackTrace();
				} catch (BookPublisherException e1) {
					if(ExceptionEnum.LongLength.getErrMsg().equals(e1.getMessage())){
						ErrMsg.setText("出版社名が文字数の上限を超えています。");
					}
					e1.printStackTrace();
				} catch (BookYearException e1) {
					ErrMsg.setText("出版年は4桁以下の数値で入力してください。");
					e1.printStackTrace();
				} catch (BookRegisteredException e1) {
					ErrMsg.setText("すでに登録されています。入力情報を確認してください。");
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}


			}
		});

		this.add(btnUpdate);
	}

//	public BookUpdateInput(Book book) {
//		setTitle("蔵書管理システム");
//		setBounds(100, 100, 450, 300);
//
//		setLayout(null);
//
//		JLabel Title = new JLabel("蔵書情報更新");
//		Title.setBounds(10, 0, 150, 20);
//		this.add(Title);
//
//		JLabel ErrMsg = new JLabel("更新情報を入力してください。");
//		ErrMsg.setBounds(10, 30, 400, 20);
//		this.add(ErrMsg);
//
//		JLabel labelTitle = new JLabel("タイトル");
//		JTextField textTitle = new JTextField(book.getTitle());
//		labelTitle.setBounds(10, 60, 200, 30);
//		textTitle.setBounds(100, 60, 200, 30);
//		this.add(labelTitle);
//		this.add(textTitle);
//
//		JLabel labelWriter = new JLabel("著者");
//		JTextField textWriter = new JTextField(book.getWriter());
//		labelWriter.setBounds(10, 90, 200, 30);
//		textWriter.setBounds(100, 90, 200, 30);
//		this.add(labelWriter);
//		this.add(textWriter);
//
//		JLabel labelPublisher = new JLabel("出版社");
//		JTextField textPublisher = new JTextField(book.getPublisher());
//		labelPublisher.setBounds(10, 120, 200, 30);
//		textPublisher.setBounds(100, 120, 200, 30);
//		this.add(labelPublisher);
//		this.add(textPublisher);
//
//		JLabel labelYear = new JLabel("出版年");
//		JTextField textYear = new JTextField(Integer.toString(book.getYear()));
//		labelYear.setBounds(10, 150, 200, 30);
//		textYear.setBounds(100, 150, 200, 30);
//		this.add(labelYear);
//		this.add(textYear);
//
//		JButton btnUpdate = new JButton("更新");
//		btnUpdate.setBounds(100, 200, 200, 30);
//		btnUpdate.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//
//				int year = -1, err = 0;
//				List<Book> resultList = null;
//
//				if (!textYear.getText().isEmpty()) {
//
//					try {
//						year = Integer.parseInt(textYear.getText());
//
//						if (String.valueOf(year).length() < 5) {
//							year = Integer.parseInt(textYear.getText());
//
//						} else {
//							ErrMsg.setText("出版年は4桁以下の数値で入力してください。");
//							err = 1;
//						}
//					} catch (NumberFormatException e1) {
//						ErrMsg.setText("出版年は4桁以下の数値で入力してください。");
//						err = 1;
//					}
//				}
//
//				if (err == 0) {
//					resultList = new BookBL().findByConditionsPerfectMatching(
//							new Book(textTitle.getText(), textWriter.getText(), textPublisher.getText(), year));
//					if (!resultList.isEmpty()) {
//						ErrMsg.setText("すでに登録されています。入力情報を確認してください。");
//
//						//br.setVisible(true);
//					} else {
//						new BookBL().update(
//								book, new Book(textTitle.getText(), textWriter.getText(), textPublisher.getText(), year));
//						ErrMsg.setText("登録完了しました。");
//						btnUpdate.setVisible(false);
//					}
//				}
//
//			}
//		});
//
//		this.add(btnUpdate);
//	}

}
