package com.jeffcaijf.jaxrs2.cxf;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jeffcai on 6/17/2016.
 */
@Path("/authentication")
public class AuthenticationEndpoint {

    private static Map<String, String> tokenCache = new ConcurrentHashMap<String, String>();

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response authenticateUser(Credentials credentials) {
        try {
            credentials = new Credentials();
            // Authenticate the user using the credentials provided
            authenticate(credentials.getUsername(), credentials.getUsername());

            // Issue a token for the user
            String token = issueToken(credentials.getUsername());

            // Return the token on the response
            return Response.ok(token).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    private void authenticate(String username, String password) throws Exception {
        // Authenticate against a database, LDAP, file or whatever
        // Throw an Exception if the credentials are invalid
    }

    private String issueToken(String username) {
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
        // TODO how to make it expire?
        String token = UUID.randomUUID().toString();
        tokenCache.putIfAbsent(username, token);
        return token;
    }

}
