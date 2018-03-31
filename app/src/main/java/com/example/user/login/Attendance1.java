package com.example.user.login;

/**
 * Created by USER on 3/26/2018.
 */

public class Attendance1 {

    String roll;
    String username="";
    public Attendance1(String username, String roll) {
        this.roll = roll;
        this.username =username;
    }

    public String getfirstText()
    {
        return username;
    }
    public String getsecondText()
    {
        return roll;
    }
}
