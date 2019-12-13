package com.topin.models;

import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.topin.db.DbJsonObject;
import org.json.simple.JSONArray;

import java.util.Map;

public class Worker extends BaseModel {

    public DbJsonObject getPurchases() {
        return DbJsonObject.create(User.where("users", Filter.filter(Criteria.where("username").is("user"))).first());
    }

    @Override
    protected Map<String, String> insert(int id) {
        return null;
    }

    @Override
    protected Map<String, String> save() {
        return null;
    }
}
