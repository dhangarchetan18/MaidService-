package com.example.maidbooking;

public class MyModel {
    private int id;
    private String fname;
    private String lnmae;
    private String contact;
    private String email;
    private String city;

    public MyModel(int id, String fname, String lnmae, String contact, String city, String email) {
        this.id = id;
        this.fname = fname;
        this.lnmae = lnmae;
        this.contact=contact;

        //---------------27.03.23
        this.city=city;
        this.email=email;
                //=============end
//        this.email = email;
//        this.city = city;
    }
    public int getId(){
        return id;
    }

    public String getFname() {
        return fname;
    }
    public String getLnmae()
    {
        return lnmae;
    }
    public String getContact()
    {
        return contact;
    }
    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }
}
