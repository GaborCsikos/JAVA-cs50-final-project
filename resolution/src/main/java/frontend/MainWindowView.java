/**
 * 
 */
package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import backend.MainWindowController;
import backend.api.FileUtils;

/**
 * Main window of application
 * 
 * @author Gabor Csikos
 * 
 */
public class MainWindowView extends JFrame implements ActionListener {

	private JMenuBar menuBar;
	private JMenu yearMenu;
	private JMenuItem newYear;
	private JMenuItem loadYear;
	private JMenuItem saveYear;
	private JMenuItem exitWithoutSave;
	private MainWindowController controller;
	private FileFilter filter = new YearFilter();
	private JFileChooser chooser;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainWindowView(MainWindowController controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exitWithoutSave) {
			System.exit(0);
		} else if (e.getSource() == saveYear) {
			if (controller.isYearSet()) {

				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				chooser.setFileFilter(filter);
				chooser.setSelectedFile(new File(controller.getName()));
				chooser.setDialogTitle("Save Year");
				if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
					if (chooser.getSelectedFile() != null) {
						String path = chooser.getSelectedFile()
								.getAbsolutePath();
						if (FileUtils.isFileTypeGood(path)) {
							controller.saveYear(chooser.getSelectedFile()
									.getAbsolutePath());
						}
					}
				}
			}

		} else if (e.getSource() == loadYear) {
			chooser.setDialogTitle("Load Year");
			chooser.setSelectedFile(null);
			if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				if (chooser.getSelectedFile() != null) {
					String path = chooser.getSelectedFile().getAbsolutePath();
					if (FileUtils.isFileTypeGood(path)) {
						controller.loadYear(chooser.getSelectedFile());
					}
				}
			}
		} else if (e.getSource() == newYear) {
			String result = JOptionPane.showInputDialog(this, "Enter a year:");
			controller.checkNumber(result);
		}
	}

	public void start() {
		initMenu();
		initListeners();
		initFileChooser();
	}

	private void initFileChooser() {
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));

	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	private void initMenu() {
		menuBar = new JMenuBar();

		// Menus
		yearMenu = new JMenu("Year");
		menuBar.add(yearMenu);

		// menuitems
		newYear = new JMenuItem("New");
		loadYear = new JMenuItem("Load");
		saveYear = new JMenuItem("Save");
		exitWithoutSave = new JMenuItem("Exit without save");

		yearMenu.add(newYear);
		yearMenu.add(saveYear);
		yearMenu.add(loadYear);
		yearMenu.add(exitWithoutSave);
		this.setJMenuBar(menuBar);
		this.setTitle("Resolution planner");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setVisible(true);

	}

	private void initListeners() {
		exitWithoutSave.addActionListener(this);
		newYear.addActionListener(this);
		saveYear.addActionListener(this);
		loadYear.addActionListener(this);
	}

}
