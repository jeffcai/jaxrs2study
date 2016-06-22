package com.jeffcaijf.jaxrs2.cxf;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by jeffcai on 6/22/2016.
 */
public class Item {

    private String id;
    private String name;
    private String descption;
    private BigDecimal price;

    public Item() {
    }

    public Item(String id, String name, String descption, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.descption = descption;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", descption='" + descption + '\'' +
                ", price=" + price.setScale(2, BigDecimal.ROUND_CEILING) +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescption() {
        return descption;
    }

    public void setDescption(String descption) {
        this.descption = descption;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
