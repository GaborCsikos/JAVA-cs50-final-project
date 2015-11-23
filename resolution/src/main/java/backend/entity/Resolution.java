/**
 * 
 */
package backend.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Resolution to do
 * 
 * @author Gabor Csikos
 * 
 */
public class Resolution extends Task implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<SubTask> subtasks = new ArrayList<>();

	public List<SubTask> getSubtasks() {
		return subtasks;
	}

	public void setSubtasks(List<SubTask> subtasks) {
		this.subtasks = subtasks;
	}

}
