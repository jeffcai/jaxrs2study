package com.jeffcaijf.jaxrs2.cxf;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.util.Date;

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
    public Response getUnsecureInfo(@PathParam("msg") final String msg, @Context Request request) {
        System.out.println("index: " + index++);

        // ETag
        String message = "[Unsecured] Hi, " + msg;
        EntityTag tag = new EntityTag(Integer.toString(message.hashCode()));
        Response.ResponseBuilder builder = request.evaluatePreconditions(tag);
        if (builder != null) {
            // If these values do not match the values within the If-Match and If-Unmodified-Since headers
            // sent with the request, evaluatePreconditions() sends back an instance of a ResponseBuilder initialized
            // with the error code 412, “Precondition Failed.” A Response object is built and sent back to the client.
            // If the preconditions are met, the service performs the update and sends back
            // a success code of 204, “No Content.”

//            Response.status(Response.Status.PRECONDITION_FAILED).build();
//            Response.status(Response.Status.NO_CONTENT).build();

            return builder.build();
        }
        builder = Response.ok(message);
        builder.tag(tag);
        return builder.build();
    }

}
