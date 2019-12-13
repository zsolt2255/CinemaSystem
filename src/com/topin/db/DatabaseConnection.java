package com.topin.db;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;

public class DatabaseConnection {
    private static final String filePath = "src/com/topin/db/db.json";

    private JSONObject database = null;

    private static DatabaseConnection instance;
    static {
        instance = new DatabaseConnection();
    }

    /**
     * DatabaseConnection Constructor
     */
    private DatabaseConnection() {
        JSONParser parser = new JSONParser();

        try {
            //System.out.println(filePath);
            database = (JSONObject) parser.parse( new FileReader(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return DatabaseConnection
     */
    public static DatabaseConnection getInstance() {
        if(instance == null) {
            instance = new DatabaseConnection();
        }

        return instance;
    }

    /**
     * @return JSONObject
     */
    public JSONObject getDatabase() {
        return database;
    }

    /**
     * @param table
     * @return JSONArray
     */
    public JSONArray getTable(String table) {
        if(database.get(table) instanceof JSONObject) {
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(database.get(table));

            return jsonArray;
        }

        return (JSONArray) database.get(table);
    }

    /**
     * @return void
     */
    public void saveDatabase() {
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(database.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}