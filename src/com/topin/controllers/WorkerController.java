package com.topin.controllers;

import com.topin.db.DbJsonArray;
import com.topin.db.DbJsonObject;
import com.topin.models.Purchase;
import com.topin.models.User;
import com.topin.models.Worker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class WorkerController {
    private final Worker worker;
    private JSONArray jsonPurchases;
    private JSONArray jsonCreditCards;
    private DbJsonObject data;

    public WorkerController() {
        this.worker = new Worker();
        data = this.worker.getPurchases();
        jsonPurchases = (JSONArray) data.get("purchases");
        jsonCreditCards = (JSONArray) data.get("creditCards");
    }

    public void getTaking() {
        int sum = 0;
        for ( int i = 0; i < jsonPurchases.size(); i++) {
            Purchase purchase = new Purchase(DbJsonObject.create(jsonPurchases.get(i)));
            sum += purchase.getPrice();
        }
        System.out.println("Az összes bevétel: "+sum+" FT");
    }

    public void getViewers() {
        System.out.println("Az összes eddigi néző: "+jsonPurchases.size());
    }

    public void weeklyReport() {
        int sum = 0;
        for ( int i = 0; i < jsonPurchases.size(); i++) {
            Purchase purchase = new Purchase(DbJsonObject.create(jsonPurchases.get(i)));
            sum += purchase.getPrice();
        }
        System.out.println("A heti adatok összesítés: ");
        System.out.println("Az összes eddigi néző: "+jsonPurchases.size());
        System.out.println("Az összes bevétel: "+sum);
        System.out.println("Az összes bankkártyák száma: "+jsonCreditCards.size());
    }

    public void getUserData() {
        UserController.showUsers();
        System.out.println("Az összes role száma: 4");
        System.out.println("Felhasználók száma: "+User.all().size());
    }
}
