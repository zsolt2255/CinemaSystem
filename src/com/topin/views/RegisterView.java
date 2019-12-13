package com.topin.views;

import com.topin.helpers.ConsoleColors;
import menusystem.MenuHelper;

import java.util.Map;

public class RegisterView {


    public static Map<String, String> createUser()
    {
        String username = MenuHelper.getInstance().readLine("Felhasználónév: ");
        String password = MenuHelper.getInstance().readLine("Jelszó: ");

        int role;
        do {
            role = MenuHelper.getInstance().readInt("Szerepkör [1: user; 2: worker; 3: admin]: ");
        } while (role < 1 || role > 3);

        return Map.of(
                "username", username,
                "password", password,
                "role", String.valueOf(role)

        );
    }


    public static void createUserSuccess() { System.out.println("Sikeres felhasználó létrehozás!"); }
    public static void deleteUserFailed() { System.out.println("Ismeretlen felhasználó azonosító!"); }
    public static void createUserFailed() { System.out.println(ConsoleColors.RED+"Sikertelen felhasználó létrehozás! Ezzel a felhasználónévvel már létezik felhasználó!"+ConsoleColors.RESET); }

}
