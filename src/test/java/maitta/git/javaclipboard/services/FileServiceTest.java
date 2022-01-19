package maitta.git.javaclipboard.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import maitta.git.javaclipboard.services.ConfigService;
import maitta.git.javaclipboard.services.FileService;

public class FileServiceTest {
	FileService fs;
	@Mock
	ConfigService cs;

	@Before
	public void setUp() {
		if(fs == null) {
			fs = new FileService();
			MockitoAnnotations.initMocks(this);
		}
	}

	@Test
	public void generateFileStructureTest() {
		FileService fsSpy = spy(fs);
		when(cs.getPathToClearFile()).thenReturn("dummyPath");
		//TODO either use powermockito to mock static fileutils methods or
		//integrate testing instead of unit testing this method.
		//fsSpy.generateFileStructure(cs);
	}

	@Test
	public void readFileTest() {
		//TODO readAllBytes apache commons static method called. Powermockito
		//or integration tests with real files and everything.
	}

	@Test
	public void parseAndCypherFilesTest() {
		//TODO writeStringToFile apache commons static method called.
		//Powermockito or real thing.
	}

	@Test
	public void decypherDumpFileTest() {
		//TODO writeStringToFile apache commons static method called.
		//Powermockito or real thing.
	}

}
