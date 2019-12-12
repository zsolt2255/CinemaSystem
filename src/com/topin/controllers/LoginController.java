package com.topin.controllers;

import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.topin.helpers.Pair;
import com.topin.models.User;
import com.topin.services.StorageService;
import com.topin.views.LoginView;
import menusystem.MenuController;
import menusystem.menus.AdminMenu;
import org.json.simple.JSONObject;

public class LoginController {
    private final User model;
    private final LoginView view;

    /**
     * @param model
     * @param view
     */
    private LoginController(User model, LoginView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * @return LoginController
     */
    public static void loginUser() {
        Pair<String, String> pair = LoginView.login();
        String username = pair.getLeft();
        String password = pair.getRight();

        JSONObject userObject = User.where("users",Filter.filter(Criteria.where("username").is(username).and("password").is(password))).first();

        if(userObject != null) {
            User user = User.make(userObject);
            StorageService.getInstance().setCurrentUser(user);
            LoginController loginController = new LoginController(user, new LoginView(user));
            loginController.view.loginSuccess();
            do {
                switch (user.getRole()){
                    case "admin":
                        MenuController.create(new AdminMenu()).execute();
                        break;
                    case "user":
                        break;
                    case "worker":
                        break;
                }
            }while (StorageService.getInstance().isLogined());
        } else {
            LoginView.loginError();
        }
    }
    public static void logoutUser(){
        User user = StorageService.getInstance().user();
        StorageService.getInstance().setCurrentUser(null);
        LoginController loginController = new LoginController(user, new LoginView(user));
        loginController.view.logoutSuccess();
    }
}
