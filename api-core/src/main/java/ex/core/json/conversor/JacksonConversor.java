package ex.core.json.conversor;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import ex.core.json.conversor.exception.JSonConversorException;

@Singleton
public class JacksonConversor implements JSonConversor {
	
	private final Logger logger = Logger.getLogger(JacksonConversor.class);

	public JacksonConversor() {
		logger.info("JacksonConversor");
	}
	
	public String converte(Object object) throws JSonConversorException {

		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (Exception e) {
			throw new JSonConversorException(e);
		}
	}
	
	public Map converte(String json) throws JSonConversorException {

		try {
			return new ObjectMapper().readValue(json, HashMap.class);
		} catch (Exception e) {
			throw new JSonConversorException(e);
		}
	}

}
