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

	private long id;
	private static long idCounter = 0;

	public Task() {
		idCounter++;
		setId(idCounter);
	}

	private int percentage;

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

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
