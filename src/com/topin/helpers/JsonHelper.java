package com.topin.helpers;

import com.topin.db.DbJsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonHelper {

    /**
     * @param key
     * @param value
     * @param from
     * @return JSONArray
     */
    public static JSONArray removeByKey(String key, int value, JSONArray from) {
        return removeByKey(key, String.valueOf(value), from);
    }

    /**
     * @param key
     * @param value
     * @param from
     * @return JSONArray
     */
    public static JSONArray removeByKey(String key, String value, JSONArray from) {
        int i=0;

        for (Object jsonObject: from) {
            if((DbJsonObject.create(jsonObject)).getString(key).equals(value)) {
                return remove(i, from);
            }
            i++;
        }

        return from;
    }

    /**
     * @param idx
     * @param from
     * @return JSONArray
     */
    public static JSONArray remove(int idx, JSONArray from) {
        List<JSONObject> objs = asList(from);
        objs.remove(idx);

        JSONArray jsonArray = new JSONArray();
        for (JSONObject obj : objs) {
            jsonArray.add(obj);
        }

        return jsonArray;
    }

    /**
     * @param jsonArray
     * @return List
     */
    public static List<JSONObject> asList(JSONArray jsonArray) {
        int len = jsonArray.size();
        ArrayList<JSONObject> result = new ArrayList<>(len);

        for (int i = 0; i < len; i++) {
            JSONObject obj = (JSONObject) jsonArray.get(i);

            if (obj != null) {
                result.add(obj);
            }
        }

        return result;
    }
}
