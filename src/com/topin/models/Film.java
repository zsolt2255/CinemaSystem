package com.topin.models;

import com.topin.db.DbJsonObject;
import com.topin.db.Query;
import com.topin.exceptions.IDNotFound;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class Film extends BaseModel {

    private String title;
    private int length;
    private String form;
    private Date time;
    private int room_ID;
    private int sold;

    private Room room;

    /**
     * @param dbJsonObject
     */
    public Film(DbJsonObject dbJsonObject) {
        id = dbJsonObject.getInt("id");
        title = dbJsonObject.getString("title");
        length = dbJsonObject.getInt("length");
        form = dbJsonObject.getString("form");
        room_ID = dbJsonObject.getInt("room");
        sold = dbJsonObject.getInt("sold");
        time = dbJsonObject.getDate("time");
        try {
            room = Room.whereID(room_ID);
        } catch (IDNotFound idNotFound) {
            System.out.println(idNotFound.getMessage());
        }
    }

    /**
     * @param title
     * @param length
     * @param form
     * @param time
     * @param room_ID
     * @param sold
     */
    public Film(String title, int length, String form, Date time, int room_ID, int sold) throws IDNotFound {
        this.title = title;
        this.length = length;
        this.form = form;
        this.time = time;
        this.room_ID = room_ID;
        this.sold = sold;
        this.room = Room.whereID(room_ID);
    }

    /**
     * @param jsonObject
     * @return
     */
    public static Film make(JSONObject jsonObject) {
        return jsonObject==null?null:new Film(DbJsonObject.create(jsonObject));
    }

    /**
     * @param query
     * @return ArrayList
     */
    public static ArrayList<Film> make(Query query) {
        return make(query.all());
    }

    /**
     * @param jsonArray
     * @return
     */
    public static ArrayList<Film> make(JSONArray jsonArray) {
        ArrayList<Film> films = new ArrayList<>();
        jsonArray.forEach(item-> films.add(new Film(new DbJsonObject((JSONObject) item))));
        return films;
    }

    /**
     * @return ArrayList
     */
    public static ArrayList<Film> all() {
        return make(Film.all("films").get());
    }

    /**
     * @param id
     * @return Map
     */
    @Override
    protected Map<String, String> insert(int id) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return Map.of(
                "id", String.valueOf(id),
                "title", title,
                "length", String.valueOf(length),
                "form", form,
                "time", formatter.format(time),
                "room", String.valueOf(room_ID),
                "sold", String.valueOf(sold)
        );
    }

    @Override
    protected Map<String, String> save() {
        return null;
    }

    /**
     * @return void
     */
    public void store() {
        store("films");
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", length=" + length +
                ", form='" + form + '\'' +
                ", time=" + time +
                ", room=" + room_ID +
                ", sold=" + sold +
                ", free_seats= " + (room.getMaxTicketsCount()-sold) +
                '}';
    }

    /**
     * @return void
     */
    public void delete() {
        if(id > 0)
            delete("films", id);
    }

    public int getRoom_ID() {
        return room_ID;
    }
}
