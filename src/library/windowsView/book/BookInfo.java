package library.windowsView.book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import library.dto.Book;
import library.dto.User;

public class BookInfo  extends JFrame{
	public BookInfo(User loginUser, Book book) {
		setTitle("蔵書管理システム");
		setBounds(100, 100, 450, 300);

		setLayout(null);

		JLabel Title = new JLabel("蔵書情報更新");
		Title.setBounds(10, 0, 150, 20);
		this.add(Title);

		JLabel ErrMsg = new JLabel("詳細情報です。操作を選択してください。");
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
		btnOK.setBounds(30, 220, 100, 30);
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		this.add(btnOK);

		JButton btnUpdate = new JButton("更新");
		btnUpdate.setBounds(150, 220, 100, 30);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookUpdateInput bui = new BookUpdateInput(loginUser, book);
					bui.setVisible(true);
					setVisible(false);

			}
		});

		this.add(btnUpdate);

		JButton btnRemove = new JButton("削除");
		btnRemove.setBounds(270, 220, 100, 30);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


					//String year = table.getValueAt(table.getSelectionModel().getLeadSelectionIndex(), 3).toString();

					BookRemoveInput bri = new BookRemoveInput(book);
//					BookRemoveInput bri = new BookRemoveInput(
//							new Book((String) table.getValueAt(table.getSelectionModel().getLeadSelectionIndex(), 0),
//									(String) table.getValueAt(table.getSelectionModel().getLeadSelectionIndex(), 1),
//									(String) table.getValueAt(table.getSelectionModel().getLeadSelectionIndex(), 2),
//									Integer.parseInt(year)));

					bri.setVisible(true);
					setVisible(false);


			}
		});

		this.add(btnRemove);


	}

}
