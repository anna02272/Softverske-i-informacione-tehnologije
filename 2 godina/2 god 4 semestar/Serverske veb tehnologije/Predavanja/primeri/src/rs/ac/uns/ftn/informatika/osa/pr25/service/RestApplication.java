package rs.ac.uns.ftn.informatika.osa.pr25.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class RestApplication extends Application {
		

		@Override
	    public Set<Class<?>> getClasses() {
	        final Set<Class<?>> classes = new HashSet<Class<?>>();
	        classes.add(AdminService.class);
	        classes.add(CategoryService.class);
	        classes.add(OrderItemService.class);
	        classes.add(ProductImageService.class);
	        classes.add(ProductService.class);
	        classes.add(PurchaseOrderService.class);
	        classes.add(SupplierService.class);
	        classes.add(UserService.class);
	        return classes;
	    }    
}
