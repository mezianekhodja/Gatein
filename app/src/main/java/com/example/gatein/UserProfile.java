package com.example.gatein;

public class UserProfile {
    public String userEmail;
    public String userPhone;
    public String userName;
    public String userHoraires;
    public int userCounter;

    public UserProfile(){

    }
    public UserProfile(String userName, String userEmail,String userPhone, String userHoraires, int userCounter){
        this.userName=userName;
        this.userEmail=userEmail;
        this.userPhone=userPhone;
        this.userHoraires=userHoraires;
        this.userCounter=userCounter;
    }

    public String getUserEmail() {
        return userEmail;
    }
    public String getUserName() {
        return userName;
    }
    public String getUserPhone() {
        return userPhone;
    }
    public String getUserHoraires() {
        return userHoraires;
    }
    public int getUserCounter() {
        return userCounter;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
    public void setUserHoraires(String userHoraires) {
        this.userHoraires = userHoraires;
    }
    public void setUserCounter(int userCounter) {
        this.userCounter = userCounter;
    }
}
