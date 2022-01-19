package maitta.git.javaclipboard.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import maitta.git.javaclipboard.services.DirectoryBrowserService;

public class DirectoryBrowserServiceTest {
	DirectoryBrowserService dbs;
	@Mock
	File seedDir;
	@Mock
	File resFile1, resFile2, resDir;
	File[] fArray;
	String RES_FILE1_PATH = "dummyPath1";
	String RES_FILE2_PATH = "dummyPath2";

	@Before
	public void setUp() throws Exception {
		if(dbs == null) {
			MockitoAnnotations.initMocks(this);
			dbs = new DirectoryBrowserService();
			fArray = new File[2];
			fArray[0] = resFile1;
			fArray[1] = resFile2;			
			when(resFile1.isFile()).thenReturn(true);
			when(resFile2.isFile()).thenReturn(true);
			when(resFile1.getAbsolutePath()).thenReturn(RES_FILE1_PATH);
			when(resFile2.getAbsolutePath()).thenReturn(RES_FILE2_PATH);
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void getDirectoryFileListExceptionTest() {
		when(seedDir.isDirectory()).thenReturn(false);
		dbs.getDirectoryFileList(seedDir);
	}

	@Test
	public void getDirectoryFileListTest() {		
		when(seedDir.isDirectory()).thenReturn(true);		
		when(seedDir.listFiles()).thenReturn(fArray);
		List<String> resList = dbs.getDirectoryFileList(seedDir);
		assertTrue(resList.contains(RES_FILE1_PATH));
		assertTrue(resList.contains(RES_FILE2_PATH));
	}
	
	@Test
	public void getDirectoryFileListRecursiveTest() {
		when(seedDir.isDirectory()).thenReturn(true);		
		File[] dArray = new File[1];
		dArray[0] = resDir;
		when(seedDir.listFiles()).thenReturn(dArray);
		when(resDir.isFile()).thenReturn(false);
		when(resDir.isDirectory()).thenReturn(true);
		when(resDir.listFiles()).thenReturn(fArray);
		List<String> resList = dbs.getDirectoryFileList(seedDir);
		assertTrue(resList.contains(RES_FILE1_PATH));
		assertTrue(resList.contains(RES_FILE2_PATH));
	}
}
