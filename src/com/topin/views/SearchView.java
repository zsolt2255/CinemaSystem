package com.topin.views;

import com.topin.helpers.ConsoleColors;
import com.topin.models.Time;
import menusystem.MenuHelper;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

public class SearchView {
    public static String getRoute() {
        return MenuHelper.getInstance().readLine("Film címe: ");
    }

    public static void showRoute(String route, Long price, ArrayList<Time> times) throws ParseException {
        System.out.println(ConsoleColors.BLUE + "Sikeres találat!" + ConsoleColors.RESET);
        System.out.println("============================");
        System.out.println("A film neve: "+route);
        System.out.println("Ár: " + price);
        Iterator<Time> iterator = times.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Time time = iterator.next();
            System.out.println("Kezdés: " + time.getStartFormat());
            System.out.println("Befejezés: " + time.getEndFormat());
            System.out.println("Hossz: " + time.difference(time.getStartFormat(),time.getEndFormat()) + " óra");
            i++;
        }
    }

    public static void showError() {
        System.out.println(ConsoleColors.RED+"Nincs ilyen film."+ConsoleColors.RESET);
    }
}
