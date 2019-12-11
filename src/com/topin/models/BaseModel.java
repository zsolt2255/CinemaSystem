package com.topin.models;

import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.topin.db.DatabaseConnection;
import com.topin.db.DbJsonArray;
import com.topin.db.DbJsonObject;
import com.topin.db.Query;
import com.topin.helpers.JsonHelper;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Map;


abstract public class BaseModel {
    protected int id;
    protected DatabaseConnection db;

    /**
     * BaseModel Constructor
     */
    public BaseModel() {
        this.db = DatabaseConnection.getInstance();
    }

    /**
     * @param table
     * @param id
     * @return Query
     */
    public static Query find(String table, int id) {
        return (new Query(table)).where(Criteria.where("id").is(id));
    }

    /**
     * @param table
     * @param criteria
     * @return Query
     */
    public static Query where(String table, Criteria criteria) {
        return (new Query(table)).where(criteria);
    }

    /**
     * @param table
     * @param filter
     * @return Query
     */
    public static Query where(String table, Filter filter) {
        return (new Query(table)).where(filter);
    }

    /**
     * @param table
     * @param key
     * @param val
     * @return Query
     * @throws ParseException
     */
    public static Query where(String table, String key, String val) throws ParseException {
        Query query = (new Query(table)).where(Criteria.where(key).is(val));
        JSONArray jsonArray = (JSONArray) (new JSONParser()).parse(query.get().toString());

        return new Query(jsonArray);
    }

    /**
     * @param table
     * @return Query
     */
    public static Query all(String table) {
        return new Query(table);
    }

    /**
     * @param table
     * @param db
     * @return DbJsonArray
     */
    protected DbJsonArray findData(String table, JSONObject db) {
        //System.out.println(table);
        String[] split = table.split("[.]");

        if(table.length() == 0) {
            if(db != null) {
                if (db instanceof JSONObject) {
                    //JSONArray arr = new JSONArray();
                    //arr.add(db);
                    return DbJsonArray.create(db);
                }
            }

            return null;
        }
        if(split.length == 1 && (!split[0].contains("[") && !split[0].contains("]"))) {
            return DbJsonArray.create(db.get(split[0]));
        } else {
            String s = split[0];
            split[0] = "";
            String onlyTableName = StringUtils.join(split, '.');

            if (onlyTableName.length() > 1) {
                onlyTableName = onlyTableName.substring(1);
            }

            if (s.contains("[") && s.contains("]")) {
                int getId = Integer.parseInt(s.replaceAll("\\D+", ""));
                String getDb = s.replaceAll("[^A-Za-z]+", "");
                JSONArray currDb = (JSONArray) db.get(getDb);

                int i = 0;
                for (Object jsonObject : currDb) {
                    JSONObject obj = (JSONObject) jsonObject;
                    int currentId = DbJsonObject.create(obj).getInt("id");

                    if (currentId == getId) {
                        return findData(onlyTableName, (JSONObject) currDb.get(i));
                    }

                    i++;
                }
            } else {
                Object currDb = db.get(s);

                if (currDb instanceof JSONObject) {
                    return findData(onlyTableName, (JSONObject) currDb);
                }

            }
        }

        return null;
    }


    /**
     * @param datas
     * @param database
     * @return JSONObject
     */
    protected JSONObject insertData(Map<String, String> datas, DbJsonArray database) {
        JSONObject jsonObject = new JSONObject();
        datas.forEach(jsonObject::put);
        database.get().add(datas);

        return DatabaseConnection.getInstance().getDatabase();
    }

    /**
     * @param datas
     * @param database
     * @return JSONObject
     */
    protected JSONObject insertData(Map<String, String> datas, JSONArray database) {
        JSONObject jsonObject = new JSONObject();
        datas.forEach(jsonObject::put);
        database.add(datas);

        return DatabaseConnection.getInstance().getDatabase();
    }

    /**
     * @param table
     * @param datas
     * @return JSONObject
     */
    protected JSONObject insertData(String table, Map<String, String> datas) {
        return insertData(table, datas, DatabaseConnection.getInstance().getDatabase());
    }

    /**
     * @param table
     * @param datas
     * @param database
     * @return JSONObject
     */
    protected JSONObject insertData(String table, Map<String, String> datas, JSONObject database) {

        //if(table.split(".").length)
        if(database.get(table) instanceof JSONArray) {
            JSONArray arr = (JSONArray) database.get(table);
            JSONObject jsonObject = new JSONObject();

            datas.forEach(jsonObject::put);
            ((JSONArray) database.get(table)).add(jsonObject);

            database.put(table, arr);
        } else if(database.get(table) instanceof JSONObject) {
            JSONObject jsonObject = new JSONObject();
            datas.forEach(jsonObject::put);

            database.putAll(datas);
        }

        return DatabaseConnection.getInstance().getDatabase();
    }

    /**
     * @param id
     * @return Map
     */
    abstract protected Map<String, String> insert(int id);

    /**
     * @return Map
     */
    abstract protected Map<String, String> save();

    /**
     * @param table
     */
    public void store(String table) {
        if(this.id == 0) {
            insertData(table, insert(getNextId(table)));
        } else {
            save();
        }

        DatabaseConnection.getInstance().saveDatabase();
    }

    /**
     * @param table
     * @param datas
     */
    protected void storeSingleObject(String table, Map<String, String> datas) {
        insertData(table, datas);
    }

    /**
     * @param table
     * @return int
     */
    private int getNextId(String table) {
        return getNextId(DatabaseConnection.getInstance().getTable(table));
    }

    /**
     * @param jsonArray
     * @return int
     */
    protected int getNextId(JSONArray jsonArray) {
        if(jsonArray.size() == 0) {
            return 1;
        }

        if(jsonArray.size() == 1 && jsonArray.get(0).equals("")) {
            return 1;
        }

        int maxid = 0;

        for (Object jsonObject : jsonArray) {
            if (jsonObject != null && (id = Integer.parseInt(String.valueOf(((JSONObject) jsonObject).get("id")))) > maxid) {
                maxid = id;
            }
        }

        return ++maxid;
    }

    /**
     * @return int
     */
    public int getId() {
        return id;
    }


    /**
     * @param table
     * @param id
     */
    public void delete(String table, int id) {
        DatabaseConnection.getInstance().getDatabase().replace(
                table,
                DatabaseConnection.getInstance().getTable(table),
                JsonHelper.removeByKey("id", id, DatabaseConnection.getInstance().getTable(table))
        );

        DatabaseConnection.getInstance().saveDatabase();
    }


}
