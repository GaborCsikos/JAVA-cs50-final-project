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
		MainWindowController controller = new MainWindowController();
		MainWindowView view = new MainWindowView(controller);
		try {
			view.start();
			controller.start();
		} catch (Exception e) {
			e.printStackTrace();
			view.showMessage("Problem occured, please contact the developer!");
		}

	}

}
