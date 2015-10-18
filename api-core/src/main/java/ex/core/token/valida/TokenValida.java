package ex.core.token.valida;

import java.util.Map;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import ex.core.json.conversor.JSonConversor;
import ex.core.json.conversor.exception.JSonConversorException;
import ex.core.token.Token;
import ex.core.token.exception.TokenException;

public class TokenValida {

	private final Logger logger = Logger.getLogger(TokenValida.class);
	
	@Inject
	private JSonConversor jsonConversor;
	
	@Inject
	private Token token;
	
	public void valida(String auth) throws TokenException, JSonConversorException {
		logger.info("valida()");
		
		Map dados = jsonConversor.converte(token.getPayload(auth));
		
		logger.debug(dados);
		
		if(!dados.get("login").equals("aaa")){
			throw new TokenException();
		}
	}

}
