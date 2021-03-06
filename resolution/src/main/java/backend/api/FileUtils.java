/**
 * 
 */
package backend.api;

import org.apache.commons.lang3.StringUtils;

/**
 * Utils for Files
 * 
 * @author Gabor Csikos
 * 
 */
public final class FileUtils {
	public static final String FILE_TYPE = "year";
	public static final String FILTER = "." + FILE_TYPE;

	private FileUtils() {

	}

	/**
	 * 
	 * @param path
	 *            the path
	 * @param fileName
	 *            the filename
	 * @throws EmptyFullPathException
	 * @return
	 */
	public static String getFullPath(String path, String fileName)
			throws EmptyFullPathException {
		if (StringUtils.isEmpty(path) || StringUtils.isEmpty(fileName)) {
			throw new EmptyFullPathException();
		}
		return path + "/" + fileName;
	}

	public static boolean isFileTypeGood(String file) {
		if (!StringUtils.isEmpty(file)) {
			if (file.endsWith(FILTER)) {
				return true;
			}
		}
		return false;
	}
}
