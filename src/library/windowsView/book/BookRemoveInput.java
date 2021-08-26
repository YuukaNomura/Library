package library.windowsView.book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import library.bl.BookBL;
import library.dto.Book;
import library.exception.BookIsEmptyException;

public class BookRemoveInput extends JFrame {

	public BookRemoveInput(Book book) {
		setTitle("蔵書管理システム");
		setBounds(100, 100, 450, 300);

		setLayout(null);

		JLabel Title = new JLabel("蔵書情報更新");
		Title.setBounds(10, 0, 150, 20);
		this.add(Title);

		JLabel ErrMsg = new JLabel("以下の蔵書情報を削除しますか？");
		ErrMsg.setBounds(10, 30, 400, 20);
		this.add(ErrMsg);


		String title = book.getTitle();
		String writer = book.getWriter();
		String publisher = book.getPublisher();
		String year = "";
		if(!(book.getYear() == -1)) {
			year = book.getYear() + "年";
		}
		String user = "";
		if(book.getUser()!=null) {
			if("removeUser".equals(book.getUser().getId())) {
				user = book.getUser().getName();
			}else {
				user = book.getUser().getId() + " : " + book.getUser().getName();
			}
		}

		JLabel labelTitle = new JLabel("タイトル");
		JLabel textTitle = new JLabel(title);
		labelTitle.setBounds(10, 60, 200, 30);
		textTitle.setBounds(100, 60, 200, 30);
		this.add(labelTitle);
		this.add(textTitle);

		JLabel labelWriter = new JLabel("著者");
		JLabel textWriter = new JLabel(writer);
		labelWriter.setBounds(10, 90, 200, 30);
		textWriter.setBounds(100, 90, 200, 30);
		this.add(labelWriter);
		this.add(textWriter);

		JLabel labelPublisher = new JLabel("出版社");
		JLabel textPublisher = new JLabel(publisher);
		labelPublisher.setBounds(10, 120, 200, 30);
		textPublisher.setBounds(100, 120, 200, 30);
		this.add(labelPublisher);
		this.add(textPublisher);

		JLabel labelYear = new JLabel("出版年");
		JLabel textYear = new JLabel(year);
		labelYear.setBounds(10, 150, 200, 30);
		textYear.setBounds(100, 150, 200, 30);
		this.add(labelYear);
		this.add(textYear);

		JLabel labelUser = new JLabel("所有者");
		JLabel textUser = new JLabel(user);
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

		JButton btnRemove = new JButton("削除");
		btnRemove.setBounds(100, 220, 100, 30);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookBL bBL = new BookBL();
				//Book book = null;

				try {
					//出版年が正しい値か確認
					//book = bBL.checkBook(textTitle.getText(), textWriter.getText(), textPublisher.getText(), textYear.getText());
					bBL.remove(book);
					ErrMsg.setText("削除が完了しました。");
					btnRemove.setVisible(false);
					btnNG.setVisible(false);
					btnOK.setVisible(true);
				}
				/*catch (BookTitleException e1) {
					ErrMsg.setText("タイトルは必須項目です。");
					e1.printStackTrace();
				} catch (BookWriterException e1) {
					e1.printStackTrace();
				} catch (BookPublisherException e1) {
					e1.printStackTrace();
				} catch (BookYearException e1) {
					ErrMsg.setText("出版年は4桁以下の正の数値で入力してください。");
					e1.printStackTrace();
				} */catch (BookIsEmptyException e1) {
					ErrMsg.setText("データが存在しません。");
					e1.printStackTrace();
				}catch(Exception e1) {
					ErrMsg.setText("エラーが発生しました。やり直してください。");
					e1.printStackTrace();

				}


//				int year = -1, err = 0;
//				List<Book> resultList = null;
//
//				resultList = new BookBL().findByConditionsPerfectMatching(
//						new Book(book.getTitle(), book.getWriter(), book.getPublisher(), book.getYear()));
//				if (resultList.isEmpty()) {
//					ErrMsg.setText("データが存在しません。最初からやり直してください。");
//
//					//br.setVisible(true);
//				} else {
//					new BookBL().remove(
//							new Book(book.getTitle(), book.getWriter(), book.getPublisher(), book.getYear()));
//					ErrMsg.setText("");
//					btnRemove.setVisible(false);
//				}
			}

		});

		this.add(btnRemove);
	}
//	public BookRemoveInput(Book book) {
//		setTitle("蔵書管理システム");
//		setBounds(100, 100, 450, 300);
//
//		setLayout(null);
//
//		JLabel Title = new JLabel("蔵書情報更新");
//		Title.setBounds(10, 0, 150, 20);
//		this.add(Title);
//
//		JLabel ErrMsg = new JLabel("以下の蔵書情報を削除しますか？");
//		ErrMsg.setBounds(10, 30, 400, 20);
//		this.add(ErrMsg);
//
//		JLabel labelTitle = new JLabel("タイトル");
//		JLabel textTitle = new JLabel(book.getTitle());
//		labelTitle.setBounds(10, 60, 200, 30);
//		textTitle.setBounds(100, 60, 200, 30);
//		this.add(labelTitle);
//		this.add(textTitle);
//
//		JLabel labelWriter = new JLabel("著者");
//		JLabel textWriter = new JLabel(book.getWriter());
//		labelWriter.setBounds(10, 90, 200, 30);
//		textWriter.setBounds(100, 90, 200, 30);
//		this.add(labelWriter);
//		this.add(textWriter);
//
//		JLabel labelPublisher = new JLabel("出版社");
//		JLabel textPublisher = new JLabel(book.getPublisher());
//		labelPublisher.setBounds(10, 120, 200, 30);
//		textPublisher.setBounds(100, 120, 200, 30);
//		this.add(labelPublisher);
//		this.add(textPublisher);
//
//		JLabel labelYear = new JLabel("出版年");
//		JLabel textYear = new JLabel(Integer.toString(book.getYear()));
//		labelYear.setBounds(10, 150, 200, 30);
//		textYear.setBounds(100, 150, 200, 30);
//		this.add(labelYear);
//		this.add(textYear);
//
//		JButton btnRemove = new JButton("削除");
//		btnRemove.setBounds(100, 200, 200, 30);
//		btnRemove.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//
//				int year = -1, err = 0;
//				List<Book> resultList = null;
//
//				resultList = new BookBL().findByConditionsPerfectMatching(
//						new Book(book.getTitle(), book.getWriter(), book.getPublisher(), book.getYear()));
//				if (resultList.isEmpty()) {
//					ErrMsg.setText("データが存在しません。最初からやり直してください。");
//
//					//br.setVisible(true);
//				} else {
//					new BookBL().remove(
//							new Book(book.getTitle(), book.getWriter(), book.getPublisher(), book.getYear()));
//					ErrMsg.setText("削除が完了しました。");
//					btnRemove.setVisible(false);
//				}
//			}
//
//		});
//
//		this.add(btnRemove);
//	}
}
