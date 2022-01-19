package maitta.git.javaclipboard;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import maitta.git.javaclipboard.MainFactory;
import maitta.git.javaclipboard.services.ConfigService;
import maitta.git.javaclipboard.services.FileService;

public class MainFactoryTest {
	MainFactory mf;
	
	@Before
	public void setUp() throws Exception {
		if(mf == null) {
			mf = new MainFactory();
		}
	}

	@Test
	public void getFileServiceTest() {
		FileService fs = mf.getFileService();
		assertNotNull(fs);
	}
	
	@Test
	public void getConfigServiceTest() throws FileNotFoundException {
		ConfigService cs = mf.getConfigService();
		assertNotNull(cs);
	}
}
