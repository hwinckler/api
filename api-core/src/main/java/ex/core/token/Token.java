package ex.core.token;

import java.util.Map;

import ex.core.json.conversor.exception.JSonConversorException;
import ex.core.token.exception.TokenException;

public interface Token {

	public String getToken(String payload) throws TokenException;
	public String getPayload(String token) throws TokenException, JSonConversorException;
}
