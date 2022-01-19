package maitta.git.javaclipboard;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import maitta.git.javaclipboard.Main;
import maitta.git.javaclipboard.MainFactory;
import maitta.git.javaclipboard.Start;

public class MainTest {
	Main main;
	@Spy
	Start s;
	@Mock
	MainFactory mf;
	String[] args;
	
	@Before
	public void setup() {
		if(main == null) {			
			MockitoAnnotations.initMocks(this);
			main = new Main();
			args = new String[] {"dummy"};
		}
	}
	
	@Test
	public void mainCallsStart() {
		// TODO IMPORTANT create integration test since main(args) is static
		//Main.main(args);
		doNothing().when(s).start(any(MainFactory.class));
		//verify(s).start(any(MainFactory.class));
	}
}
