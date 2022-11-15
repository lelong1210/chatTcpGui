package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import event.EventServer;
import event.PublicEvent;

public class Controller implements ActionListener  {
	private ServerGuiView view;
	private int port = 9900;
	private TCPServer tcpServer;
	
	public void actionPerformed(ActionEvent e) {

	}
    public Controller(ServerGuiView view) {
    	
        this.view = view;
        tcpServer = new TCPServer(port,view.getTextAreaResult(),view);
        tcpServer.open();
        tcpServer.start();
        
        PublicEvent.getInstance().addeventServer(new EventServer() {
			
			@Override
			public void UpdateUser(String username, int option) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void SelectTableUser() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void Adduser(String username, String Password) {
				// TODO Auto-generated method stub
				System.out.println("User In system "+tcpServer.getArrayListUser().toString());
			}
		});
    }
}
