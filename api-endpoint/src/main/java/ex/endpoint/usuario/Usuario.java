package ex.endpoint.usuario;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import ex.core.usuario.lista.UsuarioLista;

@RequestScoped
@Path("/usuario/lista")
public class Usuario {

	private final Logger logger = Logger.getLogger(Usuario.class);
	
	@Inject
	private UsuarioLista usuarioLista;

	@POST
	@Produces("application/json; charset=ISO-8859-1")
	public Response lista(@FormParam("feminino") Boolean feminino){
		logger.info("lista()");
		logger.debug("feminino: " + feminino);
		
 		try {
 			
 			return Response.status(200).entity(usuarioLista.lista(feminino)).build();
 			
 		}catch (Exception e) {
 			logger.error("lista", e);
			return Response.status(500).entity("{ \"mensagem\":\""+e.getMessage()+"\"}").build();
		}
 		
		
	}	
}
