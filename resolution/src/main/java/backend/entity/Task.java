/**
 * 
 */
package backend.entity;

/**
 * @author Gabor Csikos
 * 
 */
public abstract class Task {

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

	@Override
	public String toString() {
		return name;
	}
}
