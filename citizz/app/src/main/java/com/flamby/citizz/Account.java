package com.flamby.citizz;

public class Account {

    public String name;
    public String fam_name;
    public String mail;
    public String password;
    public String city;
    public String region;
    public String department;

    public Account(String _name, String _fam_name, String _mail, String _password, String _city, String _region, String _department) {
        this.name = _name;
        this.fam_name = _fam_name;
        this.mail = _mail;
        this.password = _password;
        this.city = _city;
        this.region = _region;
        this.department = _department;
    }
}
