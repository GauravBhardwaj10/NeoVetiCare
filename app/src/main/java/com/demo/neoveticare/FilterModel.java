package com.demo.neoveticare;

public class FilterModel {
    String Schedule;
    boolean val;


    FilterModel(String Schedule,

                boolean val){

        this.Schedule=Schedule;
        this.val=val;

    }


    public String getSchedule(){
        return Schedule;
    }


    public  boolean  getval(){
        return val;
    }


}
