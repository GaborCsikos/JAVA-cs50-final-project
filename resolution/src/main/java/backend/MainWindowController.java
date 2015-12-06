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

	public void reCalculate() {
		if (model != null) {
			for (Resolution res : model.getResolutions()) {
				if (isAllSubtaskFinished(res)) {
					res.setPercentage(100);
				} else {
					int allSubTask = res.getSubtasks().size();
					if (allSubTask != 0) {
						int allpercentage = getSubTaskPercentage(res);
						res.setPercentage(allpercentage / allSubTask);
					}
				}
			}
		}

	}

	private boolean isAllSubtaskFinished(Resolution res) {
		boolean isAllFinished = true;
		for (SubTask subTask : res.getSubtasks()) {
			if (!subTask.isDone()) {
				return false;
			}
		}
		return isAllFinished;
	}

	private int getSubTaskPercentage(Resolution res) {
		int percentage = 0;
		for (SubTask subTask : res.getSubtasks()) {
			percentage += subTask.getPercentage();
		}
		return percentage;
	}

	public Resolution getResolutionById(long id) {
		for (Resolution res : model.getResolutions()) {
			if (id == res.getId()) {
				return res;
			}
		}
		return null;
	}

	public SubTask getSubtaskById(long resolutionId, long subTaskId) {
		for (Resolution res : model.getResolutions()) {
			if (resolutionId == res.getId()) {
				for (SubTask subTask : res.getSubtasks()) {
					if (subTaskId == subTask.getId()) {
						return subTask;
					}
				}
			}
		}
		return null;
	}

	public List<SubTask> getSubTasksByResolutionId(long id) {
		for (Resolution res : model.getResolutions()) {
			if (id == res.getId()) {
				return res.getSubtasks();
			}
		}
		return null;
	}

	public void removeTaskById(long resolutionId, long selectedItemId) {
		for (Resolution res : model.getResolutions()) {
			if (resolutionId == res.getId()) {
				for (SubTask subTask : res.getSubtasks()) {
					if (selectedItemId == subTask.getId()) {
						res.getSubtasks().remove(subTask);
						break;
					}
				}
			}
		}

	}

	public void removeResolutionById(long selectedItemId) {
		for (Resolution res : model.getResolutions()) {
			if (selectedItemId == res.getId()) {
				model.getResolutions().remove(res);
				break;
			}
		}

	}
}
