package com.jeffcaijf.jaxrs2.wink;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by jeffcai on 6/16/2016.
 */
@Path("myresource")
public class MyResource {

    @Context
    private HttpHeaders headers;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getIt() {
        if (headers.getRequestHeader("basic") == null) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        return Response.ok("Got it!").build();
    }

}
