/**
 * 
 */
package backend.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Year representation
 * @author Gabor Csikos
 *
 */
public class Year {

	private List<Resolution> resolutions = new ArrayList<>();

	public List<Resolution> getResolutions() {
		return resolutions;
	}

	public void setResolutions(List<Resolution> resolutions) {
		this.resolutions = resolutions;
	}
}
