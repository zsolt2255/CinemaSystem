package com.topin.models;

import com.topin.db.DbJsonObject;
import com.topin.db.Query;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Time extends BaseModel {
    private Date start;
    private Date end;

    public Time(DbJsonObject jsonObject)
    {
        id = jsonObject.getInt("id");
        start = jsonObject.getDate("start");
        end = jsonObject.getDate("end");
    }

    public Time(String start, String end)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");

        try {
            this.start = formatter.parse(start);
            this.end = formatter.parse(end);
        } catch (ParseException ignored) { }
    }

    public static Time make(JSONObject jsonObject) { return jsonObject==null?null:new Time(DbJsonObject.create(jsonObject)); }
    public static ArrayList<Time> make(Query query)
    {
        return make(query.all());
    }
    public static ArrayList<Time> make(JSONArray jsonArray)
    {
        ArrayList<Time> objs = new ArrayList<>();
        jsonArray.forEach(item-> objs.add(new Time(DbJsonObject.create(item))));

        return objs;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public String getStartFormat()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy. MM. dd. HH:mm");
        return formatter.format(start);
    }

    public String getEndFormat()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy. MM. dd. HH:mm");
        return formatter.format(end);
    }

    public String getStartBaseFormat()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return formatter.format(start);
    }

    public String getEndBaseFormat()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return formatter.format(end);
    }

    @Override
    public String toString() {
        return "Time{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    public long difference(String start, String end) throws ParseException {
        String[] startToArray = start.split("\\.");
        String[] endToArray = end.split("\\.");
        //System.out.println("start"+startToArray[3]);
        //System.out.println("end"+endToArray[3]);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date1 = formatter.parse(startToArray[3]);
        Date date2 = formatter.parse(endToArray[3]);

        long difference = date2.getTime() - date1.getTime();

        return TimeUnit.MILLISECONDS.toHours(difference);
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
