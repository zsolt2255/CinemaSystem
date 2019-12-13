package com.topin.views;

import com.topin.controllers.LoginController;
import com.topin.controllers.RouteController;
import com.topin.controllers.UserController;
import com.topin.models.SystemInfo;
import menusystem.MenuBase;
import menusystem.MenuController;

public class AdminMainMenu extends MenuBase {

    @Override
    protected void handle(Integer option) {
        switch (option)
        {
            case 1:
                MenuController.create(new RouteControlMenu()).execute();
                break;
            case 2:
                UserController.showUsers();
                break;
            case 3:
                LoginController.logout();
                break;
            case 4:
                System.exit(0);
                break;

        }
    }

    @Override
    public String[] getOptions() {
        return new String[]{
                "Filmek kezelése",
                "Felhasználók listázása",
                "Kijelentkezés",
                "Kilépés"};
    }


}
