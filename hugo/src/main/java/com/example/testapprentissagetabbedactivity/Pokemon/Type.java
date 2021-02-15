package com.example.testapprentissagetabbedactivity.Pokemon;

public class Type {
    String Url ;
    String name;

    public Type(String url, String name) {
        Url = url;
        this.name = name;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
