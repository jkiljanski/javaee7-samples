package org.javaee7.ejb.lifecycle;

import javax.annotation.Priority;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * @author Arun Gupta
 */
@Priority(Interceptor.Priority.APPLICATION + 10)
@Interceptor
@MyAroundConstructInterceptorBinding
public class MyAroundConstructInterceptor {

	@AroundConstruct
	public Object validateConstructor(InvocationContext context) throws Exception {
		System.out.println("MyAroundConstructInterceptor.validateConstructor");
		return context.proceed();
	}

	@AroundInvoke
	public Object validateMethod(InvocationContext context) throws Exception {
		System.out.println("MyAroundConstructInterceptor.validateMethod: " + context.getMethod().getDeclaringClass().getName() + ":" + context.getMethod().getName());
		return context.proceed();
	}


}
