package com.topin.models;

import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.topin.db.DatabaseConnection;
import com.topin.db.DbJsonArray;
import com.topin.db.DbJsonObject;
import com.topin.db.Query;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class Route extends BaseModel {
    private String name;
    private ArrayList<Time> times = new ArrayList<>();
    private Time time;

    public Route(DbJsonObject jsonObject) {
        id = jsonObject.getInt("id");
        name = jsonObject.getString("name");

        if(jsonObject.get("times") instanceof String) {
            times = new ArrayList<>();
        } else if(jsonObject.getArray("times") != null) {
            times = (Time.make(jsonObject.getArray("times")));
        }
    }

    public Route(String name,Time time) {
        this.name = name;
        this.time = time;
    }

    public void addTime(Time time) {
        if(time.getEnd() == null || time.getStart() == null)
            return;

        DbJsonArray x = findData("routes["+id+"].times", DatabaseConnection.getInstance().getDatabase());

        //Map<String, String> datas = Map.of("id", String.valueOf(getNextId(x.get())),"start", time.getStartBaseFormat(), "arrive", time.getArriveBaseFormat());
        insertData(Map.of("id", getNextId(x.get()),"start", time.getStartBaseFormat(), "end", time.getEndBaseFormat()), x);
        // JSONObject jsonObject = new JSONObject();
        //datas.forEach(jsonObject::put);
        //database.get().add(datas);


        DatabaseConnection.getInstance().saveDatabase();
    }

    public static Route make(JSONObject jsonObject) { return jsonObject==null?null:new Route(DbJsonObject.create(jsonObject)); }
    public static ArrayList<Route> make(Query query)
    {
        return make(query.all());
    }
    public static ArrayList<Route> make(JSONArray jsonArray)
    {
        ArrayList<Route> objs = new ArrayList<>();
        jsonArray.forEach(item-> objs.add(new Route(DbJsonObject.create(item))));

        return objs;
    }

    public static ArrayList<Route> all() { return make(Route.all("routes").get()); }
    public void store() {
        store("routes");
    }

    @Override
    public String toString() {
        return "Route{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", times=" + times +
                '}';
    }

    @Override
    protected Map insert(int id)
    {
        //TODO nem adja hozzá a timesot
        return Map.of(
                "id", id,
                "name", name,
                    "times", new JSONArray()
        );
    }

    /**
     * @return void
     */
    public void delete() {
        if(id > 0)
            delete("routes", id);
    }

    @Override
    protected Map<String, String> save() {
        return null;
    }

    public ArrayList<Time> getTimes() {
        return times;
    }

    public String getName() {
        return name;
    }

    public int getTimeTableCount() {
        return times.size();
    }

    public static Route whereName(String name)
    {
        return make(Route.where("routes", Filter.filter(Criteria.where("name").is(name))).first());
    }

    public void getTime(int id)
    {
        DbJsonArray x = findData("routes["+id+"].times", DatabaseConnection.getInstance().getDatabase());
        System.out.println(x);
    }
}
