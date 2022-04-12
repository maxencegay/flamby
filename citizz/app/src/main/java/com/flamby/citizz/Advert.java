package com.flamby.citizz;

public class Advert {
    public String type;
    public String title;
    public String description;
    public String address;
    public String city;
    public String region;
    public String department;
    public String mnemonic;
    //int imageId;

    public Advert(String _type,String _title,String _description, String _address, String _city, String _region, String _department,String _mnemonic) {

        this.type = _type;
        this.title = _title;
        this.description = _description;
        this.address = _address;
        this.city = _city;
        this.region = _region;
        this.department = _department;
        this.mnemonic = _mnemonic;
    }

    public String getType() {
        return type;
    }
    public String getTitle() {
        return title;
    }
    public String getMnemonic() {return mnemonic; }
    public String getDescription() {return description;}
    public String getAddress() {return address;}
    public String getCity() {return city;}
    public String getRegion() {return region;}
    public String getDepartment() {return department;}
}
