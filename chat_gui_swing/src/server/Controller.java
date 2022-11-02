package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener  {
	private ServerGuiView view;
	
	private TCPServer tcpServer;
	
	public void actionPerformed(ActionEvent e) {

	}
    public Controller(ServerGuiView view) {
    	
        this.view = view;
        tcpServer = new TCPServer(view.getTextAreaResult());
        tcpServer.open();
        tcpServer.start();
    }
}
