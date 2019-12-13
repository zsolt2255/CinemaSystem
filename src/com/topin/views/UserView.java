package com.topin.views;

import com.topin.models.User;

import java.util.ArrayList;

public class UserView {

    public static void showUsers(ArrayList<User> all) {
        all.forEach(UserView::showUser);
    }

    public static void showUser(User user) {
        System.out.println("ID: " + user.getId() + " " + user.getUsername() + ": " + user.getRole());
    }
}
