package menusystem.menus;

import com.topin.controllers.LoginController;
import com.topin.controllers.WorkerController;
import menusystem.MenuBase;

public class WorkerMainMenu extends MenuBase {

    @Override
    protected void handle(Integer option) {
        WorkerController workerController = new WorkerController();
        switch (option)
        {
            case 1:
                workerController.getTaking();
                break;
            case 2:
                workerController.getViewers();
                break;
            case 3:
                workerController.weeklyReport();
                break;
            case 4:
                workerController.getUserData();
                break;
            case 5:
                LoginController.logout();
                break;
            case 6:
                System.exit(0);
                break;
        }
    }

    @Override
    public String[] getOptions() {
        return new String[]{
                "Bevételi statisztika lekérése",
                "Nézőszámok lekérése",
                "Heti jelentés lekérése",
                "Felhasználói statisztikák lekérése",
                "Kijelentkezés",
                "Kilépés"};
    }


}
