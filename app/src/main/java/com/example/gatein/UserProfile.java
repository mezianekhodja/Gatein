package com.example.gatein;

public class UserProfile {
    public String userEmail;
    public String userPhone;
    public String userName;
    public String userHoraires;

    public UserProfile(){

    }
    public UserProfile(String userName, String userEmail,String userPhone, String userHoraires){
        this.userName=userName;
        this.userEmail=userEmail;
        this.userPhone=userPhone;
        this.userHoraires=userHoraires;
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
}
