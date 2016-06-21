package com.jeffcaijf.jaxrs2.cxf;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by jeffcai on 6/17/2016.
 */
@Path("/myresource/")
public class MyResource {

    private int index = 1;

    @GET
    @Secured
    @Path("/secure/{msg}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getSecureInfo(@PathParam("msg") final String msg) {
        return Response.ok("[Secured] Hi, " + msg).build();
    }

    @GET
    @Path("/unsecure/{msg}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getUnsecureInfo(@PathParam("msg") final String msg) {
        System.out.println("index: " + index++);
        return Response.ok("[Unsecured] Hi, " + msg).build();
    }

}
