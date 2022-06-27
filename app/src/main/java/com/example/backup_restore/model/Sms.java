package com.example.backup_restore.model;

import java.io.Serializable;

public class Sms implements Serializable {
    private String number;
    private String time;
    private String body;

    public Sms(String number, String time, String body) {
        this.number = number;
        this.time = time;
        this.body = body;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Sms{" +
                "number='" + number + '\'' +
                ", time='" + time + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
