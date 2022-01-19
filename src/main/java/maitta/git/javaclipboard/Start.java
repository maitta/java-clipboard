package maitta.git.javaclipboard;

import java.io.FileNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import maitta.git.javaclipboard.services.ConfigService;
import maitta.git.javaclipboard.services.CypherService;
import maitta.git.javaclipboard.services.DirectoryBrowserService;
import maitta.git.javaclipboard.services.FileService;
import maitta.git.javaclipboard.services.ConfigService.Action;

public class Start{
	private static final Logger logger = LogManager.getLogger();
	
	public void start(ConfigService cs, 
			FileService fs, 
			DirectoryBrowserService dbs,
			CypherService cys) {
    	Action action = cs.getAction();
    	switch(action){
	        case ENCRYPT:
	            fs.parseAndCypherFiles(cs, dbs, cys);
	            logger.info("Files parsed successfully, encrypted file was created.");
	            break;
	        case DECRYPT:
	            fs.decypherDumpFile(cs, cys);
	            logger.info("Encrypted file processed correctly.");
	            break;
	        case REGENERATE:
	            fs.generateFileStructure(cs);
	            logger.info("Folder structure and files' contents have been recreated.");
	            break;
	        default: break;
	    }      
    }
	
	/**
	 * Abstracts the creation of config and file services then calls
	 * {@link #start(ConfigService, FileService)}
	 * @param mf
	 */
	public void start(MainFactory mf) {   	
    	ConfigService cs = null;
    	FileService fs = null;
    	DirectoryBrowserService dbs = null;
    	CypherService cys = null;
    	try {
			cs = mf.getConfigService();
			fs = mf.getFileService();
			dbs = mf.getDirectoryBrowserService();
			cys = mf.getCypherService();
		}
		catch(FileNotFoundException e) {
			logger.error("Configuration file not found.", e);
		}
		catch(Exception e) {
			logger.error("Unknow exception occured while " + 
							"reading the configuration file.", e);
		}
        start(cs, fs, dbs, cys);  
	}
}