package com.topin;

import com.topin.services.StorageService;
import com.topin.views.AdminMainMenu;
import menusystem.MenuController;
import menusystem.menus.MainMenu;
import menusystem.menus.UserMainMenu;
import menusystem.menus.WorkerMainMenu;

public class Main {
    public static void main(String[] args) {
        do {
            if(StorageService.getInstance().user() == null)
                MenuController.create(new MainMenu()).execute();
            else if(StorageService.getInstance().user().isUser()) // USER
                MenuController.create(new UserMainMenu()).execute();
            else if(StorageService.getInstance().user().isWorker()) // WORKER
                MenuController.create(new WorkerMainMenu()).execute();
            else if(StorageService.getInstance().user().isAdmin()) // ADMIN
                MenuController.create(new AdminMainMenu()).execute();
        } while (true);
    }
}
