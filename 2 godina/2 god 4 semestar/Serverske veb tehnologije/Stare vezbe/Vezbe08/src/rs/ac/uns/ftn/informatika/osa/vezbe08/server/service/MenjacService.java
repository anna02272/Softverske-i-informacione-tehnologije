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

import rs.ac.uns.ftn.informatika.osa.vezbe08.server.entity.Menjac;
import rs.ac.uns.ftn.informatika.osa.vezbe08.server.session.MenjacDaoLocal;

@Path("/menjaci")
public class MenjacService {
	
	@EJB
	private MenjacDaoLocal menjacDao;

	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public List<Menjac> findAll() {
		List<Menjac> retVal = null; 
		try {
			retVal = menjacDao.findAll();
			
		} catch (Exception e) {
			throw new ServiceException(MessageConstants.RSP_UNKNOWN_ERROR, 
					Status.INTERNAL_SERVER_ERROR);
		}
		return retVal;
	}
	
	@GET 
	@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public Menjac findById(@PathParam("id") String id) {
		Menjac retVal = null;
		try {
			retVal = menjacDao.findById(Integer.parseInt(id));
		} catch (Exception e) {
			throw new ServiceException(MessageConstants.RSP_UNKNOWN_ERROR,
					Status.INTERNAL_SERVER_ERROR);			
		}
		return retVal;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Menjac create(Menjac menjac) {
		Menjac retVal = null;
		try {
			retVal = menjacDao.persist(menjac);
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
    public Menjac update(Menjac menjac) {
       Menjac retVal = null;
        try {
        	retVal = menjacDao.merge(menjac);
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
        	menjacDao.remove(Integer.parseInt(id));
        } catch (Exception e) {
			throw new ServiceException(MessageConstants.RSP_UNKNOWN_ERROR,
					Status.INTERNAL_SERVER_ERROR);		
        }
    	return MessageConstants.RSP_OK;
    }
    
}