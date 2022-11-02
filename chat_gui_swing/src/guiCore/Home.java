package guiCore;
import net.miginfocom.swing.MigLayout;
import chat.*;

public class Home extends javax.swing.JLayeredPane {

	private static final long serialVersionUID = 1L;

	private Chat chat;
	
	
    public Home() {
        initComponents();
        init();
    }

    private void init() {
    	this.chat = new Chat();
    	
    	
        setLayout(new MigLayout("fillx, filly", "0[200!]5[fill, 100%]5[200!]0", "0[fill]0"));
        this.add(new Menu_Left());
        this.add(this.chat);
        this.add(new Menu_Right());
    }

    public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}


    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1007, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 551, Short.MAX_VALUE)
        );
    }
}
