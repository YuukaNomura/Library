package library.windowsView.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import library.bl.UserBL;
import library.dto.User;

public class UserSearch extends JFrame {
	public UserSearch() {

		setTitle("蔵書管理システム");
		setBounds(100, 100, 450, 300);

		setLayout(null);

		JLabel Title = new JLabel("ユーザ情報更新・削除");
		Title.setBounds(10, 0, 150, 20);
		this.add(Title);

		JLabel ErrMsg = new JLabel("更新するユーザのIDを入力してください。");
		ErrMsg.setBounds(10, 30, 400, 20);
		this.add(ErrMsg);

		JLabel labelId = new JLabel("ID");
		JTextField textId = new JTextField();
		labelId.setBounds(10, 60, 200, 30);
		textId.setBounds(100, 60, 200, 30);
		this.add(labelId);
		this.add(textId);

		JLabel labelSearchId = new JLabel("ID");
		JLabel labelSearchIdResult = new JLabel();
		JLabel labelSearchName = new JLabel("名前");
		JLabel labelSearchNameResult = new JLabel();
		labelSearchId.setBounds(10, 150, 200, 30);
		labelSearchIdResult.setBounds(100, 150, 200, 30);
		labelSearchName.setBounds(10, 180, 200, 30);
		labelSearchNameResult.setBounds(100, 180, 200, 30);
		labelSearchId.setVisible(false);
		labelSearchIdResult.setVisible(false);
		labelSearchName.setVisible(false);
		labelSearchNameResult.setVisible(false);
		this.add(labelSearchId);
		this.add(labelSearchIdResult);
		this.add(labelSearchName);
		this.add(labelSearchNameResult);

		JButton btnOK = new JButton("キャンセル");
		btnOK.setBounds(220, 100, 100, 30);
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		this.add(btnOK);

		JButton btnUpdate = new JButton("更新");
		btnUpdate.setBounds(100, 220, 100, 30);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserUpdateInput uui = new UserUpdateInput(labelSearchIdResult.getText(),
						labelSearchNameResult.getText());
				uui.setVisible(true);
				setVisible(false);

				//				if (table.getSelectedColumns().length == 0) {
				//					ErrMsg.setText("データを選択してください。");
				//				} else {
				//					String year = table.getValueAt(table.getSelectionModel().getLeadSelectionIndex(), 3).toString();
				//
				//					BookUpdateInput bui = new BookUpdateInput(
				//							new Book((String) table.getValueAt(table.getSelectionModel().getLeadSelectionIndex(), 0),
				//									(String) table.getValueAt(table.getSelectionModel().getLeadSelectionIndex(), 1),
				//									(String) table.getValueAt(table.getSelectionModel().getLeadSelectionIndex(), 2),
				//									Integer.parseInt(year)));
				//
				//					bui.setVisible(true);
				//					setVisible(false);
				//
				//					//ErrMsg.setText(table.getSelectionModel().getLeadSelectionIndex() + " " + table.getValueAt(table.getSelectionModel().getLeadSelectionIndex(),0));
				//				}
			}
		});

		this.add(btnUpdate);

		btnUpdate.setVisible(false);

		JButton btnRemove = new JButton("削除");
		btnRemove.setBounds(220, 220, 100, 30);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserRemoveInput uri = new UserRemoveInput(labelSearchIdResult.getText(),
						labelSearchNameResult.getText());
				uri.setVisible(true);
				setVisible(false);

				//
				//				if (table.getSelectedColumns().length == 0) {
				//					ErrMsg.setText("データを選択してください。");
				//				} else {
				//					String year = table.getValueAt(table.getSelectionModel().getLeadSelectionIndex(), 3).toString();
				//
				//					BookRemoveInput bri = new BookRemoveInput(
				//							new Book((String) table.getValueAt(table.getSelectionModel().getLeadSelectionIndex(), 0),
				//									(String) table.getValueAt(table.getSelectionModel().getLeadSelectionIndex(), 1),
				//									(String) table.getValueAt(table.getSelectionModel().getLeadSelectionIndex(), 2),
				//									Integer.parseInt(year)));
				//
				//					bri.setVisible(true);
				//					setVisible(false);
				//
				//				}
			}
		});

		this.add(btnRemove);
		btnRemove.setVisible(false);

		JButton btnSearch = new JButton("検索");
		btnSearch.setBounds(100, 100, 100, 30);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					User resultUser = null;

					if (!textId.getText().equals("")) {

						resultUser = new UserBL().findById(textId.getText());

						if (!(resultUser == null)) {

							ErrMsg.setText("以下のユーザが見つかりました。操作を選択してください。");
							labelSearchIdResult.setText(resultUser.getId());
							labelSearchNameResult.setText(resultUser.getName());

							labelSearchId.setVisible(true);
							labelSearchIdResult.setVisible(true);
							labelSearchName.setVisible(true);
							labelSearchNameResult.setVisible(true);

							btnUpdate.setVisible(true);
							btnRemove.setVisible(true);
							//br.setVisible(true);
						} else {

							ErrMsg.setText("ユーザが見つかりません。");
							labelSearchId.setVisible(false);
							labelSearchIdResult.setVisible(false);
							labelSearchName.setVisible(false);
							labelSearchNameResult.setVisible(false);

							btnUpdate.setVisible(false);
							btnRemove.setVisible(false);
						}
					} else {
						ErrMsg.setText("IDを入力してください。");
						labelSearchId.setVisible(false);
						labelSearchIdResult.setVisible(false);
						labelSearchName.setVisible(false);
						labelSearchNameResult.setVisible(false);

						btnUpdate.setVisible(false);
						btnRemove.setVisible(false);
					}
				} catch (Exception e1) {
					ErrMsg.setText("システムエラーが発生しました。");
					e1.printStackTrace();
				}
			}
		});

		this.add(btnSearch);

	}
}
