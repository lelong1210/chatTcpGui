package guiCore;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Remote;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;

import event.PublicEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;

public class LoginView {

	JPanel panel_Login;
	JPanel panel_Register;
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JTextField getText_username() {
		return text_username;
	}

	public void setText_username(JTextField text_username) {
		this.text_username = text_username;
	}

	public JTextField getText_enter_pass() {
		return text_enter_pass;
	}

	public void setText_enter_pass(JTextField text_enter_pass) {
		this.text_enter_pass = text_enter_pass;
	}

	public JTextField getText_re_enter_pass() {
		return text_re_enter_pass;
	}

	public void setText_re_enter_pass(JTextField text_re_enter_pass) {
		this.text_re_enter_pass = text_re_enter_pass;
	}

	private JFrame frame;
	private JTextField text_username;
	private JTextField text_enter_pass;
	private JTextField text_re_enter_pass;

	public LoginView() {
	
		
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
		
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1206, 598);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JLabel lblImageMain = new JLabel();
		lblImageMain.setBounds(12, 0, 742, 548);
		frame.getContentPane().add(lblImageMain);
		ImageIcon imageIconMain = new ImageIcon(this.getClass().getResource("/client/icon/login_image.png"));
		
		Image image = imageIconMain.getImage(); // transform it 
		Image newimg = image.getScaledInstance(720, 520,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIconMain = new ImageIcon(newimg);  
		
		lblImageMain.setIcon(imageIconMain);
		
		paintLogin();
		
	}
	public void paintLogin() {
		
		if(panel_Register != null) {
			frame.remove(panel_Register);
			frame.repaint();
			frame.revalidate();	
		}
		
		
		panel_Login = new JPanel();
		panel_Login.setBounds(756, 79, 353, 313);
		panel_Login.setLayout(null);
		panel_Login.setBorder(BorderFactory.createEtchedBorder());
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(12, 97, 104, 15);
		panel_Login.add(lblNewLabel);
		
		text_username = new JTextField();
		text_username.setBounds(158, 95, 154, 30);
		panel_Login.add(text_username);
		text_username.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(12, 143, 70, 15);
		panel_Login.add(lblNewLabel_1);
		
		text_enter_pass = new JPasswordField();
		text_enter_pass.setBounds(158, 141, 154, 30);
		panel_Login.add(text_enter_pass);
		text_enter_pass.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(116, 229, 117, 25);
        btnLogin.setBorder(null);
        btnLogin.setContentAreaFilled(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setIcon(new ImageIcon(getClass().getResource("/client/icon/send.png")));
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	PublicEvent.getInstance().getEventLogin().login(text_username.getText(),text_enter_pass.getText());
            	text_username.setText("");
            	text_enter_pass.setText("");
            }
        });
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(126, 276, 117, 25);
		btnRegister.setBorder(null);
		btnRegister.setContentAreaFilled(false);
		btnRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paintRegister();
			}
		});
        
        
		panel_Login.add(btnRegister);
		panel_Login.add(btnLogin);
		
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setFont(new Font("Dialog", Font.BOLD, 30));
		lblLogin.setBounds(116, 26, 199, 47);
		panel_Login.add(lblLogin);
		
		
		frame.getContentPane().add(panel_Login);
		frame.repaint();
		frame.revalidate();
	}
	
	public void paintRegister() {

		if(panel_Login != null) {
			frame.remove(panel_Login);
			frame.repaint();
			frame.revalidate();
				
		}
		
		panel_Register = new JPanel();
		panel_Register.setBounds(756, 79, 353, 313);
		panel_Register.setLayout(null);
		panel_Register.setBorder(BorderFactory.createEtchedBorder());
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(12, 97, 104, 15);
		panel_Register.add(lblNewLabel);
		
		text_username = new JTextField();
		text_username.setBounds(158, 95, 154, 30);
		panel_Register.add(text_username);
		text_username.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(12, 143, 70, 15);
		panel_Register.add(lblNewLabel_1);
		
		text_enter_pass = new JTextField();
		text_enter_pass.setBounds(158, 141, 154, 30);
		panel_Register.add(text_enter_pass);
		text_enter_pass.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(126, 276, 117, 25);
        btnLogin.setBorder(null);
        btnLogin.setContentAreaFilled(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	paintLogin();
            }
        });
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(116, 229, 117, 25);
		btnRegister.setBorder(null);
		btnRegister.setContentAreaFilled(false);
		btnRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnRegister.setIcon(new ImageIcon(getClass().getResource("/client/icon/send.png")));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            	PublicEvent.getInstance().getEventLogin().register(text_username.getText(), text_enter_pass.getText(), text_re_enter_pass.getText());
            	text_username.setText("");
            	text_enter_pass.setText("");
            	text_re_enter_pass.setText("");
			}
		});
        
        
		panel_Register.add(btnRegister);
		panel_Register.add(btnLogin);
		
		
		JLabel lblLogin = new JLabel("REGISTER");
		lblLogin.setFont(new Font("Dialog", Font.BOLD, 30));
		lblLogin.setBounds(116, 26, 199, 47);
		panel_Register.add(lblLogin);
		
		

		
		JLabel lblNewLabel_2 = new JLabel("Repassword");
		lblNewLabel_2.setBounds(12, 184, 104, 15);
		panel_Register.add(lblNewLabel_2);
		
		text_re_enter_pass = new JTextField();
		text_re_enter_pass.setBounds(158, 182, 154, 30);
		panel_Register.add(text_re_enter_pass);
		text_re_enter_pass.setColumns(10);
		
		frame.getContentPane().add(panel_Register);
		frame.repaint();
		frame.revalidate();
	}
}
