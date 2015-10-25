/**
 * 
 */
package backend.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Year representation
 * 
 * @author Gabor Csikos
 * 
 */
public class Year implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int year;

	private List<Resolution> resolutions = new ArrayList<>();

	public List<Resolution> getResolutions() {
		return resolutions;
	}

	public void setResolutions(List<Resolution> resolutions) {
		this.resolutions = resolutions;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
