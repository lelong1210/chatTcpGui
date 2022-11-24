package server;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import core.UserInfo;
import event.PublicEvent;
import guiCore.*;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class ServerGuiView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;

	public JTextField getTxtUsername() {
		return txtUsername;
	}

	public void setTxtUsername(JTextField txtUsername) {
		this.txtUsername = txtUsername;
	}

	private DefaultTableModel model;
	private JComboBox<String> txtComboBoxStatus;

	public JComboBox<String> getTxtComboBoxStatus() {
		return txtComboBoxStatus;
	}

	public void setTxtComboBoxStatus(JComboBox<String> txtComboBoxStatus) {
		this.txtComboBoxStatus = txtComboBoxStatus;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	private JTable tableUserLogin;
	private JTextArea textAreaResult;

	public JTextArea getTextAreaResult() {
		return textAreaResult;
	}

	public void setTextAreaResult(JTextArea textAreaResult) {
		this.textAreaResult = textAreaResult;
	}

	public ServerGuiView() throws IOException {
		for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				try {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
		SetUpGUi();
	}

	private void SetUpGUi() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1600, 759);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(28, 0, 386, 93);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblH = new JLabel("Username");
		lblH.setBounds(33, 32, 86, 15);
		panel_1.add(lblH);

		txtUsername = new JTextField();
		txtUsername.setBounds(182, 28, 165, 30);
		panel_1.add(txtUsername);
		txtUsername.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Status");
		lblNewLabel_2.setBounds(33, 66, 64, 15);
		panel_1.add(lblNewLabel_2);

		txtComboBoxStatus = new JComboBox<>();
		txtComboBoxStatus.addItem("Block");
		txtComboBoxStatus.addItem("Active");
		txtComboBoxStatus.setBounds(182, 61, 165, 24);
		panel_1.add(txtComboBoxStatus);

		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> user = AddUser(getContentPane());
				if (user == null) {
					JOptionPane.showMessageDialog(getContentPane(), "information not empty", "Alert",
							JOptionPane.DEFAULT_OPTION);
				} else {
					PublicEvent.getInstance().getEventServer().Adduser(user.get(0), user.get(1));
				}
			}
		});
		btnAddUser.setBounds(34, 99, 117, 25);
		contentPane.add(btnAddUser);

		JButton btnUpdateUser = new JButton("Update");
		btnUpdateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnUpdateUser.setBounds(153, 99, 117, 25);
		contentPane.add(btnUpdateUser);

//		model = new DefaultTableModel(vData, vTitle);

		JPanel panel = new JPanel();
		panel.setBounds(426, 0, 449, 409);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 449, 409);
		panel.add(scrollPane_1);

		model = new DefaultTableModel();

		ArrayList<String> arrCols = new ArrayList<String>();
		arrCols.add("Username");
		arrCols.add("Status");
		arrCols.add("Chat");
		model.setColumnIdentifiers(arrCols.toArray());

		tableUserLogin = new JTable(model);
		tableUserLogin.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// get thong tin cot name
				int col = 0;
				int row = tableUserLogin.getSelectedRow();
				String username = (String) tableUserLogin.getModel().getValueAt(row, col);
				// get thong tin cot status
				col = 1;
				row = tableUserLogin.getSelectedRow();
				String status = (String) tableUserLogin.getModel().getValueAt(row, col);
				PublicEvent.getInstance().getEventServer().SelectTableUser(username, status);

			}
		});
		scrollPane_1.setViewportView(tableUserLogin);

		JButton btnKick = new JButton("Kick");
		btnKick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get thong tin cot name
				int col = 0;
				int row = tableUserLogin.getSelectedRow();
				String username = (String) tableUserLogin.getModel().getValueAt(row, col);
				// get thong tin cot status
				col = 1;
				row = tableUserLogin.getSelectedRow();
				String status = (String) tableUserLogin.getModel().getValueAt(row, col);
				PublicEvent.getInstance().getEventServer().KickUser(username, status);
			}
		});
		btnKick.setBounds(272, 99, 137, 25);
		contentPane.add(btnKick);

		textAreaResult = new JTextArea();
		JScrollPane scrollableTextArea = new JScrollPane(textAreaResult);
		scrollableTextArea.setBounds(30, 136, 387, 310);
		getContentPane().add(scrollableTextArea);

		getContentPane().setLayout(null);
		setSize(897, 532);
		setVisible(true);
		// thoát chương trình khi tắt window
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public ArrayList<String> AddUser(Container container) {

		JPanel panel = new JPanel(new BorderLayout(5, 5));

		JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
		label.add(new JLabel("username"));
		label.add(new JLabel("Password"));
		panel.add(label, BorderLayout.WEST);

		JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
		JTextField username = new JTextField();
		controls.add(username);
		JTextField password = new JTextField();
		controls.add(password);
		panel.add(controls, BorderLayout.CENTER);

		JOptionPane.showMessageDialog(container, panel, "Add User", JOptionPane.QUESTION_MESSAGE);

		ArrayList<String> arrayListUser = new ArrayList<String>();
		arrayListUser.add(username.getText());
		arrayListUser.add(password.getText());

		if (username.getText().isEmpty() || password.getText().isEmpty()) {
			return null;
		}
		return arrayListUser;
	}

	public void UpdateUserLoginInSystem(ArrayList<UserInfo> userInfo, ArrayList<UserInfo> userInfoSystem) {
		//

		// reload
		if (model.getRowCount() > 0) {
			for (int i = model.getRowCount() - 1; i > -1; i--) {
				model.removeRow(i);
			}
		}
		// load data in database
		ArrayList<String> arrRows = new ArrayList<String>();
		for (int i = 0; i < userInfo.size(); i++) {
			arrRows.add(userInfo.get(i).getUsername());
			arrRows.add("No Login");
			model.addRow(arrRows.toArray());
			arrRows.clear();
		}
		// update status
		for (int i = 0; i < userInfoSystem.size(); i++) {
			for (int j = 0; j < userInfo.size(); j++) {
				if (userInfoSystem.get(i).getUsername().equals(userInfo.get(j).getUsername())) {
					model.setValueAt("Loging", j, 1);
				}
			}
		}
		// update "..." chat
		for (int i = 0; i < userInfo.size(); i++) {
			model.setValueAt("Chat", i, 2);
		}
		
		  //SET CUSTOM RENDERER TO TEAMS COLUMN
		  tableUserLogin.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());;

		  //SET CUSTOM EDITOR TO TEAMS COLUMN
		  tableUserLogin.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor(new JTextField()));
		
		// load data
		model.fireTableDataChanged();
	}
}
