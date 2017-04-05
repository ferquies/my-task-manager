package com.ferquies.mytaskmanager.domain.model;

import java.security.Timestamp;
import java.util.Date;

/**
 * Created by ferqu on 01/04/2017.
 */

public class Task {

    private String name;
    private String description;
    private Date start;
    private Date end;
    private Timestamp time;

    public Task() {}

    public Task(String name, String description, Date start, Date end) {
        this.name = name;
        this.description = description;
        this.start = start;
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

}
