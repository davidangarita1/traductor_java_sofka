package com.sofka.model;

public class Model {
    String english;
    String spanish;

    public Model(String english, String spanish) {
        this.english = english;
        this.spanish = spanish;
    }

    public void setWord(String key) {
        if(key == "ES") {
            this.spanish = spanish;
        }
        if(key == "EN") {
            this.english = english;
        }
    }

    public String get(String key) {
        if(key == "ES") {
            return spanish;
        }
        if(key == "EN") {
            return english;
        }
        return english;
    }
}
