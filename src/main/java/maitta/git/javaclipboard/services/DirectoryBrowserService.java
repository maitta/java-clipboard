package maitta.git.javaclipboard.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Logger;

import org.apache.logging.log4j.LogManager;

/**
 * Will recursively iterate through a directory in order to get a flat list of 
 * filenames and paths for later conversion.
 */
public class DirectoryBrowserService{
        private static final Logger logger = LogManager.getLogger();

        public List<String> getDirectoryFileList(File folder) 
        		throws IllegalArgumentException {
            if(!folder.isDirectory()){
                logger.error("Source directory provided yielded an error: " 
                		+ folder.getPath());
                throw new 
                	IllegalArgumentException
                	("No valid source directory has been given. " + 
                	"Cannot continue.");
            }            
            List<String> dirList = new ArrayList<String>(); 
            getFileList(folder, dirList);
            return dirList;
        }

        /**
         * Iterate if it's a file push it to the list, if it's a directory then
         * it calls itself.
         * @param folder
         * @param dirList
         */
        private void getFileList(File folder, List<String> dirList){            
            for(final File file : folder.listFiles()){
                if(file.isFile()){
                    dirList.add(file.getAbsolutePath());
                }
                else if(file.isDirectory()){
                    getFileList(file, dirList);
                }
                else{
                    logger.error("Cannot process file: " + file.getName());
                }
            }
        }
}