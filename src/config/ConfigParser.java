package config;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigParser extends Properties {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4819161035935624884L;
	String result = "";
	InputStream inputStream;
	
	public ConfigParser(String fileName) throws IOException{
		getPropValues(fileName);
		
	}
	public void getPropValues(String fileName) throws IOException {	
		try {
			inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

			if (inputStream != null) {
				this.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + fileName + "' not found.");
			}

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}		
	}

	public int get (String key){
		return Integer.parseInt(this.getProperty(key));
	}
	
}