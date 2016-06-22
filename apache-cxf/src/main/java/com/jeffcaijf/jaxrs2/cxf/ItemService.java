package com.jeffcaijf.jaxrs2.cxf;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeffcai on 6/22/2016.
 */
@Path("/item/")
public class ItemService {

    @GET
    @Path("/query")
    @Produces(MediaType.APPLICATION_JSON)
    public Response query() {
        List<Item> items = new ArrayList();
        items.add(new Item("1", "i1", "item one", new BigDecimal(123.34)));
        items.add(new Item("2", "i2", "item two", new BigDecimal(35.34)));
        return Response.ok(items).build();
    }

}
