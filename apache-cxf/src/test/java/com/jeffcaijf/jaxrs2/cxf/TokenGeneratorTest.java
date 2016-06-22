package com.jeffcaijf.jaxrs2.cxf;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by jeffcai on 6/21/2016.
 */
public class TokenGeneratorTest {

    @Test
    public void get() throws Exception {
        TokenGenerator.getCache().cleanUp();

        String token = TokenGenerator.getToken("tester");
        System.out.println(token);
        Assert.assertNotNull(token);

        Assert.assertEquals(1, TokenGenerator.getCache().size());

        System.out.println(TokenGenerator.getToken("tester"));
        Assert.assertEquals(token, TokenGenerator.getToken("tester"));

        Assert.assertEquals(1, TokenGenerator.getCache().size());
    }


}
