package com.topin.db;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DbJsonArray {
    private JSONArray jsonArray;

    /**
     * @param jsonArray
     */
    public DbJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    /**
     * @param obj
     */
    public DbJsonArray(Object obj) {
        jsonArray = new JSONArray();
        ((JSONArray) jsonArray).add(obj);
    }

    /**
     * @param id
     * @return Object
     */
    public Object get(int id) {
        return jsonArray.get(id);
    }

    /**
     * @param jsonArray
     * @return DbJsonArray
     */
    public static DbJsonArray create(JSONArray jsonArray) {
        return new DbJsonArray(jsonArray);
    }

    /**
     * @param jsonObject
     * @return DbJsonArray
     */
    public static DbJsonArray create(JSONObject jsonObject) {
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(jsonObject);

        return new DbJsonArray(jsonArray);
    }

    /**
     * @param jsonArray
     * @return DbJsonArray
     */
    public static DbJsonArray create(Object jsonArray) {
        if(jsonArray instanceof JSONArray) {
            return new DbJsonArray((JSONArray) jsonArray);
        }
        else if(jsonArray instanceof Object) {
            return new DbJsonArray(jsonArray);
        }

        return null;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        if(jsonArray == null) {
            return null;
        }

        return jsonArray.toString();
    }

    /**
     * @return JSONArray
     */
    public JSONArray get() {
        return jsonArray;
    }

    /**
     * @return DbJsonObject
     */
    public DbJsonObject first() {
        return DbJsonObject.create(jsonArray.get(0));
    }

    /**
     * @return String
     */
    public String firstString() {
        return (String) jsonArray.get(0);
    }

    /**
     * @return int
     */
    public int firstInt() {
        return Integer.parseInt(String.valueOf(jsonArray.get(0)));
    }

    /**
     * @return double
     */
    public double firstDouble() {
        return Double.parseDouble(String.valueOf(jsonArray.get(0)));
    }
}
