package com.topin.controllers;

import com.topin.models.Route;
import com.topin.models.Time;
import com.topin.services.StorageService;
import com.topin.views.CreditCardView;
import com.topin.views.RouteView;

import java.util.Map;
import java.util.Objects;

public class RouteController {
    private Route model;
    private RouteView view;

    public RouteController(Route model, RouteView view) {
        this.model = model;
        this.view = view;
    }

    public static void listRoutes() {
        RouteView.listRoutes(Route.all());
    }

    private static RouteController selectRoute()
    {
        int id = RouteView.getRoute();
        Route route = Route.make(Route.find("routes", id).first());
        if(route == null)
            return null;
        else
            return new RouteController(route, new RouteView(route));
    }


    public static void createRoute() {
        Map<String, String> routeData = RouteView.createRoute();
        Route route = Route.whereName(routeData.get("name"));
        if(route != null)
            RouteView.createRouteFailed();
        else {
            Time time = new Time(routeData.get("start"), routeData.get("end"));
            new Route(routeData.get("name"),time).store();

            RouteView.createRouteSuccess();
        }
    }

    public static void changeRoute() {
        //
    }

    public static void removeRoute() {
        try {
            int routeID = RouteView.getRouteID();
            Route route = Route.make(Route.find("routes", routeID).first());
            if(Objects.isNull(route))
                RouteView.deleteFailed();
            else {
                route.delete();
                RouteView.deleteSuccess();
            }
        } catch(NullPointerException exception) {
            removeRoute();
        }
    }
}
