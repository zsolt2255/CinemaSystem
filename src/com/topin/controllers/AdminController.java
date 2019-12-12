package com.topin.controllers;

import com.topin.exceptions.IDNotFound;
import com.topin.models.Film;
import com.topin.models.Room;
import com.topin.models.User;
import com.topin.views.AdminView;

import java.text.ParseException;
import java.util.List;

public class AdminController {
    private final AdminView view;

    /**
     * @param view
     */
    public AdminController(AdminView view) {
        this.view = view;
    }

    public static void listUsers(){
        List<User> userList = User.all();
        if (!userList.isEmpty()){
            AdminController adminController = new AdminController(new AdminView());
            adminController.view.listUsers(userList);
        }else{
            AdminView.listUsersError();
        }
    }

    public static void addFilm(){
        Film film = null;
        AdminController adminController = new AdminController(new AdminView());
        try {
            film = AdminView.addFilm();
            film.store();
            adminController.view.addFilmSuccess();
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            AdminView.addFilmError();
        } catch (IDNotFound e){
            System.out.println("Nincs az ID-nak megfelelő erem! ID: "+e.getMessage());
            AdminView.addFilmError();
        }
    }
}
