/**
 * 
 */
package backend;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import backend.api.FileReturnType;
import backend.api.FileUtils;
import backend.entity.Year;

/**
 * Unit test of {@link YearService}
 * 
 * @author Gabor Csikos
 * 
 */
public class YearServiceTest {

	private static final String filePath = "/home/csikirustu/test";
	private static final String fileName = "TestYear";
	private static final int TEST_YEAR = 2015;
	private YearService service;
	private Year year;
	private String fullPath;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		service = new YearService();
		year = new Year(TEST_YEAR);
		fullPath = FileUtils.getFullPath(filePath, fileName);
	}

	@Test
	public void testSave() {
		FileReturnType actual = service.save(filePath, fileName, year);
		assertEquals("Saving failed", FileReturnType.SUCCESS, actual);
	}

	@Test
	public void testLoad() {
		testSave(); // depends on save
		File file = new File(fullPath);
		Year loadedYear = service.load(file);
		assertEquals("Bad year loaded", 2015, loadedYear.getYear());
	}
}
