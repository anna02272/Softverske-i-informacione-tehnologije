package dogadjaj;

import java.util.EventListener;

/**
 * Observer interfejs treba da implementiraju svi koji zele da se registruju za pracenje promena podataka u okviru modela
 * 
 */
public interface Observer extends EventListener{
	public void updatePerformed(UpdateEvent e);
}