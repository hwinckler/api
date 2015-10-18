package ex.endpoint.url;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;

public class UrlLocator {

	private final Logger logger = Logger.getLogger(UrlLocator.class);
	
	private final String URL_PROPERTIES = "url.properties";
	private Properties properties = null;
	
	private String url;
	
	public UrlLocator() {
		logger.info("UrlLocator()");

		configura();
	}
	
	private void configura() {
		logger.debug("configura()");
		
		URL resource = this.getClass().getClassLoader().getResource(URL_PROPERTIES);
		
		try(InputStream is = new FileInputStream(resource.getFile())){
			
			properties = new Properties();
			properties.load(is);
		}
		catch(Exception e){
			logger.error("configura", e);
		}
		
		this.url = properties.getProperty("url");
	}
	
	public String getUrl() {
		return this.url;
	}
}
