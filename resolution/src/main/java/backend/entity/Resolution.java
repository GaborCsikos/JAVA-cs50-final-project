/**
 * 
 */
package backend.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Resolution to do
 * @author Gabor Csikos
 *
 */
public class Resolution {

	private List<SubTask> subtasks = new ArrayList<>();

	public List<SubTask> getSubtasks() {
		return subtasks;
	}

	public void setSubtasks(List<SubTask> subtasks) {
		this.subtasks = subtasks;
	}
}
