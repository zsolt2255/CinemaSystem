package com.topin.views;

import com.topin.helpers.ConsoleColors;
import com.topin.models.Route;
import com.topin.models.Time;
import menusystem.MenuHelper;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Map;

public class RouteView {
    private Route model;

    public RouteView(Route model) {
        this.model = model;
    }

    public static void listRoutes(ArrayList<Route> all) {
        all.forEach(RouteView::showRoute);
    }

    public static void showRoute(Route route) {
        System.out.println("ID: " + route.getId() + " " + route.getName() + " -> " + "("+route.getTimeTableCount()+" db időpont)");
    }

    public static int getRoute() {
        return MenuHelper.getInstance().readInt("Adja meg az időpont azonosítóját: ");
    }

    public static void deleteFailed() {
        System.out.println(ConsoleColors.RED+"Sikertelen film törlés"+ConsoleColors.RESET);
    }

    public static void deleteSuccess() {
        System.out.println("Sikeres törlés!");
    }

    public void showTimeTable()
    {
        for (Time time : model.getTimes()) {
            System.out.println("\t [" + time.getId() + "]: " + time.getStartFormat() + " - " + time.getEndFormat());
        }
    }

    public int getTimeTableId()
    {
        return MenuHelper.getInstance().readInt("ID: ");
    }

    public static Map<String, String>  createRoute()
    {
        try {
            System.out.println("--- Új film hozzáadása ---");
            String name = MenuHelper.getInstance().readLine("Név: ");
            String start = MenuHelper.getInstance().readLine("Kezdés időpont: ");
            String end = MenuHelper.getInstance().readLine("Befejezés időpont: ");
            String price = MenuHelper.getInstance().readLine("Ár: ");

            return Map.of(
                    "name", name,
                    "start", start,
                    "end", end,
                    "price", price
            );
        } catch(NumberFormatException exception) {
            createRoute();
        }

        return null;
    }

    public static Integer getRouteID() {
        try {
            return (Integer) Integer.parseInt(MenuHelper.getInstance().readLine("Azonosító: "));
        } catch(NumberFormatException | NullPointerException exception) {
            getRouteID();
        }

        return null;
    }

    public static void createRouteFailed() { System.out.println("Már létezik ezzel a névvel film!"); }
    public static void createRouteSuccess() {
        System.out.println("Sikeres film hozzáadás!");
    }
}
