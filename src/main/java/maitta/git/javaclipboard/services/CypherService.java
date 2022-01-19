package maitta.git.javaclipboard.services;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class CypherService {
	private static final Logger logger = LogManager.getLogger();
	protected final static String ALGORITHM = "Blowfish";
	// Read from config file?
	protected final static String STRKEY = "$The Secret Key for Encryption!";
	
    public String encrypt(String strClearText) {    	
        return doOperation(true, strClearText);
    }

    public String decrypt(String strEncrypted){
        return doOperation(false, strEncrypted);
    }
    
    /**
     * 
     * @param isEncryption true for encryption, false for decryption
     * @return data in its appropriate format
     */
    private String doOperation(boolean isEncryption, String inputData) {
    	String strData = "";
    	try {
    		SecretKeySpec skeyspec = new SecretKeySpec(STRKEY.getBytes(), 
            		ALGORITHM);
    		Cipher cipher = Cipher.getInstance(ALGORITHM);
    		
    		if(isEncryption) {
                cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
                byte[] encrypted = cipher.doFinal(inputData.getBytes());
                strData = Base64.encodeBase64String(encrypted);    		
        	}else {
                cipher.init(Cipher.DECRYPT_MODE, skeyspec);
                byte[] decrypted = cipher.doFinal(
                			Base64.decodeBase64(inputData.getBytes()));
                strData = new String(decrypted);
        	}    	
    	}
    	catch(NoSuchPaddingException e) {
	        logger.error("Environment does not support chosen padding.", e);
	    } 
	    catch(NoSuchAlgorithmException e) {
	    	logger.error
	    		("Environment does not support algorithm " + ALGORITHM, e);
	    } 
	    catch(InvalidKeyException e) {
	    	logger.error("Invalid key encoding.", e);
	    }
	    catch(BadPaddingException e) {
	    	logger.error("Wrong padding for input.", e);
	    }
	    catch(IllegalBlockSizeException e) {
	    	logger.error("Cannot encrypt, data length mismatch", e);
	    }           	
    	return strData;
    }
}