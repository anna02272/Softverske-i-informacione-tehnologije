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

import rs.ac.uns.ftn.informatika.osa.vezbe08.server.entity.Korisnik;
import rs.ac.uns.ftn.informatika.osa.vezbe08.server.session.KorisnikDaoLocal;


@Path("/korisnici")
public class KorisnikService {
	
	@EJB
	private KorisnikDaoLocal korisnikDao;

	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Korisnik> findAll() {
		List<Korisnik> retVal = null; 
		try {
			retVal = korisnikDao.findAll();
			
		} catch (Exception e) {
			throw new ServiceException(MessageConstants.RSP_UNKNOWN_ERROR, 
					Status.INTERNAL_SERVER_ERROR);
		}
		return retVal;
	}
	
	@GET 
	@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Korisnik findById(@PathParam("id") String id) {
		Korisnik retVal = null;
		try {
			retVal = korisnikDao.findById(Integer.parseInt(id));
		} catch (Exception e) {
			throw new ServiceException(MessageConstants.RSP_UNKNOWN_ERROR,
					Status.INTERNAL_SERVER_ERROR);			
		}
		return retVal;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Korisnik create(Korisnik korisnik) {
		Korisnik retVal = null;
		try {
			retVal = korisnikDao.persist(korisnik);
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
    public Korisnik update(Korisnik korisnik) {
       Korisnik retVal = null;
        try {
        	retVal = korisnikDao.merge(korisnik);
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
        	korisnikDao.remove(Integer.parseInt(id));
        } catch (Exception e) {
			throw new ServiceException(MessageConstants.RSP_UNKNOWN_ERROR,
					Status.INTERNAL_SERVER_ERROR);		
        }
    	return MessageConstants.RSP_OK;
    }
    
    
}