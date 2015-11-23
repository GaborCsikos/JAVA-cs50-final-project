package frontend;

import javax.swing.JFrame;

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

	public SubtaskView(JFrame frame) {
		super(frame);
	}

	@Override
	void setLabel() {
		taskName.setText("Task:");
	}

	@Override
	void setAlltask() {
		// TODO Auto-generated method stub

	}

}
