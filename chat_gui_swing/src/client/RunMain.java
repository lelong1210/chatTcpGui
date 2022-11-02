package client;

public class RunMain {
	public static void main(String args[]) {
		new RunMain();
	}

	public RunMain() {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception ex) {

		}
		
		ClientGuiView view = new ClientGuiView();
		view.getHome().getChat().getChatBody().addItemLeft("123");
	}
}
