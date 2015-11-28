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

	public ResolutionView(JFrame frame, MainWindowController controller) {
		super(frame, controller);
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
			tasks.addItem(resolutionToAdd);
			controller.getResolutions().add((Resolution) resolutionToAdd);
		}

	}

	@Override
	void deleteTask(Object selectedItem) {
		if (selectedItem != null && selectedItem instanceof Resolution) {
			tasks.removeItem((Task) selectedItem);
			controller.getResolutions().remove((Resolution) selectedItem);
		}

	}

	@Override
	void updateTask(Object selectedItem) {
		if (selectedItem != null && selectedItem instanceof Resolution) {
			String name = ((Resolution) selectedItem).getName();
			String resolution = JOptionPane.showInputDialog("Edit Resolution:"
					+ name);
			if (!StringUtils.isEmpty(resolution)) {
				deleteTask(selectedItem);
				createTask(resolution);
			}
		}
	}

	private void createTask(String resolutionName) {
		Task resolutionToAdd = new Resolution();
		resolutionToAdd.setName(resolutionName);
		tasks.addItem(resolutionToAdd);
		controller.getResolutions().add((Resolution) resolutionToAdd);
	}
}
