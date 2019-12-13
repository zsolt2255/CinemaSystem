package com.topin.controllers;

import com.topin.models.User;
import com.topin.views.RegisterView;
import com.topin.views.UserView;

import java.util.Map;
import java.util.Objects;

public class UserController {


    public static void createUser()
    {
        Map<String, String> userData = RegisterView.createUser();
        String role = "";
        switch (Integer.valueOf(userData.get("role")))
        {
            case 1: role = "user"; break;
            case 2: role = "worker"; break;
            case 3: role = "admin"; break;
        }

        if(User.whereUsername(userData.get("username")) != null)
        {
            //RegisterView.deleteUserFailed();
            RegisterView.createUserFailed();
        } else {
            new User(userData.get("username"), userData.get("password"), role).store();
            RegisterView.createUserSuccess();
        }
    }

    public static void showUsers() {
        UserView.showUsers(User.all());
    }
}
