package com.topin.models;

import org.json.simple.JSONObject;

public class Lease {
    private Long price;
    private Long route_id;

    public Lease(Long price, Long route_id) {
        this.price = price;
        this.route_id = route_id;
    }

    public Lease(JSONObject lease) {
        price = (Long) lease.get("price");
        route_id = (Long) lease.get("route_id");
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getRouteID() {
        return route_id;
    }

    public void setRouteID(Long route_id) {
        this.route_id = route_id;
    }

    public void show() {
        System.out.println("Ár: " + price);
        System.out.println("Film id: "+route_id);
    }
}
