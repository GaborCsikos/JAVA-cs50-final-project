package backend;

import frontend.MainWindowView;

/**
 * 
 * @author Gabor Csikos
 * 
 */
public class MainWindowController {
	private MainWindowView view;
	private MainWindowModel model;

	public MainWindowController() {
		view = new MainWindowView();
		model = new MainWindowModel();
	}

	public void start() {
		model.start();
		view.start();

	}
}
