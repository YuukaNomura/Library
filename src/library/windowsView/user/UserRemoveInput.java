package library.windowsView.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import library.bl.UserBL;
import library.exception.UserIsEmptyException;

public class UserRemoveInput extends JFrame {
	public UserRemoveInput(String id, String name) {

		setTitle("蔵書管理システム");
		setBounds(100, 100, 450, 300);

		setLayout(null);

		JLabel Title = new JLabel("ユーザ情報削除");
		Title.setBounds(10, 0, 150, 20);
		this.add(Title);

		JLabel ErrMsg = new JLabel("以下のユーザ情報を削除しますか？");
		ErrMsg.setBounds(10, 30, 400, 20);
		this.add(ErrMsg);

		JLabel labelSearchId = new JLabel("ID");
		JLabel labelSearchIdResult = new JLabel(id);
		JLabel labelSearchName = new JLabel("名前");
		JLabel labelSearchNameResult = new JLabel(name);
		labelSearchId.setBounds(10, 60, 200, 30);
		labelSearchIdResult.setBounds(100, 60, 200, 30);
		labelSearchName.setBounds(10, 90, 200, 30);
		labelSearchNameResult.setBounds(100, 90, 200, 30);

		this.add(labelSearchId);
		this.add(labelSearchIdResult);
		this.add(labelSearchName);
		this.add(labelSearchNameResult);

		JButton btnOK = new JButton("OK");
		btnOK.setBounds(100, 200, 100, 30);
		btnOK.setVisible(false);
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		this.add(btnOK);

		JButton btnNG = new JButton("キャンセル");
		btnNG.setBounds(220, 200, 100, 30);
		btnNG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		this.add(btnNG);

		JButton btnUpdate = new JButton("削除");
		btnUpdate.setBounds(100, 200, 100, 30);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserBL uBl = new UserBL();
				try {
					uBl.remove(id);
					ErrMsg.setText("削除が完了しました。");
//					btnUpdate.setVisible(false);
//					btnOK.setVisible(true);
				} catch (UserIsEmptyException e1) {
					ErrMsg.setText("データが存在しません。");
					e1.printStackTrace();
				} catch (Exception e1) {
					ErrMsg.setText("予期せぬエラーが発生しました。最初からやり直してください。");
					e1.printStackTrace();
				}finally{
					btnUpdate.setVisible(false);
					btnNG.setVisible(false);
					btnOK.setVisible(true);
				}


			}
		});

		this.add(btnUpdate);

	}

}
