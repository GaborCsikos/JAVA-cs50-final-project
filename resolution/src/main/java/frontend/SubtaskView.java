package frontend;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;

import backend.MainWindowController;
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
	private long resolutionId;

	public SubtaskView(JFrame frame, MainWindowController controller,
			long resolutionId) {
		super(frame, controller, false, resolutionId);
		this.resolutionId = resolutionId;
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
			controller.getResolutionById(resolutionId).getSubtasks()
					.add((SubTask) subtaskToAdd);
		}
	}

	@Override
	void deleteTask(long selectedItemId) {
		controller.getResolutionById(resolutionId).getSubtasks()
				.remove(selectedItemId);
	}

	@Override
	void updateTask(long selectedItemId) {
		String name = controller.getSubtaskById(resolutionId, selectedItemId)
				.getName();
		String subTask = JOptionPane.showInputDialog("Edit SubTask:" + name);
		if (!StringUtils.isEmpty(subTask)) {
			controller.getSubtaskById(resolutionId, selectedItemId).setName(
					subTask);
		}
	}

	@Override
	void setState(long selectedItemIndex, boolean isDone) {
		controller.getSubtaskById(resolutionId, selectedItemIndex).setDone(
				isDone);
	}

	@Override
	void setPercentage(long selectedItemIndex, int percentage) {
		controller.getSubtaskById(resolutionId, selectedItemIndex)
				.setPercentage(percentage);
	}

}
