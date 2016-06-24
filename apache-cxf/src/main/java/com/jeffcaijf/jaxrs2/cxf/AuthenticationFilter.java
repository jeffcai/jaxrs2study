package com.jeffcaijf.jaxrs2.cxf;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by jeffcai on 6/17/2016.
 */
@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    public void filter(ContainerRequestContext requestContext) throws IOException {
        // Get the HTTP Authorization header from the request
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Can get the SecurityContext via ContainerRequestContext
        // requestContext.getSecurityContext().getUserPrincipal()

        // Check if the HTTP Authorization header is present and formatted correctly
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        // Extract the token from the HTTP Authorization header
        String token = authorizationHeader.substring("Bearer".length()).trim();

        // Validate the token
        if (!validateToken(token)) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private boolean validateToken(String token) {
        // Check if it was issued by the server and if it's not expired
        // Throw an Exception if the token is invalid
        return TokenGenerator.isValid(token);
    }
}
