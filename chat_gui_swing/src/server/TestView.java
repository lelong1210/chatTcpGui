package server;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class TestView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField textField_8;
	private Vector vListSocket = new Vector();
	private Vector vTitlelistsocket = new Vector();
	private DefaultTableModel modelsocket;
	private JTable tableSocket;
	private JTextArea textAreaResult;
	public JTextArea getTextAreaResult() {
		return textAreaResult;
	}

	public void setTextAreaResult(JTextArea textAreaResult) {
		this.textAreaResult = textAreaResult;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestView frame = new TestView();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public TestView() throws IOException {
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
		ThietLapGiaoDien();
	}

	private void ThietLapGiaoDien() {
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
		txtUsername.setBounds(182, 30, 165, 24);
		panel_1.add(txtUsername);
		txtUsername.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Status");
		lblNewLabel_2.setBounds(33, 66, 64, 15);
		panel_1.add(lblNewLabel_2);
		textField_8 = new JTextField();
		textField_8.setColumns(10);

		JComboBox<String> txtComboBoxStatus = new JComboBox<>();
		txtComboBoxStatus.addItem("");
		txtComboBoxStatus.addItem("Active");
		txtComboBoxStatus.addItem("Block");
		txtComboBoxStatus.setBounds(182, 61, 165, 24);
		panel_1.add(txtComboBoxStatus);

		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

			}
		});
		btnAddUser.setBounds(38, 99, 117, 25);
		contentPane.add(btnAddUser);

		JButton btnUpdateUser = new JButton("Update");
		btnUpdateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnUpdateUser.setBounds(167, 99, 117, 25);
		contentPane.add(btnUpdateUser);

		JButton btnDeleteUser = new JButton("Delete");
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnDeleteUser.setBounds(296, 99, 117, 25);
		contentPane.add(btnDeleteUser);

//		model = new DefaultTableModel(vData, vTitle);

		JPanel panel = new JPanel();
		panel.setBounds(426, 0, 449, 409);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 449, 409);
		panel.add(scrollPane_1);

		modelsocket = new DefaultTableModel(vListSocket, vTitlelistsocket);
		tableSocket = new JTable(modelsocket);
		tableSocket.addMouseListener(new MouseListener() {

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

			}
		});
		scrollPane_1.setViewportView(tableSocket);

		JButton btnKick = new JButton("Kick");
		btnKick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
			}
		});
		btnKick.setBounds(585, 421, 137, 25);
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
}
