package com.topin.db;

import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class Query {
    private List<String> tables = new ArrayList<>();
    private Filter filter;
    private JSONArray database = null;

    /**
     * @param table
     */
    public Query(String table) {
        this.tables.add(table);
    }

    /**
     * @param jsonArray
     */
    public Query(JSONArray jsonArray) {
        database = jsonArray;
    }

    /**
     * @return String
     */
    private String generateTableStructure() {
        String tableStructure = "$.";

        if(tables.size() > 0) {
            tableStructure += StringUtils.join(this.tables, '.');
        }

        if(this.filter != null) {
            tableStructure += "[?]";
        }

        if(tables.size() == 0) {
            tableStructure += "[*]";
        }

        return tableStructure;
    }

    /**
     * @return JSONArray
     */
    public JSONArray get() {
        if (this.filter == null) {
            return JsonPath.read((database == null ? DatabaseConnection.getInstance().getDatabase() : database), generateTableStructure());
        } else {
            try {
                String x = JsonPath.parse((database==null?DatabaseConnection.getInstance().getDatabase():database)).read(generateTableStructure(), this.filter).toString();
                database = (JSONArray)new JSONParser().parse(x);

                return database;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * @return JSONObject
     */
    public JSONObject getObject() {
        return JsonPath.read((database==null?DatabaseConnection.getInstance().getDatabase():database), generateTableStructure());
    }

    /**
     * @param criteria
     * @return Query
     */
    public Query where(Criteria criteria) {
        filter = Filter.filter(criteria);
        return this;
    }

    /**
     * @param filter
     * @return Query
     */
    public Query where(Filter filter) {
        this.filter = filter;
        return this;
    }

    /**
     * @return JSONObject
     */
    public JSONObject first() {
        if(database == null) {
            this.get();
        }

        if(database.size() > 0) {
            return (JSONObject) database.get(0);
        }

        return null;
    }

    /**
     * @return JSONArray
     */
    public JSONArray all()
    {
        if(database == null) {
            return this.get();
        } else {
            return database;
        }
    }

}
