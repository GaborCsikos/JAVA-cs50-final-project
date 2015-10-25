/**
 * 
 */
package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Main window of application
 * 
 * @author Gabor Csikos
 * 
 */
public class MainWindowView extends JFrame implements ActionListener {

	private JMenuBar menuBar;
	private JMenu yearMenu;
	private JMenuItem chooseYear;
	private JMenuItem saveYear;
	private JMenuItem exitWithoutSave;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exitWithoutSave) {
			System.exit(0);
		} else if (e.getSource() == saveYear) {
			// TODO save year
		} else if (e.getSource() == chooseYear) {
			// TODO chose Year year
		}
	}

	public void start() {
		initMenu();
		initListeners();
	}

	private void initMenu() {
		menuBar = new JMenuBar();

		// Menus
		yearMenu = new JMenu("Year");
		menuBar.add(yearMenu);

		// menuitems
		chooseYear = new JMenuItem("Choose");
		saveYear = new JMenuItem("Save");
		exitWithoutSave = new JMenuItem("Exit without save");

		yearMenu.add(chooseYear);
		yearMenu.add(saveYear);
		yearMenu.add(exitWithoutSave);
		this.setJMenuBar(menuBar);
		this.setTitle("Resolution planner");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setVisible(true);

	}

	private void initListeners() {
		exitWithoutSave.addActionListener(this);
	}

}
