package ex.core.login.valida;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import ex.core.json.conversor.JSonConversor;
import ex.core.json.conversor.exception.JSonConversorException;
import ex.core.login.exception.LoginInvalidoException;
import ex.core.token.Token;
import ex.core.token.exception.TokenException;

public class LoginValida {

	private final Logger logger = Logger.getLogger(LoginValida.class);
	
	@Inject
	private JSonConversor jsonConversor;
	
	@Inject
	private Token token;
	
	public String autentica(String login, String senha) throws TokenException, JSonConversorException, LoginInvalidoException {
		logger.info("autentica()");
		
		if((login == null || login.isEmpty()) || (senha == null || senha.isEmpty()))
			throw new LoginInvalidoException("Login invalido!");
		
		Map<String, String> payload = new HashMap<String, String>();
		payload.put("login", login);
		payload.put("senha", senha);
		payload.put("data", Calendar.getInstance().getTimeInMillis()+"");
		
		String json = jsonConversor.converte(payload);
		
		return "{\"token\":\""+token.getToken(json)+"\"}";
	}

}
