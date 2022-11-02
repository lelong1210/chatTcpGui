package client;

public class RunMain {
	public static void main(String args[]) {

		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ClientGuiView view = new ClientGuiView();
					new Controller(view);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}
}
