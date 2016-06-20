package com.jeffcaijf.jaxrs2.cxf;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeffcai on 6/20/2016.
 */
@Path("/orderservice/")
public class OrderService {

    @GET
    @Path("/order/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrder(@PathParam("id") String id) {
        return Response.ok(mockOrder(id)).build();
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
