/**
 * 
 */
package backend.api;

/**
 * Utils for Files
 * 
 * @author Gabor Csikos
 * 
 */
public final class FileUtils {
	private static final String FILE_TYPE = "year";

	private FileUtils() {

	}

	public static String getFullPath(String path, String fileName) {
		return path + fileName + "." + FILE_TYPE;
	}
}
