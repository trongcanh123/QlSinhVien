package com.example.qlsv.model;

import java.util.Date;

public class student {
    private String Id;
    private String name;
    private String dob;

    public student() {
    }

    public student(String id, String name, String dob) {
        Id = id;
        this.name = name;
        this.dob = dob;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
