package library.windowsView.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import library.bl.UserBL;
import library.dto.User;
import library.exception.UserIdException;
import library.exception.UserIsEmptyException;
import library.exception.UserLoginException;
import library.exception.UserNameException;
import library.exception.UserPassException;
import library.exception.UserPassNoMatchException;
import library.util.ExceptionEnum;

public class UserUpdateInput extends JFrame {
	public UserUpdateInput(String id, String name) {
		setTitle("蔵書管理システム");
		setBounds(100, 100, 450, 300);

		setLayout(null);

		JLabel Title = new JLabel("ユーザ情報更新");
		Title.setBounds(10, 0, 150, 20);
		this.add(Title);

		JLabel ErrMsg = new JLabel("登録情報を入力してください。");
		ErrMsg.setBounds(10, 30, 400, 20);
		this.add(ErrMsg);

		JLabel labelId = new JLabel("ID");
		JLabel textId = new JLabel(id);
		labelId.setBounds(10, 60, 200, 30);
		textId.setBounds(180, 60, 200, 30);
		this.add(labelId);
		this.add(textId);

		JLabel labelPass = new JLabel("現在のパスワード");
		JPasswordField textPass = new JPasswordField();
		labelPass.setBounds(10, 90, 200, 30);
		textPass.setBounds(180, 90, 200, 30);
		this.add(labelPass);
		this.add(textPass);

		JLabel labelNewPass1 = new JLabel("新しいパスワード");
		JPasswordField textNewPass1 = new JPasswordField();
		labelNewPass1.setBounds(10, 120, 200, 30);
		textNewPass1.setBounds(180, 120, 200, 30);
		this.add(labelNewPass1);
		this.add(textNewPass1);

		JLabel labelNewPass2 = new JLabel("新しいパスワード（確認用）");
		JPasswordField textNewPass2 = new JPasswordField();
		labelNewPass2.setBounds(10, 150, 200, 30);
		textNewPass2.setBounds(180, 150, 200, 30);
		this.add(labelNewPass2);
		this.add(textNewPass2);

		JLabel labelName = new JLabel("名前");
		JTextField textName = new JTextField(name);
		labelName.setBounds(10, 180, 200, 30);
		textName.setBounds(180, 180, 200, 30);
		this.add(labelName);
		this.add(textName);

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

		JButton btnUpdate = new JButton("登録");
		btnUpdate.setBounds(100, 220, 100, 30);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				UserBL uBl = new UserBL();
				User oldUser = null, newUser = null;
				try {
					oldUser = uBl.login(id, textPass.getText());
					oldUser.setPass(textPass.getText());
					newUser = uBl.checkUser(textId.getText(), textNewPass1.getText(), textNewPass2.getText(),
							textName.getText());
					uBl.update(oldUser, newUser);
					ErrMsg.setText("登録完了しました。");
					btnUpdate.setVisible(false);
					btnNG.setVisible(false);
					btnOK.setVisible(true);
				} catch (UserIdException e1) {
					//ErrMsg.setText("IDは必須項目です。");
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
				} catch (UserLoginException e1) {
					ErrMsg.setText("現在のパスワードが違います。");
					e1.printStackTrace();
				} catch (UserIsEmptyException e1) {
					ErrMsg.setText("予期せぬエラーが発生しました。最初からやり直してください。");
					btnUpdate.setVisible(false);
					btnOK.setVisible(true);
					e1.printStackTrace();
				} catch (Exception e1) {
					ErrMsg.setText("システムエラーが発生しました。");
					e1.printStackTrace();
				}
			}
		});

		this.add(btnUpdate);
	}

}
