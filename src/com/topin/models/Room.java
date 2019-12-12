package com.topin.models;

import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.topin.db.DbJsonObject;
import com.topin.db.Query;
import com.topin.exceptions.IDNotFound;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class Room extends BaseModel {
    private int rows;
    private int cols;
    private int maxTicketsCount;

    /**
     * @param dbJsonObject
     */
    public Room(DbJsonObject dbJsonObject){
        id = dbJsonObject.getInt("id");
        rows = dbJsonObject.getInt("rows");
        cols = dbJsonObject.getInt("cols");
        maxTicketsCount = rows*cols;
    }

    /**
     * @param rows
     * @param cols
     */
    public Room(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.maxTicketsCount = rows*cols;
    }

    /**
     * @param jsonObject
     * @return
     */
    public static Room make(JSONObject jsonObject) {
        return jsonObject==null?null:new Room(DbJsonObject.create(jsonObject));
    }

    /**
     * @param query
     * @return ArrayList
     */
    public static ArrayList<Room> make(Query query) {
        return make(query.all());
    }

    /**
     * @param jsonArray
     * @return ArrayList
     */
    public static ArrayList<Room> make(JSONArray jsonArray) {
        ArrayList<Room> rooms = new ArrayList<>();
        jsonArray.forEach(item-> rooms.add(new Room(new DbJsonObject((JSONObject) item))));
        return rooms;
    }

    /**
     * @return ArrayList
     */
    public static ArrayList<Room> all() {
        return make(Room.all("rooms").get());
    }


    public static Room whereID(int id) throws IDNotFound {
        Room room = make(Room.where("rooms", Filter.filter(Criteria.where("id").is(id))).first());
        if (room == null){
            throw new IDNotFound(id);
        }
        return room;
    }

    @Override
    protected Map<String, String> insert(int id) {
        return Map.of(
                "id", String.valueOf(id),
                "rows", String.valueOf(rows),
                "cols", String.valueOf(cols)
        );
    }

    @Override
    protected Map<String, String> save() {
        return null;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", rows=" + rows +
                ", cols=" + cols +
                ", maxTicketsCount=" + maxTicketsCount +
                '}';
    }

    /**
     * @return void
     */
    public void delete() {
        if(id > 0)
            delete("rooms", id);
    }

    public int getMaxTicketsCount() {
        return maxTicketsCount;
    }
}
