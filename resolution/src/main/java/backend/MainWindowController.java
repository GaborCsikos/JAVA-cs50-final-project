package backend;

import java.io.File;
import java.util.List;

import backend.api.FileUtils;
import backend.api.NothingToSaveException;
import backend.entity.Resolution;
import backend.entity.SubTask;
import backend.entity.Year;
import frontend.MainWindowView;

/**
 * Controller for year handling
 * 
 * @author Gabor Csikos
 * 
 */
public class MainWindowController {
	private MainWindowModel model;
	private MainWindowView view;

	public MainWindowController() {
		model = new MainWindowModel();
	}

	public void start() {
		model.start();

	}

	public void saveYear(String filePath) {
		try {
			model.saveYear(filePath);
		} catch (NothingToSaveException exeption) {
			view.showMessage("No file Selected to save");
		}

	}

	public void setView(MainWindowView view) {
		this.view = view;

	}

	public void checkNumber(String result) {
		if (!result.isEmpty()) {
			try {
				int year = Integer.parseInt(result);
				if (year > 2015) {
					model.createYear(year);
					view.setTitle(result);
				} else {
					view.showMessage("Please select a year from 2016");
				}

			} catch (NumberFormatException e) {
				view.showMessage("Please select a valid year");
			}
		}
	}

	public String getName() {
		String year = Integer.toString(model.getYear());
		String name = year + FileUtils.FILTER;
		return name;
	}

	public boolean isYearSet() {
		if (model.getYearObject() != null) {
			return true;
		}
		return false;
	}

	public void loadYear(File file) {
		Year loadedYear = model.loadYear(file);
		if (loadedYear != null) {
			model.setYearObject(loadedYear);
		} else {
			view.showMessage("Couldn't load year");
		}

	}

	public List<Resolution> getResolutions() {
		return model.getResolutions();
	}

	public List<SubTask> loadSubTasks(int index) {
		return model.getSubtasks(index);
	}
}
