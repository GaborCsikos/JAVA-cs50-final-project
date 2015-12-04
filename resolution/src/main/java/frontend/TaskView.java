/**
 * 
 */
package frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import backend.MainWindowController;
import backend.entity.Resolution;
import backend.entity.SubTask;
import backend.entity.Task;

/**
 * @author Gabor Csikos
 * 
 */
public abstract class TaskView extends JDialog implements ActionListener,
		ChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panel;
	private JPanel taskPanel;
	private JPanel backButtonPanel;
	private JButton backButton;
	protected JCheckBox done;
	private JButton createButton;
	private JButton updateButton;
	private JButton deleteButton;
	protected JLabel percentagelabel;
	protected JSlider currentPercentage;

	protected JPanel operationPanel;
	protected JLabel taskName;
	protected JComboBox<Task> tasks;

	protected MainWindowController controller;
	protected boolean isResolution;
	private long resolutionId;

	public TaskView(final JFrame frame, MainWindowController controller,
			boolean isResolution, long resolutionId) {
		super(frame);
		this.dialogInit();
		this.setSize(500, 200);
		this.setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initLayout();
		setLabel();
		setActionListeners();
		this.setModal(true);
		this.controller = controller;
		this.isResolution = isResolution;
		this.resolutionId = resolutionId;
	}

	private void setActionListeners() {
		createButton.addActionListener(this);
		backButton.addActionListener(this);
		updateButton.addActionListener(this);
		deleteButton.addActionListener(this);
		done.addActionListener(this);
		tasks.addActionListener(this);
		currentPercentage.addChangeListener(this);
	}

	abstract void setLabel();

	abstract void createTask();

	abstract void deleteTask(long selectedItemId);

	abstract void updateTask(long selectedItemId);

	abstract void setState(long selectedItemId, boolean selected);

	abstract void setPercentage(long selectedItemId, int percentage);

	public void loadState(long selectedItemId) {
		if (tasks.getSelectedItem() != null) {
			currentPercentage.setValue(((Task) tasks.getSelectedItem())
					.getPercentage());
			done.setSelected(((Task) tasks.getSelectedItem()).isDone());
			int percentage = ((Task) tasks.getSelectedItem()).getPercentage();
			percentagelabel.setText(percentage + "%");
		}
	}

	private void initLayout() {
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		addTasks();
		addOperations();
		addBackButton();
		panel.add(taskPanel);
		panel.add(operationPanel);
		panel.add(backButtonPanel);
	}

	private void addTasks() {
		taskPanel = new JPanel(new FlowLayout());
		addTaskToview();

	}

	private void addTaskToview() {
		taskName = new JLabel("");
		tasks = new JComboBox<Task>();
		done = new JCheckBox("done");
		percentagelabel = new JLabel("0%");
		currentPercentage = new JSlider(0, 100);
		taskPanel.add(taskName);
		taskPanel.add(tasks);
		taskPanel.add(currentPercentage);
		taskPanel.add(percentagelabel);
		taskPanel.add(done);
	}

	private void addOperations() {
		operationPanel = new JPanel(new FlowLayout());
		addOperationsToView();
	}

	private void addOperationsToView() {
		operationPanel = new JPanel(new FlowLayout());
		createButton = new JButton("New");
		updateButton = new JButton("Edit");
		deleteButton = new JButton("Delete");
		operationPanel.add(createButton);
		operationPanel.add(updateButton);
		operationPanel.add(deleteButton);
	}

	private void addBackButton() {
		backButtonPanel = new JPanel(new FlowLayout());
		addCancelButton();
	}

	private void addCancelButton() {
		backButton = new JButton("Back");
		backButtonPanel.add(backButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backButton) {
			dispose();
		} else if (e.getSource() == createButton) {
			createTask();
		} else if (e.getSource() == deleteButton) {
			if (tasks.getSelectedItem() != null) {
				deleteTask(((Task) tasks.getSelectedItem()).getId());
			}
		} else if (e.getSource() == updateButton) {
			if (tasks.getSelectedItem() != null) {
				updateTask(((Task) tasks.getSelectedItem()).getId());
			}
		} else if (e.getSource() == tasks) {
			setCheckBox(tasks.getSelectedItem());
		} else if (e.getSource() == done) {
			if (tasks.getSelectedItem() != null) {
				setState(((Task) tasks.getSelectedItem()).getId(),
						done.isSelected());
			}

		}
		if (tasks.getSelectedItem() != null) {
			loadState(((Task) tasks.getSelectedItem()).getId());
		}
		reloadModel();

	}

	private void reloadModel() {
		if (isResolution) {
			for (Resolution element : controller.getResolutions()) {
				tasks.addItem((Task) element);
			}
		} else {
			for (SubTask element : controller
					.getSubTasksByResolutionId(resolutionId)) {
				tasks.addItem((Task) element);
			}
		}

		setCurrentState();

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == currentPercentage) {
			if (tasks.getSelectedItem() != null) {
				setPercentage(((Task) tasks.getSelectedItem()).getId(),
						currentPercentage.getValue());
			}
		}

	}

	private void setCheckBox(Object selectedItem) {
		if (tasks.getSelectedItem() != null) {
			done.setSelected(((Task) selectedItem).isDone());
		}

	}

	public void setModelCombobox(ComboBoxModel<Resolution> model) {
		int size = model.getSize();
		for (int i = 0; i < size; i++) {
			Task element = (Task) model.getElementAt(i);
			tasks.addItem(element);
		}
		setCurrentState();
		this.setVisible(true);
	}

	public void setCurrentState() {
		if (tasks.getSelectedItem() != null) {
			done.setSelected(((Task) tasks.getSelectedItem()).isDone());
			int percentage = ((Task) tasks.getSelectedItem()).getPercentage();
			currentPercentage.setValue(percentage);
			percentagelabel.setText(percentage + "%");
		}

	}

	public void setModelSubtask(ComboBoxModel<SubTask> model) {
		int size = model.getSize();
		for (int i = 0; i < size; i++) {
			Task element = (Task) model.getElementAt(i);
			tasks.addItem(element);
		}
		setCurrentState();
		this.setVisible(true);
	}

}
