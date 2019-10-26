package com.example.demo;

public class Ride {

    private final long id;
    private final Object[] content;

    public Ride(long id, Object[] content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public Object[] getContent() {
        return content;
    }
}
