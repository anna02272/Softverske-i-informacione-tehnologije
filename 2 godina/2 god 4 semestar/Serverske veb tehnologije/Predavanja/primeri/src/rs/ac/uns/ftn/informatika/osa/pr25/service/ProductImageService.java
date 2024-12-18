package rs.ac.uns.ftn.informatika.osa.pr25.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
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

import rs.ac.uns.ftn.informatika.osa.pr25.dao.ProductImageDao;
import rs.ac.uns.ftn.informatika.osa.pr25.entity.ProductImage;

@Stateless
@Path("/productImages")
public class ProductImageService {
	
	@EJB
	private ProductImageDao productImageDao;


	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public List<ProductImage> findAll() {
		List<ProductImage> retVal = null; 
		try {
			retVal = productImageDao.findAll();
			
		} catch (Exception e) {
			throw new ServiceException(MessageConstants.RSP_UNKNOWN_ERROR, 
					Status.INTERNAL_SERVER_ERROR);
		}
		return retVal;
	}
	
	@GET 
	@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public ProductImage findById(@PathParam("id") String id) {
		ProductImage retVal = null;
		try {
			retVal = productImageDao.findById(Integer.parseInt(id));
		} catch (Exception e) {
			throw new ServiceException(MessageConstants.RSP_UNKNOWN_ERROR,
					Status.INTERNAL_SERVER_ERROR);			
		}
		return retVal;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public ProductImage create(ProductImage productImage) {
		ProductImage retVal = null;
		try {
			retVal = productImageDao.persist(productImage);
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
	public ProductImage update(ProductImage productImage) {
       ProductImage retVal = null;
        try {
        	retVal = productImageDao.merge(productImage);
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
        	productImageDao.remove(Integer.parseInt(id));
        } catch (Exception e) {
        	throw new ServiceException(MessageConstants.RSP_UNKNOWN_ERROR,
					Status.INTERNAL_SERVER_ERROR);		
        }
    	return MessageConstants.RSP_OK;
    }
    
}