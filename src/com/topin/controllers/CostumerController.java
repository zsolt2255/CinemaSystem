package com.topin.controllers;

import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.topin.db.DatabaseConnection;
import com.topin.db.DbJsonObject;
import com.topin.helpers.Pair;
import com.topin.models.*;
import com.topin.services.StorageService;
import com.topin.views.CreditCardView;
import com.topin.views.PurchasesView;
import com.topin.views.SearchView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

public class CostumerController {
    private Costumer costumer;
    private JSONObject db;

    /**
     * CostumerController Constructor
     */
    public CostumerController() {
        costumer = StorageService.getInstance().costumer();
        db = DatabaseConnection.getInstance().getDatabase();
    }

    public void addCreditCard() {
        //TODO EGY KÁRTYÁT TUDOK CSAK HOZZÁADNI, 2-nél meghal [0 lesz az utolsó id]
        CreditCard creditCard = CreditCardView.getCard();
        costumer.addCreditCard(creditCard);
    }

    public void removeCreditCard() {
        try {
            int cardNumber = CreditCardView.getCreditID();
            costumer.removeCreditCard(cardNumber);
        } catch(NullPointerException exception) {
            removeCreditCard();
        }
    }

    public void getCreditCards() {
        CreditCardView.showCreditCards(costumer.getCreditCards());
    }

    /**
     * @return void
     */
    public void purchase() {
        DbJsonObject dbJsonObject = PurchasesView.purchase(db,costumer.getCreditCards());

        if ( dbJsonObject != null) {
            Purchase purchase = new Purchase(dbJsonObject);
            costumer.addPurchase(purchase);

            PurchasesView.showSuccess();
        } else {
            PurchasesView.showError();
        }
    }

    /**
     * @return void
     */
    public void purchases() {
        PurchasesView.showPurchases(costumer.getPurchases());
    }

    /**
     * @throws ParseException
     * @return void
     */
    public static void search() throws ParseException {
        //TODO megoldani, ha több filmnek ugyan az a neve
        String route = SearchView.getRoute();
        JSONObject jsonRoute = Route.where("routes", Filter.filter(Criteria.where("name").is(route))).first();
        if ( jsonRoute != null) {
            JSONObject leases = Route.where("leases", Filter.filter(Criteria.where("route_id").is(jsonRoute.get("id")))).first();
            JSONArray jsonTimes = (JSONArray) jsonRoute.get("times");
            Long price = (Long) leases.get("price");
            ArrayList<Time> times = Time.make(jsonTimes);

            SearchView.showRoute(route, price, times);
        } else {
            SearchView.showError();
        }
    }
}
