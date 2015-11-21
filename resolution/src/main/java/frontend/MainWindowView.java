/**
 * 
 */
package frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

import backend.MainWindowController;
import backend.api.FileUtils;
import backend.entity.Resolution;
import backend.entity.SubTask;

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
	private JPanel resolutionPanelInfo;
	private JComboBox<Resolution> resolutionCombobox;
	private JComboBox<SubTask> subTaskCombobox;
	private JCheckBox showOnlyNotFinishedResolutions;
	private JCheckBox showOnlyNotFinishedTasks;

	private JPanel resolutionPanelProgress;
	private JPanel subTaskPanelInfo;
	private JPanel subTaskPanelProgress;
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
		initView();
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

	private void initView() {
		BorderLayout layout = new BorderLayout();

		addResolutionView();
		addSubTaskView();
		getContentPane().setLayout(layout);
		// getContentPane().add(resolutionPanelInfo, BorderLayout.CENTER);
		// TODO set main Panel
	}

	private void addSubTaskView() {
		addSubTaskInfo();
		addSubTaskProgress();

	}

	private void addSubTaskProgress() {
		// TODO Auto-generated method stub

	}

	private void addSubTaskInfo() {
		FlowLayout subTaskLayout = new FlowLayout();
		subTaskPanelInfo = new JPanel();
		subTaskPanelInfo.setLayout(subTaskLayout);

		// label
		JLabel taskLabel = new JLabel("Task:");

		// checkbox
		showOnlyNotFinishedTasks = new JCheckBox("Only not finished");
		showOnlyNotFinishedTasks.setSelected(true);

		// combobox
		subTaskCombobox = new JComboBox<SubTask>();
		List<SubTask> subTasks = controller.loadSubTasks(resolutionCombobox
				.getSelectedIndex());
		for (SubTask element : subTasks) {
			subTaskCombobox.addItem(element);
		}
		subTaskPanelInfo.add(taskLabel);
		subTaskPanelInfo.add(subTaskCombobox);
		subTaskPanelInfo.setVisible(true);
		subTaskPanelInfo.add(showOnlyNotFinishedTasks);

	}

	private void initListeners() {
		exitWithoutSave.addActionListener(this);
		newYear.addActionListener(this);
		saveYear.addActionListener(this);
		loadYear.addActionListener(this);
	}

	private void addResolutionView() {
		addResolutionInfo();
		addResolutionProgress();
	}

	private void addResolutionInfo() {
		FlowLayout resolutionLayout = new FlowLayout();
		resolutionPanelInfo = new JPanel();
		resolutionPanelInfo.setLayout(resolutionLayout);

		// label
		JLabel resolutionLabel = new JLabel("Resolution:");

		// checkbox
		showOnlyNotFinishedResolutions = new JCheckBox("Only not finished");
		showOnlyNotFinishedResolutions.setSelected(true);

		// combobox
		resolutionCombobox = new JComboBox<Resolution>();
		List<Resolution> resolutions = controller.getResolutions();
		for (Resolution resolution : resolutions) {
			resolutionCombobox.addItem(resolution);
		}
		resolutionPanelInfo.add(resolutionLabel);
		resolutionPanelInfo.add(resolutionCombobox);
		resolutionPanelInfo.setVisible(true);
		resolutionPanelInfo.add(showOnlyNotFinishedResolutions);

	}

	private void addResolutionProgress() {
		// TODO Auto-generated method stub

	}

	private void initFileChooser() {
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));

	}
}
