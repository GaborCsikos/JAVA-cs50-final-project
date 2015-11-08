/**
 * 
 */
package backend.api;

import java.io.File;

import backend.entity.Year;

/**
 * Service for loading a year
 * 
 * @author Gabor Csikos
 * 
 */
public interface Load {

	Year load(File file);
}
