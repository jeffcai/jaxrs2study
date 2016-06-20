package com.jeffcaijf.jaxrs2.cxf;

import junit.framework.Assert;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.transport.local.LocalTransportFactory;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeffcai on 6/20/2016.
 */
public class AuthenticationTest {

    private final static String ENDPOINT_ADDRESS = "local://myapp/resource";
    private static Server server;

    @BeforeClass
    public static void initialize() throws Exception {
        startServer();
    }

    private static void startServer() throws Exception {
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        List<Object> providers = new ArrayList();
        providers.add(new JacksonJsonProvider());
        sf.setProviders(providers);
        sf.setResourceClasses(AuthenticationEndpoint.class);
        sf.setResourceProvider(AuthenticationEndpoint.class, new SingletonResourceProvider(new AuthenticationEndpoint(), true));

        sf.setTransportId(LocalTransportFactory.TRANSPORT_ID);
        sf.setAddress(ENDPOINT_ADDRESS);
        server = sf.create();
    }

    @AfterClass
    public static void destroy() throws Exception {
        server.stop();
        server.destroy();
    }

    @Test
    public void authenticate() {
        List<Object> providers = new ArrayList();
        providers.add(new JacksonJsonProvider());

        WebClient client = WebClient.create(ENDPOINT_ADDRESS, providers);
        client.path("auth/authenticate");
        client.type(MediaType.APPLICATION_JSON);
        String token = client.post(mockupCredential(), String.class);
        System.out.println("token: " + token);
        Assert.assertNotNull(token);
    }

    private Credentials mockupCredential() {
        Credentials credentials = new Credentials();
        credentials.setUsername("tester");
        credentials.setPassword("12345");
        return credentials;
    }

}
