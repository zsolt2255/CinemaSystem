package menusystem.menus;

import com.topin.controllers.CostumerController;
import menusystem.MenuBase;

public class CostumerPurchaseMenu extends MenuBase {
    @Override
    protected void handle(Integer option) {
        CostumerController controller = new CostumerController();
        switch (option)
        {
            case 1:
                controller.purchase();
                break;
            case 2:
                controller.purchases();
                break;
            case 3:
                break;
        }
    }

    @Override
    public String[] getOptions() {
        return new String[]{
                "Vásárlás",
                "Vásárlási előzmények",
                "Vissza"
        };
    }


}
