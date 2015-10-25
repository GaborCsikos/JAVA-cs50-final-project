/**
 * 
 */
package backend.api;

import backend.entity.Year;

/**
 * Service for saving a year
 * 
 * @author Gabor Csikos
 * 
 */
public interface Save {

	FileType save(String filePath, String fileName, Year year);

}
