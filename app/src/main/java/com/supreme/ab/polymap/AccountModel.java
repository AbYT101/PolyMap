package com.supreme.ab.polymap;

public class AccountModel {
    private String email;
    private String fullname;
    private String phoneno;
    private String sex;
    private String city;

    public AccountModel(String city, String email, String fullname,  String phoneno, String sex) {
        this.email = email;
        this.fullname = fullname;
        this.phoneno = phoneno;
        this.sex = sex;
        this.city = city;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
