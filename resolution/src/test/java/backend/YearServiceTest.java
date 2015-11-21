/**
 * 
 */
package backend;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import backend.api.FileReturnType;
import backend.entity.Year;

/**
 * Module test of {@link YearService}
 * 
 * @author Gabor Csikos
 * 
 */
public class YearServiceTest {

	private static final String filePath = "/home/csikirustu/test/TestYear.year"; // needs
																					// to
																					// be
	private static final int TEST_YEAR = 2015;
	private YearService service;
	private Year year;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		service = new YearService();
		year = new Year(TEST_YEAR);
	}

	@Test
	public void testSave() {
		FileReturnType actual = service.save(filePath, year);
		assertEquals("Saving failed", FileReturnType.SUCCESS, actual);
	}

	@Test
	public void testLoad() {
		testSave(); // depends on save
		File file = new File(filePath);
		Year loadedYear = service.load(file);
		assertEquals("Bad year loaded", 2015, loadedYear.getYear());
	}
}
