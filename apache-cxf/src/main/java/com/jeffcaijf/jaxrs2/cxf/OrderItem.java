package com.jeffcaijf.jaxrs2.cxf;

import java.math.BigDecimal;

/**
 * Created by jeffcai on 6/20/2016.
 */
public class OrderItem {

    private String itemName;
    private BigDecimal price;
    private int units;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }
}
