package rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class Logger {

	@AroundInvoke
	public Object logEvent(InvocationContext ctx) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm");  
		
		try {
			System.out.println("["+sdf.format(new Date()) + "] Class name: " + ctx.getTarget().getClass() + ", method name: " + ctx.getMethod() + " started.");
			return ctx.proceed();
		} catch (Exception e) {
			System.out.print("["+sdf.format(new Date()) + "] Class name: " + ctx.getTarget().getClass() + ", method name: " + ctx.getMethod() + "thrown an exception.");
			e.printStackTrace();
			throw e;
		} finally {
			System.out.println("["+sdf.format(new Date()) + "] Class name: " + ctx.getTarget().getClass() + ", method name: " + ctx.getMethod() + " finished.");
		}
	}
}
