package todoapp;

import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * Created by shekhargulati on 09/06/14.
 */
public class Todo {

    private String id;
    private String starter;
    private String name;
    private String spicy;

    public String getMains() {
        return mains;
    }

    public void setMains(String mains) {
        this.mains = mains;
    }

    public String getStarter() {
        return starter;
    }

    public void setStarter(String starter) {
        this.starter = starter;
    }

    private String mains;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Todo(BasicDBObject dbObject) {
        this.id = ((ObjectId) dbObject.get("_id")).toString();
        this.mains = dbObject.getString("mains");
        this.starter = dbObject.getString("starter");
        this.name = dbObject.getString("name");
        this.spicy = dbObject.getString("spicy");

    }


    public String getSpicy() {
        return spicy;
    }

    public void setSpicy(String spicy) {
        this.spicy = spicy;
    }
}
