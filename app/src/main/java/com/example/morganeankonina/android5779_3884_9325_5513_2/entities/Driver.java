package com.example.morganeankonina.android5779_3884_9325_5513_2.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 * the Driver class describes the properties of the driver
 */
public class Driver implements Serializable{
    private String username;
    private String password;
    private String familyName;
    private String name;
    private String id;
    private String phone;
    private String email;
    private String visaCard;
    public Travel refTravel =new Travel();

    /**
     * Empty ctor
     * */
    public Driver() {
        this.username= "";
        this.password="";
        this.familyName = "";
        this.name = "";
        this.id = "";
        this.phone = "";
        this.email = "";
        this.visaCard = "";
        this.refTravel=null;
    }
    /**
     * Ctor
     */
    public Driver(String username, String password, String familyName, String name, String id, String phone, String email, String visaCard) {
        this.username = username;
        this.password = password;
        this.familyName = familyName;
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.visaCard = visaCard;
    }
    /**-------------------------Getters-------------------------------------------------------------
     */
    public String getFamilyName() {
        return familyName;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getVisaCard() {
        return visaCard;
    }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public Travel getRefTravel() { return refTravel; }

    /**-------------------------Setters-------------------------------------------------------------
     * */
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) { this.id = id; }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setVisaCard(String visaCard) {
        this.visaCard = visaCard;
    }

    public void setRefTravel(Travel refTravel) { this.refTravel = refTravel; }

    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) { this.password = password; }

    /**
     * toString function that represents the class Driver
     * @return String
     */
    @Override
    public String toString() {
        return "Driver{" +
                "username=" + username+'\'' +
                "password="+ password+'\'' +
                "familyName=" + familyName + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", visaCard=" + visaCard +
                ", travel=" + refTravel.toString()+
                '}';
    }

    /**
     * equals function chesks if two drivers are equal
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Driver driver = (Driver) obj;
        return (this.getUsername().equals(driver.getUsername()) && this.getPassword().equals(driver.getPassword()));
    }
}

