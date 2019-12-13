package menusystem.menus;

import com.topin.controllers.CostumerController;
import com.topin.controllers.LoginController;
import menusystem.MenuBase;
import menusystem.MenuController;

import java.text.ParseException;

public class UserMainMenu extends MenuBase {

    @Override
    protected void handle(Integer option) {
        switch (option)
        {
            case 1:
                MenuController.create(new CostumerCardEditorMenu()).execute();
                break;
            case 2:
                MenuController.create(new CostumerPurchaseMenu()).execute();
                break;
            case 3:
                try {
                    CostumerController.search();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                LoginController.logout();
                break;
            case 5:
                System.exit(0);
                break;
        }
    }

    @Override
    public String[] getOptions() {
        return new String[]{
                "Bankkártya kezelés",
                "Jegy vásárlás",
                "Keresés",
                "Kijelentkezés",
                "Kilépés",
        };
    }


}
