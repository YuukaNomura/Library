package library.windowsView.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class UserOperation extends JFrame{
	public UserOperation() {
		setTitle("蔵書管理システム");
		setBounds(100, 100, 450, 300);

		setLayout(null);

	    JLabel Title = new JLabel("操作選択");
	    Title.setBounds(10, 0, 150, 20);
		this.add(Title);

	    JLabel Select = new JLabel("操作を選択してください。");
	    Select.setBounds(10, 30, 200, 20);
		this.add(Select);

		JButton btnInsert= new JButton("ユーザ登録");
		btnInsert.setBounds(30, 70, 200, 30);
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserInsert bi = new UserInsert();
				bi.setVisible(true);
				setVisible(false);
			}
		});

	    this.add(btnInsert);

		JButton btnUpdate= new JButton("ユーザ情報更新・削除");
		btnUpdate.setBounds(30, 110, 200, 30);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserSearch us = new UserSearch();
				us.setVisible(true);
				setVisible(false);
			}
		});

	    this.add(btnUpdate);


		JButton btnExit= new JButton("キャンセル");
		btnExit.setBounds(30, 150, 200, 30);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

	    this.add(btnExit);
	}

}
