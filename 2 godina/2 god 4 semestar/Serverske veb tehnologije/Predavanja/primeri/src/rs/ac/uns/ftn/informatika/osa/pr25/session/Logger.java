package rs.ac.uns.ftn.informatika.osa.pr25.session;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class Logger {

  @AroundInvoke
  public Object logEvent(InvocationContext ctx) throws Exception {
    if (ctx.getMethod() == null)
      return ctx.proceed();
    
    return ctx.proceed();
  }
}
