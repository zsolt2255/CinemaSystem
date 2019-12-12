package menusystem.menus;

import com.topin.controllers.AdminController;
import com.topin.controllers.LoginController;
import com.topin.models.User;
import menusystem.MenuBase;

public class AdminMenu extends MenuBase {
    @Override
    protected void handle(Integer option) {
        switch (option){
            case 1:
                AdminController.addFilm();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                AdminController.listUsers();
                break;
            case 5:
                LoginController.logoutUser();
                break;
        }
    }

    @Override
    public String[] getOptions() {
        return new String[]{"Adatok bevitele", "Adatok módosítása", "Keresés", "Felhasználók listázása", "Kijelentkezés"};
    }
}
