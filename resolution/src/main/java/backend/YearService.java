/**
 * 
 */
package backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import backend.api.EmptyFullPathException;
import backend.api.FileReturnType;
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
	public Year load(File file) {
		try {
			FileInputStream fileInput = new FileInputStream(file);
			ObjectInputStream inputStream = new ObjectInputStream(fileInput);
			Year yearToLoad = (Year) inputStream.readObject();
			inputStream.close();
			return yearToLoad;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see backend.api.Save#save(backend.entity.Year)
	 */
	@Override
	public FileReturnType save(String filePath, String fileName, Year year) {
		try {
			FileOutputStream fileOut = new FileOutputStream(
					FileUtils.getFullPath(filePath, fileName));
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(year);
			out.close();
			fileOut.close();
			return FileReturnType.SUCCESS;
		} catch (IOException i) {
			i.printStackTrace();
			return FileReturnType.FAILED;
		} catch (EmptyFullPathException empty) {
			System.out.println("filePath:" + filePath);
			System.out.println("fileName:" + fileName);
			empty.printStackTrace();
			return FileReturnType.FAILED;
		} catch (Exception e) {
			e.printStackTrace();
			return FileReturnType.FAILED;
		}

	}
}
