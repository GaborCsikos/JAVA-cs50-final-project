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

import backend.entity.Resolution;
import backend.entity.SubTask;
import backend.entity.Task;

/**
 * @author Gabor Csikos
 * 
 */
public abstract class TaskView extends JDialog {

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

	protected JPanel operationPanel;
	protected JLabel taskName;
	// TODO change combobox
	private JComboBox<Task> tasks;

	public TaskView(final JFrame frame) {
		super(frame);
		this.dialogInit();
		this.setSize(500, 200);
		this.setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initLayout();
		setLabel();
		this.setModal(true);
		this.setVisible(true);
	}

	abstract void setLabel();

	abstract void setAlltask();

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
		done = new JCheckBox();
		taskPanel.add(taskName);
		taskPanel.add(tasks);
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
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				dispose();
				setAlltask();
			}

		});
	}

	public void loadResolutions(ComboBoxModel<Resolution> model) {
		// TODO Auto-generated method stub

	}

	public void loadSubtasks(ComboBoxModel<SubTask> model) {
		// TODO Auto-generated method stub

	}

}
