package menusystem.menus;

import com.topin.controllers.CostumerController;
import menusystem.MenuBase;

public class CostumerCardEditorMenu extends MenuBase {
    @Override
    protected void handle(Integer option) {
        CostumerController controller = new CostumerController();
        switch (option)
        {
            case 1:
                controller.addCreditCard();
                break;
            case 2:
                controller.removeCreditCard();
                break;
            case 3:
                controller.getCreditCards();
                break;
            default:
                break;
        }
    }

    @Override
    public String[] getOptions() {
        return new String[]{"Bankkártya hozzáadás", "Bankkártya törlés", "Bankkártyák", "Vissza"};
    }
}
