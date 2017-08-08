package com.tyh.studentmanager.entity;

import java.util.Date;

/**
 * Created by DWade on 2017/8/7.
 */
public class Student {
    private String id;
    private String name;
    private Date birthday;
    private String description;
    private int avgScore;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getAvgScore() {
        return avgScore;
    }
    public void setAvgScore(int avgScore) {
        this.avgScore = avgScore;
    }
    public Student(String id, String name, Date birthday, String description, int avgScore) {
        super();
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.description = description;
        this.avgScore = avgScore;
    }
    public Student() {
        super();
    }
}
