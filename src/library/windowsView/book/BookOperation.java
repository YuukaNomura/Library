package library.windowsView.book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import library.dto.User;

public class BookOperation extends JFrame{
	public BookOperation(User loginUser) {
		setTitle("蔵書管理システム");
		setBounds(100, 100, 450, 300);

		setLayout(null);

	    JLabel Title = new JLabel("操作選択");
	    Title.setBounds(10, 0, 150, 20);
		this.add(Title);

	    JLabel Select = new JLabel("操作を選択してください。");
	    Select.setBounds(10, 30, 200, 20);
		this.add(Select);

		JButton btnSearch= new JButton("蔵書検索");
		btnSearch.setBounds(30, 70, 200, 30);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookSearch bs = new BookSearch(loginUser);
				bs.setVisible(true);
				setVisible(false);
			}
		});

	    this.add(btnSearch);


		JButton btnInsert= new JButton("蔵書登録");
		btnInsert.setBounds(30, 110, 200, 30);
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookInsert bi = new BookInsert(loginUser);
				bi.setVisible(true);
				setVisible(false);
			}
		});

	    this.add(btnInsert);

		JButton btnExit= new JButton("キャンセル");
		btnExit.setBounds(30, 150, 200, 30);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//BookUpdate bu = new BookUpdate();
				//bu.setVisible(true);
				setVisible(false);
			}
		});

	    this.add(btnExit);

	}
}
