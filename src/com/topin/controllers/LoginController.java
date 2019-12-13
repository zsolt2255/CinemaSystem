package com.topin.controllers;

import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.topin.db.DbJsonObject;
import com.topin.helpers.Pair;
import com.topin.models.Costumer;
import com.topin.models.User;
import com.topin.services.StorageService;
import com.topin.views.LoginView;
import org.json.simple.JSONObject;

public class LoginController {
    private final User model;
    private final LoginView view;

    public LoginController(User model, LoginView view) {
        this.model = model;
        this.view = view;
    }

    public static LoginController loginUser()
    {
        Pair<String, String> pair = LoginView.login();
        String username = pair.getLeft();
        String password = pair.getRight();

        JSONObject userObject = User.where("users",Filter.filter(Criteria.where("username").is(username).and("password").is(password))).first();
        if(userObject != null) {
            if ( userObject.get("role").equals("user")) {
                Costumer costumer = new Costumer(DbJsonObject.create(userObject));
                StorageService.getInstance().setCurrentCostumer(costumer);
            }
            User user = User.make(userObject);
            StorageService.getInstance().setCurrentUser(user);
            LoginController loginController = new LoginController(user, new LoginView(user));
            loginController.view.loginSuccess();
            return loginController;
        }
        else
            LoginView.loginError();

        return null;
    }

    /**
     * @return void
     */
    public static void logout() {
        LoginView loginView = new LoginView(StorageService.getInstance().user());
        loginView.logoutSuccess();

        StorageService.getInstance().setCurrentUser(null);
    }
}
