package todoapp;

import com.google.gson.Gson;
import com.mongodb.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by shekhargulati on 09/06/14.
 */
public class TodoService {

    private final DB db;
    private final DBCollection collection;

    public TodoService(DB db) {
        this.db = db;
        this.collection = db.getCollection("todos");
    }

    public List<Todo> findAll() {
        List<Todo> todos = new ArrayList<>();
        DBCursor dbObjects = collection.find();
        while (dbObjects.hasNext()) {
            DBObject dbObject = dbObjects.next();
            todos.add(new Todo((BasicDBObject) dbObject));
        }
        return todos;
    }

    public void createNewTodo(String body) {
        Todo todo = new Gson().fromJson(body, Todo.class);
        collection.insert(new BasicDBObject("name", todo.getName()).append("mains", todo.getMains()).append("starter", todo.getStarter()).append("spicy", todo.getSpicy()));
    }

    public Todo find(String id) {
        System.out.println(id + " id ");
        return new Todo((BasicDBObject) collection.findOne(new BasicDBObject("_id", new ObjectId(id))));
    }

    public Todo update(String todoId, String body) {
        Todo todo = new Gson().fromJson(body, Todo.class);
        collection.update(new BasicDBObject("_id", new ObjectId(todoId)), new BasicDBObject("$set", new BasicDBObject("name", todo.getName())));
        collection.update(new BasicDBObject("_id", new ObjectId(todoId)), new BasicDBObject("$set", new BasicDBObject("mains", todo.getMains())));
        collection.update(new BasicDBObject("_id", new ObjectId(todoId)), new BasicDBObject("$set", new BasicDBObject("spicy", todo.getSpicy())));
        collection.update(new BasicDBObject("_id", new ObjectId(todoId)), new BasicDBObject("$set", new BasicDBObject("starter", todo.getStarter())));
        return this.find(todoId);
    }
}
