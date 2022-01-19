package maitta.git.javaclipboard.services;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileService {

	private static final Logger logger = LogManager.getLogger();
	private static final String FILE_SEPARATOR = 
	"#NEW_FILE@@@@@@@@@@##########@@@@@@@@@@##########@@@@@@@@@@##########\r\n";
	private static final String FILE_NAME_CONTENT_SEPARATOR = 
	"#FILE_CONTENT@@@@@@@@@@---------@@@@@@@@@@---------@@@@@@@@@@--------\r\n";

	public void generateFileStructure(ConfigService confSer) {
		String cFile = confSer.getPathToClearFile();
		try {
			String fullClear = readFile(cFile, StandardCharsets.UTF_8);
			String[] fMemory = fullClear.split(FILE_SEPARATOR);
			for (String fm : fMemory) {
				String[] file = fm.split(FILE_NAME_CONTENT_SEPARATOR);
				FileUtils.writeStringToFile(new File(file[0]), file[1], 
						StandardCharsets.UTF_8);
			}
		} catch (IOException e) {
			logger.error("Could not parse dump file.", e);
		} catch (Exception e) {
			logger.error("Error while generating folder structure.", e);
		}
	}

	public String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	public void parseAndCypherFiles(ConfigService confSer, 
			DirectoryBrowserService dbs,
			CypherService cys) {
		String seed = confSer.getSeedFolder();
		File folder = new File(seed);
		List<String> pathList = dbs.getDirectoryFileList(folder);

		HashMap<String, String> contents = new HashMap<String, String>();

		for (String f : pathList) {
			try {
				String co = readFile(f, StandardCharsets.UTF_8);
				contents.put(f, co);
			} catch (IOException e) {
				logger.error("could not read file");
			}
		}
		String dump = "";
		for (Map.Entry<String, String> c : contents.entrySet()) {
			dump += c.getKey() + FILE_NAME_CONTENT_SEPARATOR;
			dump += c.getValue() + "\r\n";
			dump += FILE_SEPARATOR;
		}
		System.out.println(dump);
		try {
			dump = cys.encrypt(dump);
			FileUtils.writeStringToFile(new File(confSer.getPathToDumpFile()), 
					dump, StandardCharsets.UTF_8);
		} catch (IOException e) {
			logger.error("could not write file");
		}
	}

	public void decypherDumpFile(ConfigService confSer, CypherService cys) {
		try {
			String dump = readFile(confSer.getPathToDumpFile(), 
					StandardCharsets.UTF_8);
			dump = cys.decrypt(dump);
			FileUtils.writeStringToFile(new File(confSer.getPathToClearFile()), 
					dump, StandardCharsets.UTF_8);
		} catch (IOException e) {
			logger.error("Could not write file", e);
		} catch (Exception e) {
			logger.error("Could not encrypt text", e);
		}
	}
}
