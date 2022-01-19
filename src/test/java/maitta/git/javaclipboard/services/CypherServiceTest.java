package maitta.git.javaclipboard.services;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import maitta.git.javaclipboard.services.CypherService;

public class CypherServiceTest {
	CypherService cphs;
	
	private static final String inputData = "some dummy text";
	private static final String outputData = "TK6vA27efaLHwmLjNPEtYA==";

	@Before
	public void setUp() {
		if(cphs == null) {
			cphs = new CypherService();
		}
	}

	@Test
	public void encryptTest() {
		String res = cphs.encrypt(inputData);
		assertEquals(outputData, res);
	}
	
	@Test
	public void encryptOutputTest() {
		String res = cphs.encrypt(outputData);
		assertNotEquals(outputData, res);
		assertNotEquals(inputData, res);
	}
	
	@Test
	public void decryptTest() {
		String res = cphs.decrypt(outputData);
		assertEquals(inputData, res);
	}
	
	@Test
	public void decryptInputTest() {
		String res = cphs.decrypt(inputData);
		assertEquals(res, "");
	}
}
