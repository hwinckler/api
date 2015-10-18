package ex.endpoint.login;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import ex.core.login.exception.LoginInvalidoException;
import ex.core.login.valida.LoginValida;

@RequestScoped
@Path("/login")
public class Login {
	
	private final Logger logger = Logger.getLogger(Login.class);
	
	@Inject
	private LoginValida usuarioToken;	

	@POST
	@Produces("application/json; charset=ISO-8859-1")
	public Response login(@FormParam("login") String login, @FormParam("senha") String senha){
		logger.info("login()");
		logger.debug("login: " + login);
		
 		try {
 			
 			return Response.status(200).entity(usuarioToken.autentica(login, senha)).build();
 		}catch (LoginInvalidoException e) {
 			logger.error("login", e);
			return Response.status(401).entity("{ \"mensagem\":\""+e.getMessage()+"\"}").build();
 		}catch (Exception e) {
 			logger.error("login", e);
			return Response.status(500).entity("{ \"mensagem\":\""+e.getMessage()+"\"}").build();
		}
 		
		
	}	
	
}
