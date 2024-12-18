package rs.ac.uns.ftn.informatika.osa.vezbe08.server.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class RestApplication extends Application {
		

		@Override
	    public Set<Class<?>> getClasses() {
	        final Set<Class<?>> classes = new HashSet<Class<?>>();
	        classes.add(GorivoService.class);
	        classes.add(VoziloService.class);
	        classes.add(MenjacService.class);
	        classes.add(KorisnikService.class);
	        return classes;
	    }    
}
