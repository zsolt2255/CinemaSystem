package com.topin.views;

import com.topin.models.CreditCard;
import menusystem.MenuHelper;

import java.util.ArrayList;
import java.util.Iterator;

public class CreditCardView {

    public static CreditCard getCard() {
        try {
            int cardNumber = Integer.valueOf(MenuHelper.getInstance().readLine("Kártyaszám: "));
            String expiry = MenuHelper.getInstance().readLine("Lejárat: ");
            int CCN = Integer.valueOf(MenuHelper.getInstance().readLine("CCN: "));
            String type = MenuHelper.getInstance().readLine("Típus: ");

            return new CreditCard(cardNumber, expiry, CCN, type);
        } catch(NumberFormatException exception) {
            getCard();
        }

        return null;
    }

    public static Integer getCreditID() {
        try {
            return (Integer) Integer.parseInt(MenuHelper.getInstance().readLine("Azonosító: "));
        } catch(NumberFormatException | NullPointerException exception) {
            getCreditID();
        }

        return null;
    }

    public static void showCreditCards(ArrayList<CreditCard> cards) {
        if ( cards.size() != 0) {
            Iterator<CreditCard> iterator = cards.iterator();
            while (iterator.hasNext()) {
                CreditCard card = iterator.next();
                card.show();
            }
        } else {
            System.out.println("Nincs hozzáadva kártya");
        }
    }
}
