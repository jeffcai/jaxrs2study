package com.jeffcaijf.jaxrs2.cxf;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jeffcai on 6/17/2016.
 */
public class App extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(AuthenticationFilter.class);
        classes.add(JacksonJsonProvider.class);
        classes.add(MyResource.class);
        classes.add(AuthenticationEndpoint.class);
        classes.add(OrderService.class);
        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        Set<Object> classes = new HashSet<Object>();
//        classes.add(new MyResource());
//        classes.add(new AuthenticationEndpoint());
//        classes.add(new OrderService());
        return classes;
    }

}
