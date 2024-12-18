package automat.event;

import java.util.EventListener;

/**
 * Listener treba da implementiraju svi koji zele da se registruju za pracenje promena podataka u okviru kontrolera parkinga:
 * 
 */
public interface UpdateListener extends EventListener{
	public void updatePerformed(UpdateEvent e);
}