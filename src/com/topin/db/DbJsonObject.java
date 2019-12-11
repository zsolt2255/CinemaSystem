package com.topin.db;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class DbJsonObject {
    private JSONObject jsonObject;

    /**
     * @param jsonObject
     */
    public DbJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    /**
     * @param key
     * @return
     */
    public JSONObject getObject(String key) {
        return (JSONObject) jsonObject.get(key);
    }

    /**
     * @param key
     * @return JSONArray
     */
    public JSONArray getArray(String key) {
        return (JSONArray) jsonObject.get(key);
    }

    /**
     * @param key
     * @return
     */
    public Object get(String key) {
        return jsonObject.get(key);
    }

    /**
     * @param key
     * @return int
     */
    public int getInt(String key) {
        if(!jsonObject.containsKey(key)) {
            return 0;
        }

        if(jsonObject.get(key) instanceof Long) {
            return ((Long) jsonObject.get(key)).intValue();
        } else if(jsonObject.get(key) instanceof String) {
            return Integer.parseInt((String) jsonObject.get(key));
        } else {
            return (int) jsonObject.get(key);
        }
    }

    /**
     * @param key
     * @return String
     */
    public String getString(String key) {
        if(!jsonObject.containsKey(key)) {
            return null;
        }

        if(jsonObject.get(key) instanceof Long) {
            return ((Long) jsonObject.get(key)).toString();
        } else {
            return jsonObject.get(key).toString();
        }
    }

    /**
     * @param key
     * @return double
     */
    public double getDouble(String key) {
        if(!jsonObject.containsKey(key)) {
            return 0;
        }

        if(jsonObject.get(key) instanceof Long) {
            return Double.valueOf((Long) jsonObject.get(key));
        } else if(jsonObject.get(key) instanceof String) {
            return Double.valueOf(jsonObject.get(key).toString());
        } else {
            return (double) jsonObject.get(key);
        }
    }

    /**
     * @param key
     * @return Date
     */
    public Date getDate(String key) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            return formatter.parse((String) jsonObject.get(key));
        } catch (ParseException ignore) {
            System.out.println(Arrays.toString(ignore.getStackTrace()));
        }

        return null;
    }

    /**
     * @param jsonObject
     * @return DbJsonObject
     */
    public static DbJsonObject create(JSONObject jsonObject) {
        return new DbJsonObject(jsonObject);
    }

    /**
     * @param jsonObject
     * @return DbJsonObject
     */
    public static DbJsonObject create(Object jsonObject) {
        if(jsonObject instanceof JSONObject) {
            return new DbJsonObject((JSONObject) jsonObject);
        }

        return new DbJsonObject(new JSONObject());
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return jsonObject.toString();
    }
}

