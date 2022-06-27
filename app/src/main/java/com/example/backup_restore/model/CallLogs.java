package com.example.backup_restore.model;

import java.io.Serializable;

public class CallLogs implements Serializable {
    private String number;
    private String name;
    private String duration;
    private String call;

    public CallLogs(String number, String name, String duration, String call) {
        this.number = number;
        this.name = name;
        this.duration = duration;
        this.call = call;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
    }
}
