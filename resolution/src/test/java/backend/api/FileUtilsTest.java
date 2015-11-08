/**
 * 
 */
package backend.api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit tests of {@link FileUtils}
 * 
 * @author Gabor Csikos
 * 
 */
public class FileUtilsTest {

	private static final String dummy = "dummy";

	@Test(expected = EmptyFullPathException.class)
	public void testFullPathNull() throws EmptyFullPathException {
		FileUtils.getFullPath(null, null);
	}

	@Test(expected = EmptyFullPathException.class)
	public void testFullPathEmpty() throws EmptyFullPathException {
		FileUtils.getFullPath("", "");
	}

	@Test(expected = EmptyFullPathException.class)
	public void testFilenameNull() throws EmptyFullPathException {
		FileUtils.getFullPath(dummy, null);
	}

	@Test(expected = EmptyFullPathException.class)
	public void testFilenameEmpty() throws EmptyFullPathException {
		FileUtils.getFullPath(dummy, "");
	}

	@Test(expected = EmptyFullPathException.class)
	public void testPathNull() throws EmptyFullPathException {
		FileUtils.getFullPath(null, dummy);
	}

	@Test(expected = EmptyFullPathException.class)
	public void testPathEmpty() throws EmptyFullPathException {
		FileUtils.getFullPath("", dummy);
	}

	@Test
	public void testFullPath() throws EmptyFullPathException {
		String path = "/home/test";
		String filename = "Test";
		String actual = FileUtils.getFullPath(path, filename);
		String expected = path + "/" + filename + "." + FileUtils.FILE_TYPE;
		assertEquals("Full path is Different", expected, actual);
	}

}
