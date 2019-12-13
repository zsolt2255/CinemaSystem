package com.topin.models;

import com.topin.db.DbJsonObject;
import org.json.simple.JSONObject;

import java.util.Date;
import java.util.Map;

public class Purchase extends BaseModel {
    private Long price;
    private Integer route_id;
    private Integer credit_card_index;
    private Date timestamp;

    public Purchase(Long price, Integer route_id, Integer credit_card_index, Date timestamp) {
        this.price = price;
        this.route_id = route_id;
        this.credit_card_index = credit_card_index;
        this.timestamp = timestamp;
    }

    public Purchase(DbJsonObject jsonObject) {
        this.price = (long) jsonObject.getInt("price");
        this.id = jsonObject.getInt("id");
        this.route_id = jsonObject.getInt("route_id");
        this.credit_card_index = jsonObject.getInt("credit_card_index");
        this.timestamp = new Date();
    }


    public void show() {
        System.out.println("============================");
        System.out.println("Ár: " + price);
        System.out.println("route_id: " + route_id);
        System.out.println("credit_card_index: " + credit_card_index);
        System.out.println("timestamp: " + timestamp);
    }

    public Long getPrice() {
        return this.price;
    }

    public Integer getRouteID() {
        return this.route_id;
    }

    public Date getDate() {
        return this.timestamp;
    }

    @Override
    protected Map<String, String> insert(int id) {
        return null;
    }

    @Override
    protected Map<String, String> save() {
        return null;
    }
}
