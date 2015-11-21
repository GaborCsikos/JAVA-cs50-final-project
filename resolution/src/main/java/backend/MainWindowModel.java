/**
 * 
 */
package backend;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import backend.api.NothingToSaveException;
import backend.entity.Resolution;
import backend.entity.SubTask;
import backend.entity.Year;

/**
 * Model for Year Handling
 * 
 * @author Gabor Csikos
 * 
 */
public class MainWindowModel {

	private Year year;
	private YearService service;

	public void saveYear(String filePath) throws NothingToSaveException {
		if (year != null) {
			service.save(filePath, year);
		} else {
			throw new NothingToSaveException();
		}
	}

	public void start() {
		service = new YearService();

	}

	public int getYear() {
		return year.getYear();
	}

	public void setYear(int year) {
		this.year.setYear(year);
	}

	public Year getYearObject() {
		return year;
	}

	public void createYear(int yearToSet) {
		year = new Year();
		year.setYear(yearToSet);

	}

	public Year loadYear(File file) {
		return service.load(file);

	}

	public void setYearObject(Year loadedYear) {
		this.year = loadedYear;
		year.setYear(loadedYear.getYear());
	}

	public List<Resolution> getResolutions() {
		if (year != null) {
			return year.getResolutions();
		} else {
			return new ArrayList<Resolution>();
		}
	}

	public List<SubTask> getSubtasks(int index) {
		if (year != null) {
			Resolution res = year.getResolutions().get(index);
			if (res != null) {
				return res.getSubtasks();
			}
		}
		return new ArrayList<SubTask>();
	}
}
