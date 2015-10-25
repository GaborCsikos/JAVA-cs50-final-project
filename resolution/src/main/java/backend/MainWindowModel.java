/**
 * 
 */
package backend;

import backend.entity.Year;

/**
 * @author Gabor Csikos
 * 
 */
public class MainWindowModel {

	private Year year;
	private YearService service;

	public void saveYear() {
		if (year != null) {
			// service.save(year); TODO save
		} else {
			// TODO set error to view
		}
	}

	public void start() {
		service = new YearService();

	}
}
