package menusystem;

import java.util.HashMap;
import java.util.Map;

abstract public class MenuBase {
    private Map<Integer, String> options = new HashMap<>();

    /**
     * @param index
     * @param option
     * @return void
     */
    public void addOption(Integer index, String option)
    {
        options.put(index, option);
    }

    /**
     * @return void
     */
    public void execute()
    {
        if(options.size() < 1) {
            return;
        }

        MenuExecute menuExecute = new MenuExecute(options);
        int result;

        do {
            result = menuExecute.execute();

            if(options.get(result-1) == null) {
                System.out.println("Hibás bevitel! Próbálja újra...");
            }
        } while (options.get(result-1) == null);

        this.handle(result);
    }

    /**
     * @param option
     */
    protected abstract void handle(Integer option);

    /**
     * @return String[]
     */
    public abstract String[] getOptions();
}
