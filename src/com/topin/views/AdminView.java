package com.topin.views;

import com.topin.exceptions.IDNotFound;
import com.topin.models.Film;
import com.topin.models.User;
import menusystem.MenuHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdminView {

    public void listUsers(List<User> users){
        users.forEach(user -> {System.out.println(user.toString());});
    }

    /**
     * @return Film
     */
    public static Film addFilm() throws ParseException, IDNotFound {
        String title = MenuHelper.getInstance().readLine("Cím: ");
        int length = MenuHelper.getInstance().readInt("Hossz (perc): ");
        String form = MenuHelper.getInstance().readLine("Formátum: ");
        Date time = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(MenuHelper.getInstance().readLine("Időpont (yyyy-MM-dd HH:mm): "));
        int room_ID = MenuHelper.getInstance().readInt("Terem (ID): ");

        return new Film(title,length,form, time, room_ID, 0);
    }

    public static void listUsersError(){
        System.out.println("Felhasználó lista lekérdezés nem sikerült!");
    }

    public static void addFilmError(){System.out.println("A film hozzáadása sikertelen!");}

    public void addFilmSuccess(){System.out.println("A film hozzáadása sikeres!");}

}
