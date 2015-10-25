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
public class Resolution implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean done;
	private String name;

	private List<SubTask> subtasks = new ArrayList<>();

	public List<SubTask> getSubtasks() {
		return subtasks;
	}

	public void setSubtasks(List<SubTask> subtasks) {
		this.subtasks = subtasks;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
