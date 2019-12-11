package menusystem.menus;

import com.topin.controllers.LoginController;
import menusystem.MenuBase;

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
                LoginController.loginUser();
                break;
            case 2:
                System.exit(0);
                break;

        }
    }

    /**
     * @return String[]
     */
    @Override
    public String[] getOptions() {
        return new String[]{"Bejelentkezés", "Kilépés"};
    }


}
