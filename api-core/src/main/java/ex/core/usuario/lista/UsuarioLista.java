package ex.core.usuario.lista;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import ex.core.json.conversor.JSonConversor;
import ex.core.json.conversor.exception.JSonConversorException;

public class UsuarioLista {

	private final Logger logger = Logger.getLogger(UsuarioLista.class);

	@Inject
	private JSonConversor jsonConversor;
	
	public String lista(Boolean feminino) throws JSonConversorException {
		logger.info("lista()");	
		
		return jsonConversor.converte(getUsuarios(feminino));
	}

	private List<Map<String, String>> getUsuarios(Boolean feminino) {
		logger.info("getUsuarios()");
		
		List<Map<String, String>> usuarios = new ArrayList<Map<String,String>>();
		
		Map<String, String> usu1 = new HashMap<String, String>();
		usu1.put("id", "1");
		usu1.put("login", "usu1");
		usu1.put("sexo", "M");
		
		Map<String, String> usu2 = new HashMap<String, String>();
		usu2.put("id", "2");
		usu2.put("login", "usu2");
		usu2.put("sexo", "F");
		
		Map<String, String> usu3 = new HashMap<String, String>();
		usu3.put("id", "3");
		usu3.put("login", "usu3");
		usu3.put("sexo", "M");
		
		if(!feminino){
			usuarios.add(usu1);			
		}

		usuarios.add(usu2);
		
		if(!feminino){
			usuarios.add(usu3);			
		}
		
		return usuarios;
	}
	
}
