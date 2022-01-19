package maitta.git.javaclipboard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import maitta.git.javaclipboard.services.ConfigService;
import maitta.git.javaclipboard.services.CypherService;
import maitta.git.javaclipboard.services.DirectoryBrowserService;
import maitta.git.javaclipboard.services.FileService;

public class MainFactory {
	private final String pathToFile = "." + File.separator + "app.config";
	
	public ConfigService getConfigService() throws FileNotFoundException {
		Properties properties = new Properties();
		FileInputStream fis = getConfigFileStream();
		ConfigService cs = new ConfigService(fis, properties);
		return cs;
	}
	
	public FileService getFileService() {    	        
        FileService fs = new FileService();
        return fs;
	}
	
	public DirectoryBrowserService getDirectoryBrowserService() {    	        
		DirectoryBrowserService dbs = new DirectoryBrowserService();
        return dbs;
	}
	
    private FileInputStream getConfigFileStream() throws FileNotFoundException {
    	File file = new File(pathToFile);
    	FileInputStream configFileStream = new FileInputStream(file);
    	return configFileStream;
    }

	public CypherService getCypherService() {
		CypherService cys = new CypherService();
		return cys;
	}
}
