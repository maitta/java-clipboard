package maitta.git.javaclipboard;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import maitta.git.javaclipboard.MainFactory;
import maitta.git.javaclipboard.Start;
import maitta.git.javaclipboard.services.ConfigService;
import maitta.git.javaclipboard.services.CypherService;
import maitta.git.javaclipboard.services.DirectoryBrowserService;
import maitta.git.javaclipboard.services.FileService;
import maitta.git.javaclipboard.services.ConfigService.Action;

public class StartTest {
	Start s;
	@Mock
	ConfigService cs;
	@Mock
	FileService fs;
	@Mock
	DirectoryBrowserService dbs;
	@Mock
	CypherService cys;
	@Mock
	MainFactory mf;
	    
	@Before
	public void setUp() throws Exception {
		if(s == null) {
			s = new Start();
			MockitoAnnotations.initMocks(this);
		}
		doNothing().when(fs).parseAndCypherFiles(cs, dbs, cys);
		doNothing().when(fs).decypherDumpFile(cs, cys);
		doNothing().when(fs).generateFileStructure(cs);
	}

	@Test
	public void startCypherTest() {
		when(cs.getAction()).thenReturn(Action.ENCRYPT);		
		s.start(cs, fs, dbs, cys);
		verify(fs, times(1)).parseAndCypherFiles(cs, dbs, cys);
	}
	
	@Test
	public void startDecypherTest() {
		when(cs.getAction()).thenReturn(Action.DECRYPT);		
		s.start(cs, fs, dbs, cys);
		verify(fs, times(1)).decypherDumpFile(cs, cys);
	}
	
	@Test
	public void startGenerateFileStructureTest() {
		when(cs.getAction()).thenReturn(Action.REGENERATE);		
		s.start(cs, fs, dbs, cys);
		verify(fs, times(1)).generateFileStructure(cs);
	}
	
	@Test
	public void startFactoryTest() throws FileNotFoundException {	
		Start startSpy = spy(s);
		when(mf.getConfigService()).thenReturn(cs);
		when(mf.getFileService()).thenReturn(fs);
		when(mf.getDirectoryBrowserService()).thenReturn(dbs);
		when(mf.getCypherService()).thenReturn(cys);
		doNothing().when(startSpy).start(cs, fs, dbs, cys);
		startSpy.start(mf);
		verify(startSpy, times(1)).start(cs, fs, dbs, cys);
	}
	
	@Test
	public void startFactoryFileExceptionTest() throws FileNotFoundException{	
		Start startSpy = spy(s);
		when(mf.getConfigService()).thenThrow(FileNotFoundException.class);
		doNothing().when(startSpy).start(cs, fs, dbs, cys);
		// TODO check that logger gets called with message (PowerMock)
		//s.start(mf);
		
	}
	
	@Test
	public void startFactoryExceptionTest() throws Exception{	
		Start startSpy = spy(s);
		when(mf.getFileService()).thenThrow(Exception.class);
		doNothing().when(startSpy).start(cs, fs, dbs, cys);
		// TODO check that logger gets called with message (PowerMock)
		//s.start(mf);
		
	}
	
}
