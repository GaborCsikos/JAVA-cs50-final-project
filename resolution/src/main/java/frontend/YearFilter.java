/**
 * 
 */
package frontend;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import backend.api.FileUtils;

/**
 * @author Gabor Csikos
 * 
 */
public class YearFilter extends FileFilter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		} else {
			String filename = f.getName().toLowerCase();
			return filename.endsWith("." + FileUtils.FILE_TYPE);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.filechooser.FileFilter#getDescription()
	 */
	@Override
	public String getDescription() {
		return ".year";
	}

}
