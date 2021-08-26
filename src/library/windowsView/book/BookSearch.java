package library.windowsView.book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import library.bl.BookBL;
import library.dto.Book;
import library.dto.User;
import library.exception.BookYearException;

public class BookSearch extends JFrame {
	public BookSearch(User loginUser) {
		setTitle("蔵書管理システム");
		setBounds(100, 100, 450, 300);

		setLayout(null);

		JLabel Title = new JLabel("蔵書検索");
		Title.setBounds(10, 0, 150, 20);
		this.add(Title);

		JLabel ErrMsg = new JLabel("検索条件を入力してください。");
		ErrMsg.setBounds(10, 30, 400, 20);
		this.add(ErrMsg);

		JLabel labelTitle = new JLabel("タイトル");
		JTextField textTitle = new JTextField();
		labelTitle.setBounds(10, 60, 200, 30);
		textTitle.setBounds(100, 60, 200, 30);
		this.add(labelTitle);
		this.add(textTitle);

		JLabel labelWriter = new JLabel("著者");
		JTextField textWriter = new JTextField();
		labelWriter.setBounds(10, 90, 200, 30);
		textWriter.setBounds(100, 90, 200, 30);
		this.add(labelWriter);
		this.add(textWriter);

		JLabel labelPublisher = new JLabel("出版社");
		JTextField textPublisher = new JTextField();
		labelPublisher.setBounds(10, 120, 200, 30);
		textPublisher.setBounds(100, 120, 200, 30);
		this.add(labelPublisher);
		this.add(textPublisher);

		JLabel labelYear = new JLabel("出版年");
		JTextField textYear = new JTextField();
		labelYear.setBounds(10, 150, 200, 30);
		textYear.setBounds(100, 150, 200, 30);
		this.add(labelYear);
		this.add(textYear);

		JButton btnNG = new JButton("キャンセル");
		btnNG.setBounds(220, 200, 100, 30);
		btnNG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		this.add(btnNG);


		JButton btnSearch = new JButton("検索");
		btnSearch.setBounds(100, 200, 100, 30);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				BookBL bBL = new BookBL();
				List<Book> resultList = null;

				try {
					//出版年が正しい値か確認
					bBL.checkYear(textYear.getText());

					resultList = bBL.findByConditions(
							bBL.createBook(textTitle.getText(), textWriter.getText(), textPublisher.getText(),
									textYear.getText()));

					if (!resultList.isEmpty()) {
						BookSearchResult br = new BookSearchResult(loginUser, resultList);
						br.setVisible(true);
						//setVisible(false);
					} else {
						ErrMsg.setText("検索結果は0件です。条件を変更してください。");
					}
				} catch (BookYearException e1) {
					ErrMsg.setText("出版年は4桁以下の正の数値で入力してください。");
					e1.printStackTrace();
				} catch (Exception e1) {
					ErrMsg.setText("システムエラーが発生しました。");
					e1.printStackTrace();
				}


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
//					resultList = new BookBL().findByConditions(
//							new Book(textTitle.getText(), textWriter.getText(), textPublisher.getText(), year));
//					if (!resultList.isEmpty()) {
//						BookSearchResult br = new BookSearchResult(resultList);
//						setVisible(false);
//					} else {
//						ErrMsg.setText("検索結果は0件です。条件を変更してください。");
//					}
//				}

			}
		});

		this.add(btnSearch);

	}
}
