package com.topin.views;

import com.topin.db.DbJsonObject;
import com.topin.helpers.Pair;
import com.topin.models.CreditCard;
import com.topin.models.Lease;
import com.topin.models.Purchase;
import menusystem.MenuHelper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;

public class PurchasesView {
    public static void showPurchases(ArrayList<Purchase> purchases) {
        if ( purchases.size() != 0) {
            Iterator<Purchase> iterator = purchases.iterator();
            while (iterator.hasNext()) {
                Purchase purchase = iterator.next();
                System.out.println("Adatok: ");
                purchase.show();
            }
        } else {
            System.out.println("Nincs vásárlási előzmény.");
        }
    }
    public static DbJsonObject purchase(JSONObject db, ArrayList<CreditCard> creditCards) {
        if ( creditCards.size() != 0) {
            ArrayList<Lease> leases = new ArrayList<>();
            Lease lease;
            JSONArray jsonLeases = (JSONArray) db.get("leases");
            for ( int i = 0; i < jsonLeases.size(); i++) {
                lease = new Lease((JSONObject) jsonLeases.get(i));
                leases.add(lease);
            }
            Iterator<Lease> leaseit = leases.iterator();
            int i = 0;
            while (leaseit.hasNext()) {
                lease = leaseit.next();
                System.out.println(i + ".Film: ");
                lease.show();
                i++;
            }

            Iterator<CreditCard> iterator = creditCards.iterator();
            i = 0;
            while (iterator.hasNext()) {
                CreditCard creditCard = iterator.next();
                System.out.println(i + ".Bankkártya: ");
                creditCard.show();
                i++;
            }

            try {
                int numberOfLease = Integer.parseInt(MenuHelper.getInstance().readLine("Film száma: "));
                int numberOfCard = Integer.parseInt(MenuHelper.getInstance().readLine(("Bankkártya sorszáma: ")));
                JSONObject json = new JSONObject();
                JSONObject jsonObject = (JSONObject) jsonLeases.get(numberOfLease);
                json.put("route_id",numberOfLease);
                json.put("credit_card_index",numberOfCard);
                json.put("price",jsonObject.get("price"));
                return (new DbJsonObject(json)) ;
            } catch(IndexOutOfBoundsException | NumberFormatException exception) {
                purchase(db,creditCards);
            }
        } else {
            System.out.println("Adjon hozzá bankkártyát!");
            return null;
        }

        return null;
    }

    public static void showError() {
        System.out.println("Sikertelen vásárlás. Próbálja újra.");
    }

    public static void showSuccess() {
        System.out.println("Sikeres vásárlás.");
    }
}
