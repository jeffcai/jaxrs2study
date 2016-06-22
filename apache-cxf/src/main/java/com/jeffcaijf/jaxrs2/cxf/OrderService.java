package com.jeffcaijf.jaxrs2.cxf;

import org.apache.commons.lang3.time.DateUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jeffcai on 6/20/2016.
 */
@Path("/orderservice/")
public class OrderService {

    @GET
    @Path("/order/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrder(@PathParam("id") String id, @Context Request request) {
        System.out.println("[order service] get order with id: " + id);
        Response.ResponseBuilder builder = Response.ok(mockOrder(id));

        // Expires Header
        builder.expires(DateUtils.addHours(new Date(), 1));

        // Cache Control: private, public, no-cache, no-store, no-transform, max-age, s-max-age
        // If both an Expires header and a max-age directive are set in the same response,
        // the max-age always takes precedence which time unit is second.
        CacheControl cc = new CacheControl();
        cc.setMaxAge(300);
        // The private directive states that no shared intermediary (proxy or CDN) is allowed to cache the response.
        cc.setPrivate(true);
        // A browser will store cacheable responses on disk so that they can be used after a browser restart or computer reboot.
        // cc.setNoStore(true); // if enable it then we have no any cache at client side
        builder.cacheControl(cc);

        // Last-Modified
        builder.lastModified(new Date());

        return builder.build();
    }

    private Order mockOrder(String id) {
        Order order = new Order();
        order.setId(id);
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(mockOrderItem());
        orderItems.add(mockOrderItem());
        orderItems.add(mockOrderItem());
        order.setOrderItems(orderItems);
        return order;
    }

    private OrderItem mockOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setItemName("mock order item");
        orderItem.setPrice(new BigDecimal("123.56"));
        orderItem.setUnits(2);
        return orderItem;
    }

}
