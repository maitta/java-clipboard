package maitta.git.javaclipboard.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigService{
	private static final Logger logger = LogManager.getLogger();
	protected static final String CURRENT_FOLDER = new File("").getAbsolutePath() + "\\";
	protected static final String SEED_FOLDER = "seedFolder";
	protected static final String LOG_FOLDER = "logFolder";
	protected static final String ACTION = "action";
	protected static final String PATH_TO_DUMP_FILE = "dumpFile";
	protected static final String PATH_TO_CLEAR_FILE = "clearFile";
	
	public static enum Action{
		ENCRYPT,
		DECRYPT,
		REGENERATE
	}

    private String seedFolder;
    private String action;
    private String pathToDumpFile;
    private String pathToClearFile;
    
    public Action getAction() throws IllegalArgumentException{
    	Action res;
    	switch(action) {
	    	case "doEncrypt":
	    		res = Action.ENCRYPT;
	    		break;
	    	case "doDecrypt":
	    		res = Action.DECRYPT;
	    		break;
	    	case "doRegenerate":
	    		res = Action.REGENERATE;
	    		break;
	    	default:
	    		throw new 
	    			IllegalArgumentException("Unrecognized action.");	
    	}
    	return res;
    }

    public String getSeedFolder(){return CURRENT_FOLDER + seedFolder;}
    public String getPathToDumpFile(){return CURRENT_FOLDER + pathToDumpFile;}
    public String getPathToClearFile(){return CURRENT_FOLDER + pathToClearFile;}

    public ConfigService(
    		FileInputStream configFileStream, 
    		Properties properties){
        try{        	
            properties.load(configFileStream);
            configFileStream.close();

            seedFolder = (String)properties.get(SEED_FOLDER);
            action = (String)properties.get(ACTION);
            pathToDumpFile = (String)properties.get(PATH_TO_DUMP_FILE);
            pathToClearFile = (String)properties.get(PATH_TO_CLEAR_FILE);
            logger.info("Configuration file was read correctly");
		} catch (IOException e) {
			logger.error("Couldn't initialize ConfigService", e);
		}
    }
}