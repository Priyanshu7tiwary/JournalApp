package com.journal.journal.util;

import org.springframework.stereotype.Component;

@Component
public class Json {
    private String name;
    private String title;
    private String content;

    // Constructors
    public Json() {}

    public Json(String name, String title, String content) {
        this.name = name;
        this.title = title;
        this.content = content;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
