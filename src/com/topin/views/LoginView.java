package com.topin.views;

import com.topin.helpers.Pair;
import com.topin.models.User;
import menusystem.MenuHelper;

public class LoginView {
    private User model;

    /**
     * @param model
     */
    public LoginView(User model) {
        this.model = model;
    }

    /**
     * @return Pair
     */
    public static Pair<String, String> login() {
        String username = MenuHelper.getInstance().readLine("Felhasználónév: ");
        String password = MenuHelper.getInstance().readLine("Jelszó: ");

        Pair<String, String> pair = new Pair<>(username, password);

        return pair;
    }

    /**
     * @return void
     */
    public static void loginError() {
        System.out.println("Hibás bejelentkezési adatok!");
    }

    /**
     * @return void
     */
    public void loginSuccess() {
        System.out.println("Sikeres bejelentkezés, "+model.getUsername()+"!");
    }

    /**
     * @return void
     */
    public void logoutSuccess() {
        System.out.println("Sikeres kijelentkezés, "+model.getUsername()+"!");
    }
}
