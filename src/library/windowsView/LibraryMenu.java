package library.windowsView;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import library.bl.UserBL;
import library.dto.User;
import library.exception.UserLoginException;

public class LibraryMenu extends JFrame{

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibraryMenu frame = new LibraryMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public LibraryMenu() {
		//ユーザーがこのフレームの「クローズ」を開始したときにプログラムを終了する。
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//タイトルと位置、サイズの指定
		setTitle("蔵書管理システム");
		setBounds(100, 100, 450, 300);

		//コンテナにデフォルトで設定されているレイアウトマネージャーを無効にする
		setLayout(null);

		/*
		 * 基本的に、以下の流れで配置する。
		 * ・コンポーネントの生成
		 * ・位置、サイズの指定//public void setBounds(int x座標, int y座標, int width, int height);
		 * ・コンポーネントの追加
		 * */

	    JLabel Title = new JLabel("蔵書管理システム");	//コンポーネントの生成
	    Title.setBounds(10, 0, 150, 20);				//位置、サイズの指定
		this.add(Title);								//コンポーネントの追加

	    JLabel ErrMsg = new JLabel("ログインしてください。");
	    ErrMsg.setBounds(10, 30, 200, 20);
		this.add(ErrMsg);

		/*
		 * ログイン情報（ID、パスワード）を入力するフィールド
		 */
	    JLabel labelId = new JLabel("ID");
	    JTextField textId = new JTextField();
	    labelId.setBounds(10, 60, 200, 30);
	    textId.setBounds(100, 60, 200, 30);
	    this.add(labelId);
	    this.add(textId);

	    JLabel labelPass = new JLabel("Password");
	    JPasswordField textPass = new JPasswordField();
	    labelPass.setBounds(10, 90, 200, 30);
	    textPass.setBounds(100, 90, 200, 30);
	    this.add(labelPass);
	    this.add(textPass);

	    /*
	     * ログイン
	     */
		JButton btnLogin = new JButton("ログイン");
		btnLogin.setBounds(100, 130, 200, 30);

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//ボタンを押下した際の処理を記述
				try {
					//UserBLのloginメソッドを呼び出し
					User user = new UserBL().login(textId.getText(), textPass.getText()) ;

					//次の画面（操作選択）のインスタンスを作成
					OperationSelect os = new OperationSelect(user);

					//次の画面を表示し、現在の画面を非表示にする
					os.setVisible(true);
					setVisible(false);

				} catch (UserLoginException e1) {
					ErrMsg.setText("ログインに失敗しました。");
					e1.printStackTrace();
				} catch (Exception e1) {
					ErrMsg.setText("システムエラーが発生しました。");
					e1.printStackTrace();
				}
			}
		});

		//現在の画面（ログイン画面）にログインボタンを追加
	    this.add(btnLogin);

	}

}
