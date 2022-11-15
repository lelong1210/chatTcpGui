package guiCore;

import javax.swing.JOptionPane;
import event.PublicEvent;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Menu_Right extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;

	private javax.swing.JLayeredPane menu;
	private MenuButton menuMessage;

	public Menu_Right() {
		initComponents();
		init();
	}

	private void init() {
		ScrollBar scrollBar = new ScrollBar();
		scrollBar.setBackground(new Color(255, 255, 255));
//        showMessage();
	}
	private void initComponents() {

		menu = new javax.swing.JLayeredPane();
		menuMessage = new MenuButton();
		menuMessage.setBounds(0, 0, 39, 40);
		menuMessage.setBackground(new Color(255, 255, 255));
		
		setBackground(new java.awt.Color(255,255,255));

		menu.setBackground(new java.awt.Color(255,255,255));
		menu.setOpaque(true);

		menuMessage.setIconSelected(
				new ImageIcon(Menu_Right.class.getResource("/client/icon/power-off.png"))); // NOI18N
		menuMessage.setSelected(true);
		menuMessage.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				menuMessageActionPerformed(evt);
				Object view;
				PublicEvent.getInstance().geteventExitOrLogout().sendLogoutToServer();
//				PublicEvent.getInstance().getEventChat().newMessage(input);
			}
		});
		menu.setLayout(null);
		menu.add(menuMessage);


		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
				.addGroup(layout.createSequentialGroup().addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addContainerGap()));
	}

	private void menuMessageActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_menuMessageActionPerformed
		if (!menuMessage.isSelected()) {
			menuMessage.setSelected(true);
		}
	}
	
}
