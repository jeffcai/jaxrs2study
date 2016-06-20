package com.jeffcaijf.jaxrs2.cxf;

import java.util.List;

/**
 * Created by jeffcai on 6/20/2016.
 */
public class Order {
    private String id;
    private List<OrderItem> orderItems;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
