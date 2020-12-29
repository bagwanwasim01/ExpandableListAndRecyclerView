package com.example.myapplication;

public class ListItem  {
    private String head;
    private String desc;
    private String timeTaken;

    public ListItem(String head, String desc,String timeTaken) {
        this.head = head;
        this.desc = desc;
        this.timeTaken = timeTaken;
    }

    public ListItem() {
    }

    public ListItem(String head, String desc) {
        this.head = head;
        this.desc = desc;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getDesc() { return desc; }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTimeTaken() {
        return String.valueOf(timeTaken);
    }

    public void setTimeTaken(String timeTaken) {
        this.timeTaken = timeTaken;
    }
}
