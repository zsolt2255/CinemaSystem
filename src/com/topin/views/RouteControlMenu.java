package com.topin.views;

import com.topin.controllers.RouteController;
import menusystem.MenuBase;

public class RouteControlMenu extends MenuBase {

    @Override
    protected void handle(Integer option) {
        switch (option)
        {
            case 1:
                RouteController.createRoute();
                break;
            case 2:
                RouteController.removeRoute();
                break;
            case 3:
                RouteController.listRoutes();
                break;
        }
    }

    @Override
    public String[] getOptions() {
        return new String[]{
                "Film hozzáadása",
                //"Filmek módosítása",
                "Filmek törlése",
                "Filmek listázása",
                "Vissza"};
    }


}
