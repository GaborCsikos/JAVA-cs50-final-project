package backend;

import frontend.MainWindowView;

/**
 * Main class of application
 * 
 * @author Gabor Csikos
 * 
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainWindowController controller = new MainWindowController();
				MainWindowView view = new MainWindowView(controller);
				try {
					view.start();
					controller.start();
					controller.setView(view);
					view.initTestData();
				} catch (Exception e) {
					e.printStackTrace();
					view.showMessage("Problem occured, please contact the developer!");
				}
			}
		});

	}
}
