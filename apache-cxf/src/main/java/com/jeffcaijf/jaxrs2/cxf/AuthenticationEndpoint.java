package com.jeffcaijf.jaxrs2.cxf;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by jeffcai on 6/17/2016.
 */
@Path("/auth")
public class AuthenticationEndpoint {

    @POST
    @Path("authenticate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser(Credentials credentials) {
        try {
            // Authenticate the user using the credentials provided
            authenticate(credentials.getUsername(), credentials.getPassword());

            // Issue a token for the user
            String token = issueToken(credentials.getUsername());

            // Return the token on the response
            return Response.ok(token).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    private void authenticate(String username, String password) throws Exception {
        // Authenticate against a database, LDAP, file or whatever
        // Throw an Exception if the credentials are invalid
    }

    private String issueToken(String username) {
        try {
            return TokenGenerator.getToken(username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
