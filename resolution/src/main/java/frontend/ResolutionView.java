/**
 * 
 */
package frontend;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;

import backend.MainWindowController;
import backend.entity.Resolution;
import backend.entity.Task;

/**
 * @author Gabor Csikos
 * 
 */
public class ResolutionView extends TaskView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResolutionView(JFrame frame, MainWindowController controller,
			boolean isResolution) {
		super(frame, controller, isResolution, 0);
	}

	@Override
	void setLabel() {
		taskName.setText("Resolution:");
	}

	@Override
	void createTask() {
		String resolution = JOptionPane.showInputDialog("Resolution:");
		if (!StringUtils.isEmpty(resolution)) {
			Task resolutionToAdd = new Resolution();
			resolutionToAdd.setName(resolution);
			controller.getResolutions().add((Resolution) resolutionToAdd);
		}

	}

	@Override
	void deleteTask(long selectedItemId) {
		controller.removeResolutionById(selectedItemId);
	}

	@Override
	void updateTask(long selectedItemId) {
		controller.getResolutionById(selectedItemId);
		String name = controller.getResolutionById(selectedItemId).getName();
		String resolution = JOptionPane.showInputDialog("Edit Resolution:"
				+ name);
		if (!StringUtils.isEmpty(resolution)) {
			controller.getResolutionById(selectedItemId).setName(resolution);
		}
	}

	@Override
	void setState(long selectedItemId, boolean isDone) {
		controller.getResolutionById(selectedItemId).setDone(isDone);
		if (isDone) {
			controller.getResolutionById(selectedItemId).setPercentage(100);
		} else {
			controller.getResolutionById(selectedItemId).setPercentage(0);
		}
	}

	@Override
	void setPercentage(long selectedItemId, int percentage) {
		controller.getResolutionById(selectedItemId).setPercentage(percentage);
		if (percentage == 100) {
			controller.getResolutionById(selectedItemId).setDone(true);
		} else if (percentage < 100) {
			controller.getResolutionById(selectedItemId).setDone(false);
		}
	}

}
