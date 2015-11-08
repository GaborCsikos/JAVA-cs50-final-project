package backend;


/**
 * Controller for year handling
 * 
 * @author Gabor Csikos
 * 
 */
public class MainWindowController {
	private MainWindowModel model;

	public MainWindowController() {
		model = new MainWindowModel();
	}

	public void start() {
		model.start();

	}
}
