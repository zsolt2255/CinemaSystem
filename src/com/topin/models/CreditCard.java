package com.topin.models;

import com.topin.db.DbJsonObject;

import java.util.Map;

public class CreditCard extends BaseModel {
    private int CCN;
    private int cardNumber;
    private String expiry;
    private String type;

    /**
     * @param cardNumber
     * @param expiry
     * @param CCN
     * @param type
     */
    public CreditCard(int cardNumber, String expiry, int CCN, String type) {
        this.CCN = CCN;
        this.cardNumber = cardNumber;
        this.expiry = expiry;
        this.type = type;
    }

    /**
     * @param card
     */
    public CreditCard(DbJsonObject card) {
        this.id = card.getInt("id");
        this.CCN = card.getInt("CCN");
        this.cardNumber = card.getInt("cardNumber");
        this.expiry = card.getString("expiry");
        this.type = card.getString("type");
    }

    /**
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * @return String
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return String
     */
    public String getExpiry() {
        return expiry;
    }

    /**
     * @return String
     */
    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    /**
     * @return String
     */
    public int getCardNumber() {
        return cardNumber;
    }

    /**
     * @return String
     */
    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * @return String
     */
    public int getCCN() {
        return CCN;
    }

    /**
     * @return String
     */
    public void setCCN(int CCN) {
        this.CCN = CCN;
    }

    /**
     * @return String
     */
    public void show() {
        System.out.println("============================");
        System.out.println("Azonosító:" + id);
        System.out.println("Kártyaszám: " + cardNumber);
        System.out.println("Lejárat: " + expiry);
        System.out.println("CCN: " + CCN);
        System.out.println("Típus: " + type);
    }

    /**
     * @param id
     * @return Map
     */
    @Override
    protected Map<String, String> insert(int id) {
        return null;
    }

    /**
     * @return Map
     */
    @Override
    protected Map<String, String> save() {
        return null;
    }
}
