package com.topin.models;

import com.topin.db.DatabaseConnection;
import com.topin.db.DbJsonObject;
import org.json.simple.JSONObject;

import java.util.Map;

public class SystemInfo extends BaseModel {
    private static SystemInfo instance;
    private int kmPrice;

    private SystemInfo(DbJsonObject jsonObject)
    {
        kmPrice = jsonObject.getInt("km_price");
    }

    private static SystemInfo make(JSONObject jsonObject) { return jsonObject==null?null:new SystemInfo(DbJsonObject.create(jsonObject)); }

    public static SystemInfo getInstance()
    {
        if(instance == null)
            instance = SystemInfo.make((JSONObject) DatabaseConnection.getInstance().getTable("system").get(0));
        return instance;
    }

    @Override
    public String toString() {
        return "System{" +
                "kmPrice=" + kmPrice +
                '}';
    }

    @Override
    protected Map<String, String> insert(int id) {
        return null;
    }

    @Override
    protected Map<String, String> save() {
        return null;
    }

    public int getKmPrice() {
        return kmPrice;
    }

    public void setKmPrice(int kmPrice) {
        this.kmPrice = kmPrice;
    }

    public void store()
    {
        insertData("system", Map.of("km_price", String.valueOf(kmPrice)));
        DatabaseConnection.getInstance().saveDatabase();
    }
}
