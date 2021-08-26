package library.windowsView.book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import library.dto.Book;
import library.dto.User;

public class BookSearchResult extends JFrame {

	private String[] columnNames = {"タイトル", "著者名", "出版社", "出版年（年）"};

	public BookSearchResult(User loginUser, List<Book> result) {
		setTitle("蔵書管理システム");
		setBounds(100, 100, 450, 300);
		setLayout(null);

		JLabel Title = new JLabel("蔵書管理システム");
		Title.setBounds(10, 0, 150, 20);
		this.add(Title);

		JLabel ErrMsg = new JLabel("蔵書データを選択し、操作を選択してください。");
		ErrMsg.setBounds(10, 30, 400, 20);
		this.add(ErrMsg);

		Object[][] obj = new Object[result.size()][4];
		Map<Integer, Book> BookMap = new HashMap<>();

		int i = 0;
		for (Book book : result) {
			BookMap.put(i, book);

			obj[i][0] = book.getTitle();
			obj[i][1] = book.getWriter();
			obj[i][2] = book.getPublisher();
			if(book.getYear() == -1) {
				obj[i][3] = "";
			}else {
				obj[i][3] = book.getYear();
			}
			i++;
		}

		JTable table = new JTable(obj, columnNames);
		//複数行の選択を禁止
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(table);

		scrollPane.setBounds(0, 50, 450, 160);
		this.add(scrollPane);

		JButton btnInfo = new JButton("詳細");
		btnInfo.setBounds(220, 220, 100, 30);
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedColumns().length == 0) {
					ErrMsg.setText("データを選択してください。");
				} else {
					BookInfo bif = new BookInfo(loginUser, BookMap.get(table.getSelectedRow()));
					bif.setVisible(true);
					setVisible(false);
				}

			}
		});

		this.add(btnInfo);

		JButton btnOK = new JButton("OK");
		btnOK.setBounds(100, 220, 100, 30);
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		this.add(btnOK);

	}

}
