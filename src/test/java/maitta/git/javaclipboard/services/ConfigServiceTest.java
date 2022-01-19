package maitta.git.javaclipboard.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import maitta.git.javaclipboard.services.ConfigService;
import maitta.git.javaclipboard.services.ConfigService.Action;

public class ConfigServiceTest {
	ConfigService cs;
	@Mock
	FileInputStream fs;
	@Mock
	Properties props;
	
	protected static final String CURRENT_FOLDER = new File("").getAbsolutePath() + "\\";

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getActionExceptionTest() {
		when(props.get(ConfigService.ACTION)).thenReturn("dummy");
		cs = new ConfigService(fs, props);
		cs.getAction();
	}
	
	@Test
	public void getActionCypherTest() {
		when(props.get(ConfigService.ACTION)).thenReturn("doEncrypt");
		cs = new ConfigService(fs, props);
		Action res = cs.getAction();
		assertEquals(res, Action.ENCRYPT);
	}

	@Test
	public void getActionRegenerateTest() {
		when(props.get(ConfigService.ACTION)).thenReturn("doRegenerate");
		cs = new ConfigService(fs, props);
		Action res = cs.getAction();
		assertEquals(res, Action.REGENERATE);
	}
	
	@Test
	public void getActionDecypherTest() {
		when(props.get(ConfigService.ACTION)).thenReturn("doDecrypt");
		cs = new ConfigService(fs, props);
		Action res = cs.getAction();
		assertEquals(res, Action.DECRYPT);
	}
	
	@Test
	public void configServiceConstructorTest() {
		String seed = "seedFolder";
		String dump = "dumpFile";
		String clear = "clearFile";
		when(props.get(ConfigService.SEED_FOLDER)).thenReturn(seed);
		when(props.get(ConfigService.PATH_TO_DUMP_FILE)).thenReturn(dump);
		when(props.get(ConfigService.PATH_TO_CLEAR_FILE)).thenReturn(clear);
		cs = new ConfigService(fs, props);
		assertEquals(CURRENT_FOLDER + seed, cs.getSeedFolder());
		assertEquals(CURRENT_FOLDER + dump, cs.getPathToDumpFile());
		assertEquals(CURRENT_FOLDER + clear, cs.getPathToClearFile());
	}
	
	@Test(expected = IOException.class)
	public void configServiceConstructorExceptionTest() throws IOException {
		// TODO
		throw new IOException();
	}
}
