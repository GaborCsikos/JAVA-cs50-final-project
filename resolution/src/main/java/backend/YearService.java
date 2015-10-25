/**
 * 
 */
package backend;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import backend.api.FileType;
import backend.api.FileUtils;
import backend.api.Load;
import backend.api.Save;
import backend.entity.Year;

/**
 * @author Gabor Csikos
 * 
 */
public class YearService implements Save, Load {

	/*
	 * (non-Javadoc)
	 * 
	 * @see backend.api.Load#load(int)
	 */
	@Override
	public Year load(int year) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see backend.api.Save#save(backend.entity.Year)
	 */
	@Override
	public FileType save(String filePath, String fileName, Year year) {
		try {
			FileOutputStream fileOut = new FileOutputStream(
					FileUtils.getFullPath(filePath, fileName));
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(year);
			out.close();
			fileOut.close();
			return FileType.SUCCESS;
		} catch (IOException i) {
			i.printStackTrace();
			return FileType.FAILED;
		}

	}
}
