package com.example.webmail;

/**
 * Created by User on 6/3/2016.
 */
public class UserPOJO {

    public String fullname;
    public String lastname;
    public String email;
    public String password;
    public String phone;

    public UserPOJO(String fullname, String email, String password){
        this.fullname=fullname;
        this.email=email;
        this.password=password;


    }

    public String getfullname(){ return fullname;}
    public void setfullname(String fullname){this.fullname=fullname;}



    public String getEmail(){ return email;}
    public void setEmail(String email){this.email=email;}

    public String getPassword(){ return password;}
    public void setPassword(String password){this.password=password;}


}
