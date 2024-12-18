package rs.ac.uns.ftn.informatika.osa.pr12;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class Logger {

  @AroundInvoke
  public Object logEvent(InvocationContext ctx) throws Exception {
    if (ctx.getMethod() == null)
      return ctx.proceed();
    
    String methodName = ctx.getMethod().getName();
    System.out.println(methodName);
    if (methodName.startsWith("process")) 
      System.out.println("Method called: " + methodName);

    return ctx.proceed();
  }
}
