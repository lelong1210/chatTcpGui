package client;

import event.EventLogin;
import event.PublicEvent;
import guiCore.LoginView;

public class RunMain {
	public static void main(String args[]) {

		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {

					
					LoginView viewLog = new LoginView();
					
					
					ClientGuiView view = new ClientGuiView();
					new Controller(view,viewLog);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}
}
