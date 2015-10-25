/**
 * 
 */
package backend.api;

import backend.entity.Year;

/**
 * Service for loading a year
 * 
 * @author Gabor Csikos
 * 
 */
public interface Load {

	Year load(int year);
}
