package crats.mvcbaseproject.model;
import java.util.ArrayList;

public class News {
    private int id;
    private String type, time, text, title;

    public News(int id, String title, String type, String text, String time){
        this.id = id;
        this.title = title;
        this.type = type;
        this.text = text;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }
}
