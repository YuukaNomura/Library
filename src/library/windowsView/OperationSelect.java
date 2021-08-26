package library.windowsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import library.dto.User;
import library.windowsView.book.BookOperation;
import library.windowsView.user.UserOperation;

public class OperationSelect extends JFrame{

	public OperationSelect(User LoginUser) {
		//ユーザーがこのフレームの「クローズ」を開始したときにプログラムを終了する。
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setTitle("蔵書管理システム");
		setBounds(100, 100, 450, 300);

		setLayout(null);

	    JLabel Title = new JLabel("蔵書管理システムに " + LoginUser.getName() + " さんとしてログインしました。");
	    Title.setBounds(10, 0, 450, 20);
		this.add(Title);

	    JLabel Select = new JLabel("操作を選択してください。");
	    Select.setBounds(10, 30, 200, 20);
		this.add(Select);

		JButton btnBook= new JButton("蔵書管理");
		btnBook.setBounds(30, 70, 200, 30);
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookOperation bo = new BookOperation(LoginUser);
				bo.setVisible(true);
			}
		});

	    this.add(btnBook);

		JButton btnUser= new JButton("ユーザ操作");
		btnUser.setBounds(30, 110, 200, 30);
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserOperation uo = new UserOperation();
				uo.setVisible(true);
			}
		});

	    this.add(btnUser);

		JButton btnExit= new JButton("終了");
		btnExit.setBounds(30, 150, 200, 30);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

				//プログラム終了
				System.exit(0);
			}
		});

	    this.add(btnExit);

	}

}
