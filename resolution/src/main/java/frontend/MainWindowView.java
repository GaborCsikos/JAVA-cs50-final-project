/**
 * 
 */
package frontend;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
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
import javax.swing.JProgressBar;
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

	private JComboBox<Resolution> resolutionCombobox;
	private JComboBox<SubTask> subTaskCombobox;
	private JCheckBox showOnlyNotFinishedResolutions;
	private JCheckBox showOnlyNotFinishedTasks;
	private JLabel resolutionPercentage;
	private JLabel subTaskPercentage;
	private JProgressBar resolutionProgress;
	private JProgressBar subTaskProgress;

	private JPanel resolutionPanelProgress;
	private JPanel mainPanel;
	private JPanel subTaskPanelInfo;
	private JPanel resolutionPanelInfo;
	private JPanel subTaskPanelProgress;
	private JPanel managePanel;

	private JButton manageResolution;
	private JButton manageSubTask;

	private TaskView manageTask;
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
		} else if (e.getSource() == manageResolution) {
			if (controller.isYearSet()) {
				manageTask = new ResolutionView(this, controller, true);
				manageTask.setModelCombobox(resolutionCombobox.getModel());
				reLoad();
			} else {
				showMessage("Please create a new year");
			}
		} else if (e.getSource() == manageSubTask) {
			if (controller.isYearSet()
					&& resolutionCombobox.getSelectedItem() != null) {
				long id = ((Resolution) resolutionCombobox.getSelectedItem())
						.getId();
				manageTask = new SubtaskView(this, controller, id);
				manageTask.setModelSubtask(subTaskCombobox.getModel());
				reLoad();
			} else {
				showMessage("Please create a new year");
			}
		} else if (e.getSource() == resolutionCombobox) {
			loadSubTasks(showOnlyNotFinishedTasks.isSelected());
		} else if (e.getSource() == showOnlyNotFinishedResolutions) {
			loadResolutions(showOnlyNotFinishedResolutions.isSelected());
		} else if (e.getSource() == showOnlyNotFinishedTasks) {
			loadSubTasks(showOnlyNotFinishedTasks.isSelected());
		}

	}

	private void reLoad() {
		loadSubTasks(showOnlyNotFinishedTasks.isSelected());
		loadResolutions(showOnlyNotFinishedResolutions.isSelected());
		controller.reCalculate();
		setSubTaskPercentage();
	}

	public void start() {
		initMenu();
		initFileChooser();
		initView();
		initListeners();
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
		this.setSize(500, 200);
		this.setResizable(false);
		this.setVisible(true);

	}

	private void initView() {
		mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.WEST);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		addResolutionView();
		addSubTaskView();
		addManageButtons();
		mainPanel.add(resolutionPanelInfo);
		mainPanel.add(resolutionPanelProgress);
		mainPanel.add(subTaskPanelInfo);
		mainPanel.add(subTaskPanelProgress);
		mainPanel.add(managePanel);
	}

	private void addManageButtons() {
		managePanel = new JPanel(new FlowLayout());

		// buttons
		manageResolution = new JButton("Manage resolution");
		manageSubTask = new JButton("Manage task");

		managePanel.add(manageResolution);
		managePanel.add(manageSubTask);
		managePanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		managePanel.setVisible(true);
	}

	private void addSubTaskView() {
		addSubTaskInfo();
		addSubTaskProgress();

	}

	private void addSubTaskInfo() {
		subTaskPanelInfo = new JPanel(new FlowLayout());

		// label
		JLabel taskLabel = new JLabel("Task:");

		// checkbox
		showOnlyNotFinishedTasks = new JCheckBox("Only not finished");
		showOnlyNotFinishedTasks.setSelected(true);

		// combobox
		subTaskCombobox = new JComboBox<SubTask>();
		loadSubTasks(showOnlyNotFinishedTasks.isSelected());
		subTaskPanelInfo.add(taskLabel);
		subTaskPanelInfo.add(subTaskCombobox);
		subTaskPanelInfo.setVisible(true);
		subTaskPanelInfo.add(showOnlyNotFinishedTasks);
		subTaskPanelInfo
				.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	}

	private void loadSubTasks(boolean showJustInProgress) {
		if (resolutionCombobox.getSelectedItem() != null) {
			subTaskCombobox.removeAllItems();
			List<SubTask> subTasks = controller.loadSubTasks(resolutionCombobox
					.getSelectedIndex());
			for (SubTask element : subTasks) {
				if (!element.isDone()) {
					subTaskCombobox.addItem(element);
				} else {
					if (!showJustInProgress) {
						subTaskCombobox.addItem(element);
					}
				}
			}
		}
	}

	private void initListeners() {
		exitWithoutSave.addActionListener(this);
		newYear.addActionListener(this);
		saveYear.addActionListener(this);
		loadYear.addActionListener(this);
		manageSubTask.addActionListener(this);
		manageResolution.addActionListener(this);
		resolutionCombobox.addActionListener(this);
		showOnlyNotFinishedResolutions.addActionListener(this);
		showOnlyNotFinishedTasks.addActionListener(this);
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
		loadResolutions(showOnlyNotFinishedResolutions.isSelected());
		resolutionPanelInfo.add(resolutionLabel);
		resolutionPanelInfo.add(resolutionCombobox);
		resolutionPanelInfo.setVisible(true);
		resolutionPanelInfo.add(showOnlyNotFinishedResolutions);
		resolutionPanelInfo
				.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

	}

	private void loadResolutions(boolean showJustInProgress) {
		List<Resolution> resolutions = controller.getResolutions();
		resolutionCombobox.removeAllItems();
		for (Resolution resolution : resolutions) {
			if (!resolution.isDone()) {
				resolutionCombobox.addItem(resolution);
			} else {
				if (!showJustInProgress) {
					resolutionCombobox.addItem(resolution);
				}
			}
		}
	}

	private void addResolutionProgress() {
		resolutionPanelProgress = new JPanel(new FlowLayout());

		// label
		JLabel resolutionLabel = new JLabel("Progress:");

		// progerssBar
		resolutionProgress = new JProgressBar(0, 100);

		// percentage
		resolutionPercentage = new JLabel("0%");

		resolutionPanelProgress.add(resolutionLabel);
		resolutionPanelProgress.add(resolutionProgress);
		resolutionPanelProgress.add(resolutionPercentage);
		resolutionPanelProgress
				.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

	}

	private void addSubTaskProgress() {
		subTaskPanelProgress = new JPanel();
		subTaskPanelProgress.setLayout(new FlowLayout());

		// label
		JLabel label = new JLabel("Progress:");

		// progerssBar
		subTaskProgress = new JProgressBar(0, 100);

		// percentage
		subTaskPercentage = new JLabel("0%");

		subTaskPanelProgress.add(label);
		subTaskPanelProgress.add(subTaskProgress);
		subTaskPanelProgress.add(subTaskPercentage);
		subTaskPanelProgress
				.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

	}

	private void initFileChooser() {
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));

	}

	public void initTestData() {
		controller.checkNumber("2016");
		Resolution resolutionOne = new Resolution();
		resolutionOne.setName("Learn java");
		SubTask subTaskOne = new SubTask();
		subTaskOne.setName("read java book");
		SubTask subTaskTwo = new SubTask();
		subTaskTwo.setName("program");
		resolutionOne.getSubtasks().add(subTaskOne);
		resolutionOne.getSubtasks().add(subTaskTwo);

		Resolution resolutionTwo = new Resolution();
		resolutionTwo.setName("This is Done");
		resolutionTwo.setDone(true);
		SubTask three = new SubTask();
		three.setName("nothing");
		three.setDone(true);
		resolutionTwo.getSubtasks().add(three);
		controller.getResolutions().add(resolutionOne);
		controller.getResolutions().add(resolutionTwo);
		loadResolutions(true);
		loadSubTasks(true);
		controller.reCalculate();
		setSubTaskPercentage();
	}

	private void setSubTaskPercentage() {
		controller.setSubTaskPercentage(subTaskCombobox.getSelectedItem());
	}

	public void setResolutionPercentage(int i) {
		resolutionProgress.setValue(i);
	}

	public void setSubTaskPercentage(int i) {
		resolutionProgress.setValue(i);
	}
}
