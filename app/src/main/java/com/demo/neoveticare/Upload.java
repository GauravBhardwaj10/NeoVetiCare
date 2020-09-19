package com.demo.neoveticare;

public class Upload {

    public String name;
    public String phone;
    public String address;
    public String url;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Upload() {
    }

    public Upload(String name, String url, String phone, String address) {
        this.name = name;
        this.url= url;
        this.phone=phone;
        this.address=address;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}
