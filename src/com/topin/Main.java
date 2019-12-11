package com.topin;

import com.topin.services.StorageService;
import menusystem.MenuController;
import menusystem.menus.MainMenu;

public class Main {
    public static void main(String[] args) {
        do {
            if (StorageService.getInstance().user() == null) {
                MenuController.create(new MainMenu()).execute();
            }
        } while (true);
    }
}
