package ex.endpoint.filter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

import ex.core.token.exception.TokenException;
import ex.core.token.valida.TokenValida;

@RequestScoped
public class RestRequestFilter implements ContainerRequestFilter {

	private final Logger logger = Logger.getLogger(RestRequestFilter.class);
	
	@Inject
	private TokenValida tokenValida;
	
	@Override
	public ContainerRequest filter(ContainerRequest request) {

		logger.info("filter()");
		
        try{
        	
    		String path = request.getPath();
    		logger.debug("path: " + request.getPath() + " method: " + request.getMethod());
    		
    		if(path.equals("login") || request.getMethod().equalsIgnoreCase("OPTIONS")){
    			return request;
    		}
    		
    	      //Get the authentification passed in HTTP headers parameters
            String auth = request.getHeaderValue("authorization");
            
            if(auth == null){
                throw new WebApplicationException(Status.UNAUTHORIZED);
            }        	
        
        	tokenValida.valida(auth);
        	
        }
        catch(TokenException e){
        	logger.error("filter", e);
        	throw new WebApplicationException(Status.UNAUTHORIZED);
        }
        catch(Exception e){
        	logger.error("filter", e);
        	throw new WebApplicationException(e, Status.INTERNAL_SERVER_ERROR);
        }
		
		return request;
	}

}
