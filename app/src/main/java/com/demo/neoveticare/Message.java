package com.demo.neoveticare;


public class Message {

    String id, emailfrom, emailto, message, datetime;

    public Message() {
    }

    public Message(String id, String emailfrom, String emailto, String message, String datetime) {
        this.id = id;
        this.emailfrom = emailfrom;
        this.emailto = emailto;
        this.message = message;
        this.datetime = datetime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmailfrom() {
        return emailfrom;
    }

    public void setEmailfrom(String emailfrom) {
        this.emailfrom = emailfrom;
    }

    public String getEmailto() {
        return emailto;
    }

    public void setEmailto(String emailto) {
        this.emailto = emailto;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
