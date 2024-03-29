package menusystem.menus;

import com.topin.controllers.CostumerController;
import com.topin.controllers.LoginController;
import com.topin.controllers.UserController;
import menusystem.MenuBase;

import java.text.ParseException;

public class MainMenu extends MenuBase {

    /**
     * @param option
     * @return void
     */
    @Override
    protected void handle(Integer option) {
        switch (option)
        {
            case 1:
                LoginController loginController = LoginController.loginUser();
                break;
            case 2:
                try {
                    CostumerController.search();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                UserController.createUser();
                break;
            case 4:
                System.exit(0);
                break;

        }
    }

    /**
     * @return String[]
     */
    @Override
    public String[] getOptions() {
        return new String[]{
                "Bejelentkezés",
                "Keresés",
                "Regisztráció",
                "Kilépés"};
    }


}
