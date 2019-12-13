package com.topin.models;

import com.topin.db.DatabaseConnection;
import com.topin.db.DbJsonArray;
import com.topin.db.DbJsonObject;
import com.topin.helpers.JsonHelper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Costumer extends User {
    private User user;
    private ArrayList<CreditCard> creditCards;
    private ArrayList<Purchase> purchases;

    /**
     * @param jsonObject
     */
    public Costumer(DbJsonObject jsonObject) {
        super(jsonObject);
        user = new User(jsonObject);

        if (jsonObject.get("creditCards") != null) {
            ArrayList<CreditCard> cards = new ArrayList<>();
            JSONArray jsonCards = (JSONArray) jsonObject.get("creditCards");
            for ( int i = 0; i < jsonCards.size(); i++) {
                CreditCard card = new CreditCard(DbJsonObject.create(jsonCards.get(i)));
                cards.add(card);
            }
            creditCards = cards;
        } else {
            creditCards = new ArrayList<>();
        }

        if (jsonObject.get("purchases") != null) {
            ArrayList<Purchase> purchases = new ArrayList<>();
            JSONArray jsonPurchases = (JSONArray) jsonObject.get("purchases");
            for ( int i = 0; i < jsonPurchases.size(); i++) {
                Purchase purchase = new Purchase(DbJsonObject.create(jsonPurchases.get(i)));
                purchases.add(purchase);
            }
            this.purchases = purchases;
        } else {
            purchases = new ArrayList<>();
        }
    }

    public void addCreditCard(CreditCard cardNumber) {
        creditCards.add(cardNumber);

        try {
            DbJsonArray x = findData("users["+id+"].creditCards", DatabaseConnection.getInstance().getDatabase());
            insertData(Map.of(
                    "id", getNextId(x.get()),
                    "expiry", String.valueOf(cardNumber.getExpiry()),
                    "type", String.valueOf(cardNumber.getType()),
                    "cardNumber", String.valueOf(cardNumber.getCardNumber()),
                    "CCN", String.valueOf(cardNumber.getCCN())),
                    x);
        } catch(Exception exception) {
            //
        }

        DatabaseConnection.getInstance().saveDatabase();
    }


    public void removeCreditCard(int creditCardID) {
        /*DbJsonArray x = findData("users["+id+"].creditCards", DatabaseConnection.getInstance().getDatabase());
        DatabaseConnection.getInstance().getDatabase().replace(
                "creditCards",
                DatabaseConnection.getInstance().getTable("creditCards"),
                JsonHelper.removeKey("id", creditCardID,x.get())
        );
        DatabaseConnection.getInstance().saveDatabase();*/
        ArrayList<CreditCard> tempCards = new ArrayList<>();
        Iterator<CreditCard> iterator = creditCards.iterator();
        while ( iterator.hasNext()) {
            CreditCard tempCreditCard = iterator.next();
            if ( tempCreditCard.getId() != creditCardID) {
                tempCards.add(tempCreditCard);
            }
        }
        creditCards = tempCards;
    }

    /**
     * @return ArrayList
     */
    public ArrayList<CreditCard> getCreditCards() {
        return creditCards;
    }

    /**
     * @return ArrayList
     */
    public ArrayList<Purchase> getPurchases() {
        return purchases;
    }

    /**
     * @param purchase
     */
    public void addPurchase(Purchase purchase) {
        purchases.add(purchase);

        try {
            DbJsonArray x = findData("users["+id+"].purchases", DatabaseConnection.getInstance().getDatabase());
            insertData(Map.of("id", getNextId(x.get()),
                    "route_id", purchase.getRouteID(),
                    "credit_card_index", purchase.getRouteID(),
                    "price", purchase.getPrice(),
                    "date", String.valueOf(purchase.getDate())),
                    x);
        } catch(Exception exception) {
            //
        }

        DatabaseConnection.getInstance().saveDatabase();
    }

    /**
     * @return void
     */
    public void store() {
        store("users");
    }
}
