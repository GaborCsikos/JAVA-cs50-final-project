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

	FileReturnType save(String filePath, Year year);

}
