package com.demo.neoveticare;

import java.util.ArrayList;
import java.util.List;

public class UploadSeniorPartTimepojo {

    public String name;
    public String phone;
    public String address;
    public String writaboutyourself;
    public String experience;
    public String timings;

    public  String gender;
    public  String jobtype;
    public String url;

    List<String> schedulelist=new ArrayList<String>();
    public String provience;
    public String city;
    public String emailaddress;
    public String price;
    public String age;


    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public UploadSeniorPartTimepojo() {
    }

    public UploadSeniorPartTimepojo(String name, String phone, String address, String writaboutyourself, String experience, String timings, String jobtype, String url,String gender,List<String> schedulelist,String provience,String city,String emailaddress,String price,String age) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.writaboutyourself = writaboutyourself;
        this.experience = experience;
        this.timings = timings;
        this.gender=gender;
        this.jobtype = jobtype;
        this.url = url;
        this.schedulelist=schedulelist;
        this.provience=provience;
        this.city=city;
        this.emailaddress=emailaddress;
        this.price=price;
        this.age=age;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getWritaboutyourself() {
        return writaboutyourself;
    }

    public String getExperience() {
        return experience;
    }

    public String getTimings() {
        return timings;
    }


    public String getGender() {
        return gender;
    }

    public String getJobtype() {
        return jobtype;
    }

    public String getUrl() {
        return url;
    }

    public List<String> getSchedulelist() {
        return schedulelist;
    }

    public String getProvience() {
        return provience;
    }

    public String getCity() {
        return city;
    }
    public String getEmailaddress() {
        return emailaddress;
    }

    public String getPrice() {
        return price;
    }

    public String getAge() {
        return age;
    }
}
