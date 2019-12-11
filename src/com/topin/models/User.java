package com.topin.models;

import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.topin.db.DbJsonObject;
import com.topin.db.Query;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class User extends BaseModel {

    private String username;
    private String password;
    private String role;

    /**
     * @param dbJsonObject
     */
    public User(DbJsonObject dbJsonObject) {
        id = dbJsonObject.getInt("id");
        username = dbJsonObject.getString("username");
        password = dbJsonObject.getString("password");
        role = dbJsonObject.getString("role");
    }

    /**
     * @param username
     * @param password
     * @param role
     */
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }


    /**
     * @param jsonObject
     * @return User
     */
    public static User make(JSONObject jsonObject) {
        return jsonObject==null?null:new User(DbJsonObject.create(jsonObject));
    }

    /**
     * @param query
     * @return ArrayList
     */
    public static ArrayList<User> make(Query query) {
        return make(query.all());
    }

    /**
     * @param jsonArray
     * @return ArrayList
     */
    public static ArrayList<User> make(JSONArray jsonArray) {
        ArrayList<User> users = new ArrayList<>();
        jsonArray.forEach(item-> users.add(new User(new DbJsonObject((JSONObject) item))));

        return users;
    }

    /**
     * @return ArrayList
     */
    public static ArrayList<User> all() {
        return make(User.all("users").get());
    }

    /**
     * @param username
     * @return User
     */
    public static User whereUsername(String username) {
        return make(User.where("users", Filter.filter(Criteria.where("username").is(username))).first());
    }

    /**
     * @param id
     * @return Map
     */
    @Override
    protected Map<String, String> insert(int id) {
        return Map.of("id", String.valueOf(id), "username", username, "password", password, "role", role);
    }


    /**
     * @return Map
     */
    @Override
    protected Map<String, String> save() {
        return null;
    }


    /**
     * @return void
     */
    public void store() {
        store("users");
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    /**
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return String
     */
    public String getRole() {
        return role.toLowerCase();
    }

    /**
     * @return void
     */
    public void delete() {
        if(id > 0)
            delete("users", id);
    }

    /**
     * @return boolean
     */
    public boolean isUser() {
        return getRole().equals("user");
    }

    /**
     * @return boolean
     */
    public boolean isWorker() {
        return getRole().equals("worker");
    }

    /**
     * @return boolean
     */
    public boolean isAdmin() {
        return getRole().equals("admin");
    }

}
