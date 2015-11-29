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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;

import backend.MainWindowController;
import backend.entity.Resolution;
import backend.entity.SubTask;
import backend.entity.Task;

/**
 * @author Gabor Csikos
 * 
 */
public abstract class TaskView extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panel;
	private JPanel taskPanel;
	private JPanel backButtonPanel;
	private JButton backButton;
	private JCheckBox done;
	private JButton createButton;
	private JButton updateButton;
	private JButton deleteButton;
	private JLabel percentagelabel;
	protected JTextField currentPercentage;

	protected JPanel operationPanel;
	protected JLabel taskName;
	protected JComboBox<Task> tasks;

	protected MainWindowController controller;

	public TaskView(final JFrame frame, MainWindowController controller) {
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
	}

	private void setActionListeners() {
		createButton.addActionListener(this);
		backButton.addActionListener(this);
		updateButton.addActionListener(this);
		deleteButton.addActionListener(this);
		done.addActionListener(this);
		tasks.addActionListener(this);
		currentPercentage.addActionListener(this);
	}

	abstract void setLabel();

	abstract void createTask();

	abstract void deleteTask(Object selectedItem);

	abstract void updateTask(Object selectedItem);

	abstract void setState(int selectedItemIndex, boolean selected);

	abstract void setPercentage(int selectedItemIndex, int percentage);

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
		percentagelabel = new JLabel("%");
		currentPercentage = new JTextField("0");
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
			deleteTask(tasks.getSelectedItem());
		} else if (e.getSource() == updateButton) {
			updateTask(tasks.getSelectedItem());
		} else if (e.getSource() == tasks) {
			setCheckBox(tasks.getSelectedItem());
		} else if (e.getSource() == done) {
			if (tasks.getSelectedItem() != null) {
				setState(tasks.getSelectedIndex(), done.isSelected());
			}
		} else if (e.getSource() == currentPercentage) {
			if (tasks.getSelectedItem() != null) {
				String text = currentPercentage.getText();
				if (StringUtils.isEmpty(text)) {
					try {
						int percentage = Integer.parseInt(text);
						if (percentage < 0 || percentage > 100) {
							JOptionPane.showMessageDialog(null,
									"The range is between 0-100", "Error",
									JOptionPane.ERROR_MESSAGE);
						} else {
							setPercentage(tasks.getSelectedIndex(), percentage);
						}
					} catch (NumberFormatException exp) {
						JOptionPane.showMessageDialog(null,
								"The range is between 0-100", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
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
		this.setVisible(true);
		setCurrentState();
	}

	private void setCurrentState() {
		if (tasks.getSelectedItem() != null) {
			done.setSelected(((Task) tasks.getSelectedItem()).isDone());
			int percentage = ((Task) tasks.getSelectedItem()).getPercentage();
			currentPercentage.setText(String.valueOf(percentage));
		}

	}

	public void setModelSubtask(ComboBoxModel<SubTask> model) {
		int size = model.getSize();
		for (int i = 0; i < size; i++) {
			Task element = (Task) model.getElementAt(i);
			tasks.addItem(element);
		}
		this.setVisible(true);
		setCurrentState();
	}

}
