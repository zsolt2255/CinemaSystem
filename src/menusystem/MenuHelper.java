package menusystem;

import java.util.Scanner;

public class MenuHelper {
    private Scanner input;
    private static MenuHelper instance;

    /**
     * @return MenuHelper
     */
    public static MenuHelper getInstance() {
        if(instance == null) {
            instance = new MenuHelper();
        }

        return instance;
    }

    /**
     * MenuHelper Constructor
     */
    private MenuHelper() {
        input = new Scanner(System.in);
    }

    /**
     * @return String
     */
    public String readLine()
    {
        return new Scanner(System.in).nextLine();
    }

    /**
     * @return int
     */
    public int readInt()
    {
        return new Scanner(System.in).nextInt();
    }

    /**
     * @return String
     */
    public String readLine(String out)
    {
        System.out.print(out);
        return readLine();
    }

    /**
     * @return int
     */
    public int readInt(String out )
    {
        System.out.print(out);
        return readInt();
    }
}
