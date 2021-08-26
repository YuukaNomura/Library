package library.windowsView.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import library.bl.UserBL;
import library.dto.User;
import library.exception.UserIdException;
import library.exception.UserNameException;
import library.exception.UserPassException;
import library.exception.UserPassNoMatchException;
import library.exception.UserRegisteredException;
import library.util.ExceptionEnum;

public class UserInsert extends JFrame {
	public UserInsert() {
		setTitle("蔵書管理システム");
		setBounds(100, 100, 450, 300);

		setLayout(null);

		JLabel Title = new JLabel("ユーザ情報登録");
		Title.setBounds(10, 0, 150, 20);
		this.add(Title);

		JLabel ErrMsg = new JLabel("登録情報を入力してください。");
		ErrMsg.setBounds(10, 30, 400, 20);
		this.add(ErrMsg);

		JLabel labelId = new JLabel("ID (IDは変更できません)");
		JTextField textId = new JTextField();
		labelId.setBounds(10, 60, 200, 30);
		textId.setBounds(150, 60, 200, 30);
		this.add(labelId);
		this.add(textId);

		JLabel labelPass1 = new JLabel("パスワード");
		JPasswordField textPass1 = new JPasswordField();
		labelPass1.setBounds(10, 90, 200, 30);
		textPass1.setBounds(150, 90, 200, 30);
		this.add(labelPass1);
		this.add(textPass1);

		JLabel labelPass2 = new JLabel("パスワード（確認用）");
		JPasswordField textPass2 = new JPasswordField();
		labelPass2.setBounds(10, 120, 200, 30);
		textPass2.setBounds(150, 120, 200, 30);
		this.add(labelPass2);
		this.add(textPass2);

		JLabel labelName = new JLabel("名前");
		JTextField textName = new JTextField();
		labelName.setBounds(10, 150, 200, 30);
		textName.setBounds(150, 150, 200, 30);
		this.add(labelName);
		this.add(textName);

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

		JButton btnInsert = new JButton("登録");
		btnInsert.setBounds(100, 200, 100, 30);
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				UserBL uBl = new UserBL();
				User user = null;
				try {
					user = uBl.checkUser(textId.getText(), textPass1.getText(), textPass2.getText(),
							textName.getText());
					uBl.insert(user);
					ErrMsg.setText("登録完了しました。");
					btnInsert.setVisible(false);
					btnNG.setVisible(false);
					btnOK.setVisible(true);
				} catch (UserIdException e1) {
					if(ExceptionEnum.NullBlank.getErrMsg().equals(e1.getMessage())) {
						ErrMsg.setText("IDは必須項目です。");
					}else if(ExceptionEnum.LongLength.getErrMsg().equals(e1.getMessage()) || ExceptionEnum.FormatErr.getErrMsg().equals(e1.getMessage())){
						ErrMsg.setText("IDは10文字以内の半角英数で指定してください。");
					}else {
						ErrMsg.setText("IDの値に間違いがあります。");
					}
					e1.printStackTrace();
				} catch (UserRegisteredException e1) {
					ErrMsg.setText("すでに登録されているIDです。入力情報を確認してください。");
					e1.printStackTrace();
				} catch (UserPassException e1) {
					if(ExceptionEnum.NullBlank.getErrMsg().equals(e1.getMessage())) {
						ErrMsg.setText("パスワードは必須項目です。");
					}else if(ExceptionEnum.LongLength.getErrMsg().equals(e1.getMessage()) || ExceptionEnum.FormatErr.getErrMsg().equals(e1.getMessage())){
						ErrMsg.setText("パスワードは30文字以内の半角英数で指定してください。");
					}else {
						ErrMsg.setText("パスワードの値に間違いがあります。");
					}
					e1.printStackTrace();
				} catch (UserPassNoMatchException e1) {
					ErrMsg.setText("パスワードとパスワード（確認用）が一致しません。");
					e1.printStackTrace();
				} catch (UserNameException e1) {

					if(ExceptionEnum.NullBlank.getErrMsg().equals(e1.getMessage())) {
						ErrMsg.setText("名前は必須項目です。");
					}else if(ExceptionEnum.LongLength.getErrMsg().equals(e1.getMessage())){
						ErrMsg.setText("名前が文字数の上限を超えています。");
					}else {
						ErrMsg.setText("名前の値に間違いがあります。");
					}
					e1.printStackTrace();
				} catch (SQLException e1) {
					ErrMsg.setText("システムエラーが発生しました。");
					e1.printStackTrace();
				} catch (Exception e1) {
					ErrMsg.setText("システムエラーが発生しました。");
					e1.printStackTrace();
				}


			}
		});

		this.add(btnInsert);

	}

}
