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

import rs.ac.uns.ftn.informatika.osa.vezbe08.server.entity.Vozilo;
import rs.ac.uns.ftn.informatika.osa.vezbe08.server.session.VoziloDaoLocal;

@Path("/vozila")
public class VoziloService {
	
	@EJB
	private VoziloDaoLocal voziloDao;

	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public List<Vozilo> findAll() {
		List<Vozilo> retVal = null; 
		try {
			retVal = voziloDao.findAll();
			
		} catch (Exception e) {
			throw new ServiceException(MessageConstants.RSP_UNKNOWN_ERROR, 
					Status.INTERNAL_SERVER_ERROR);
		}
		return retVal;
	}
	
	@GET 
	@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public Vozilo findById(@PathParam("id") String id) {
		Vozilo retVal = null;
		try {
			retVal = voziloDao.findById(Integer.parseInt(id));
		} catch (Exception e) {
			throw new ServiceException(MessageConstants.RSP_UNKNOWN_ERROR,
					Status.INTERNAL_SERVER_ERROR);			
		}
		return retVal;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Vozilo create(Vozilo vozilo) {
		Vozilo retVal = null;
		try {
			retVal = voziloDao.persist(vozilo);
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
	public Vozilo update(Vozilo vozilo) {
       Vozilo retVal = null;
        try {
        	retVal = voziloDao.merge(vozilo);
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
        	voziloDao.remove(Integer.parseInt(id));
        } catch (Exception e) {
        	throw new ServiceException(MessageConstants.RSP_UNKNOWN_ERROR,
					Status.INTERNAL_SERVER_ERROR);		
        }
    	return MessageConstants.RSP_OK;
    }
    

}