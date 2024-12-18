package rs.ac.uns.ftn.informatika.osa.vezbe08.server.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import rs.ac.uns.ftn.informatika.osa.vezbe08.server.entity.Gorivo;
import rs.ac.uns.ftn.informatika.osa.vezbe08.server.session.GorivoDaoLocal;

@Path("/goriva")
public class GorivoService {
	
	@EJB
	private GorivoDaoLocal gorivoDao;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public List<Gorivo> findAll() {
		List<Gorivo> retVal = null; 
		try {
			retVal = gorivoDao.findAll();
		} catch (Exception e) {
			throw new ServiceException(MessageConstants.RSP_UNKNOWN_ERROR, 
					Status.INTERNAL_SERVER_ERROR);
		}
		return retVal;
	}
	
	@GET 
	@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public Gorivo findById(@PathParam("id") String id) {
		Gorivo retVal = null;
		try {
			retVal = gorivoDao.findById(Integer.parseInt(id));
		} catch (Exception e) {
			throw new ServiceException(MessageConstants.RSP_UNKNOWN_ERROR,
					Status.INTERNAL_SERVER_ERROR);			
		}
		return retVal;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Gorivo create(Gorivo gorivo) {
		Gorivo retVal = null;
		try {
			retVal = gorivoDao.persist(gorivo);
		} catch (Exception e) {
			throw new ServiceException(MessageConstants.RSP_UNKNOWN_ERROR,
					Status.INTERNAL_SERVER_ERROR);			
		}
		return retVal;
    }
    
    @PUT 
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Gorivo update(Gorivo gorivo) {
       Gorivo retVal = null;
        try {
        	retVal = gorivoDao.merge(gorivo);
        } catch (Exception e) {
			throw new ServiceException(MessageConstants.RSP_UNKNOWN_ERROR,
					Status.INTERNAL_SERVER_ERROR);			
		}
		return retVal;
    }
 
    @DELETE 
    @Path("{id}")
    @Produces(MediaType.TEXT_HTML)
    public String remove(@PathParam("id") String id) {
    	try {
        	gorivoDao.remove(Integer.parseInt(id));
        } catch (Exception e) {
        	throw new ServiceException(MessageConstants.RSP_UNKNOWN_ERROR,
					Status.INTERNAL_SERVER_ERROR);		
        }
    	return MessageConstants.RSP_OK;
    }
    
}