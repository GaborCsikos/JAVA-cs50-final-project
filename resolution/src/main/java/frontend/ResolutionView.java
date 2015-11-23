/**
 * 
 */
package frontend;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author Gabor Csikos
 * 
 */
public class ResolutionView extends TaskView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton addSubTask;

	public ResolutionView(JFrame frame) {
		super(frame);
	}

	@Override
	void setLabel() {
		taskName.setText("Resolution:");
	}

	@Override
	void setAlltask() {
		// TODO Auto-generated method stub

	}

}
