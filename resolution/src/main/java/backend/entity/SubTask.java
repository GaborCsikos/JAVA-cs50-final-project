/**
 * 
 */
package backend.entity;

import java.io.Serializable;

/**
 * Subtask of the resolution
 * 
 * @author Gabor Csikos
 * 
 */
public class SubTask implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private boolean done;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
}
