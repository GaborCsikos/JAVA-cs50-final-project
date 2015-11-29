package frontend;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;

import backend.MainWindowController;
import backend.entity.Resolution;
import backend.entity.SubTask;
import backend.entity.Task;

/**
 * 
 * @author Gabor Csikos
 * 
 */
public class SubtaskView extends TaskView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int resolutionIndex;

	public SubtaskView(JFrame frame, MainWindowController controller,
			int resolutionIndex) {
		super(frame, controller);
		this.resolutionIndex = resolutionIndex;
	}

	@Override
	void setLabel() {
		taskName.setText("Task:");
	}

	@Override
	void createTask() {
		String subtask = JOptionPane.showInputDialog("Task:");
		if (!StringUtils.isEmpty(subtask)) {
			Task subtaskToAdd = new SubTask();
			subtaskToAdd.setName(subtask);
			tasks.addItem(subtaskToAdd);
			controller.getResolutions().get(resolutionIndex).getSubtasks()
					.add((SubTask) subtaskToAdd);
		}
	}

	@Override
	void deleteTask(Object selectedItem) {
		if (selectedItem != null && selectedItem instanceof SubTask) {
			tasks.removeItem((Task) selectedItem);
			controller.getResolutions().get(resolutionIndex).getSubtasks()
					.remove((SubTask) (Task) selectedItem);
		}

	}

	@Override
	void updateTask(Object selectedItem) {
		if (selectedItem != null && selectedItem instanceof SubTask) {
			String name = ((SubTask) selectedItem).getName();
			String subTask = JOptionPane
					.showInputDialog("Edit SubTask:" + name);
			if (!StringUtils.isEmpty(subTask)) {
				deleteTask(selectedItem);
				createTask(subTask);
			}
		}
	}

	private void createTask(String resolutionName) {
		Task resolutionToAdd = new Resolution();
		resolutionToAdd.setName(resolutionName);
		tasks.addItem(resolutionToAdd);
		controller.getResolutions().add((Resolution) resolutionToAdd);
	}

	@Override
	void setState(int index, boolean isDone) {
		controller.getResolutions().get(resolutionIndex).getSubtasks()
				.get(index).setDone(isDone);
		if (isDone) {
			currentPercentage.setValue(100);
			controller.getResolutions().get(resolutionIndex).getSubtasks()
					.get(index).setPercentage(100);
			percentagelabel.setText(100 + "%");
		} else if (!isDone && currentPercentage.getValue() == 100) {
			currentPercentage.setValue(0);
			percentagelabel.setText(0 + "%");
			controller.getResolutions().get(resolutionIndex).getSubtasks()
					.get(index).setPercentage(0);
		}
	}

	@Override
	void setPercentage(int selectedItemIndex, int percentage) {
		controller.getResolutions().get(resolutionIndex).getSubtasks()
				.get(selectedItemIndex).setPercentage(percentage);
		percentagelabel.setText(percentage + "%");
		currentPercentage.setValue(percentage);
	}

	@Override
	void loadState(int selectedIndex) {
		SubTask subTask = controller.getResolutions().get(resolutionIndex)
				.getSubtasks().get(selectedIndex);
		currentPercentage.setValue(subTask.getPercentage());
		done.setSelected(subTask.isDone());
		percentagelabel.setText(subTask.getPercentage() + "%");
	}
}
