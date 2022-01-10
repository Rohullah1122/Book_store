package com.example.book_store.classes;

public class cls_user {
    private String id,name,lastname,mail,phone,userimage,password;


    public cls_user() {

    }

    public cls_user(String id, String name, String lastname, String mail, String phone, String userimage, String password) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.mail = mail;
        this.phone = phone;
        this.userimage = userimage;
        this.password = password;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserimage() {
        return userimage;
    }

    public void setUserimage(String userimage) {
        this.userimage = userimage;
    }
}
