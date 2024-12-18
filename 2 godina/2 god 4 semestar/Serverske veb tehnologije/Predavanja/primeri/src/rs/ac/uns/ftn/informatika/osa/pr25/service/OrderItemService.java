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

import rs.ac.uns.ftn.informatika.osa.pr25.dao.OrderItemDao;
import rs.ac.uns.ftn.informatika.osa.pr25.entity.OrderItem;

@Stateless
@Path("/orderItems")
public class OrderItemService {
	
	@EJB
	private OrderItemDao orderItemDao;	
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public List<OrderItem> findAll() {
		List<OrderItem> retVal = null; 
		try {
			retVal = orderItemDao.findAll();
			
		} catch (Exception e) {
			throw new ServiceException(MessageConstants.RSP_UNKNOWN_ERROR, 
					Status.INTERNAL_SERVER_ERROR);
		}
		return retVal;
	}
	
	@GET 
	@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public OrderItem findById(@PathParam("id") String id) {
		OrderItem retVal = null;
		try {
			retVal = orderItemDao.findById(Integer.parseInt(id));
		} catch (Exception e) {
			throw new ServiceException(MessageConstants.RSP_UNKNOWN_ERROR,
					Status.INTERNAL_SERVER_ERROR);			
		}
		return retVal;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public OrderItem create(OrderItem orderItem) {
		OrderItem retVal = null;
		try {
			retVal = orderItemDao.persist(orderItem);
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
	public OrderItem update(OrderItem orderItem) {
       OrderItem retVal = null;
        try {
        	retVal = orderItemDao.merge(orderItem);
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
        	orderItemDao.remove(Integer.parseInt(id));
        } catch (Exception e) {
        	throw new ServiceException(MessageConstants.RSP_UNKNOWN_ERROR,
					Status.INTERNAL_SERVER_ERROR);		
        }
    	return MessageConstants.RSP_OK;
    }
    
}