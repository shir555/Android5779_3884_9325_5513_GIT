package com.example.morganeankonina.android5779_3884_9325_5513_2.entities;

import com.example.morganeankonina.android5779_3884_9325_5513_2.model.backend.Backend;
import com.example.morganeankonina.android5779_3884_9325_5513_2.model.backend.BackendFactory;
import com.example.morganeankonina.android5779_3884_9325_5513_2.model.datasource.DataBaseFB;

import java.sql.Time;
//import java.util.Random;

/**
 * The Travel class describes the properties of the travel
 */
public class Travel {
    public static int counter=0;

    public enum States {FREE, TREATMENT, FINISH}

    private String id;
    private States state;
    private String startLocation;
    private String destination;
    private Time startTime;
    private Time endTime;
    private String clientName;
    private String clientPhone;
    private String clientEmail;
    public int price;

    //public Random rand = new Random();
    /**
     * Empty ctor
     */
    public Travel() {
        this.id=String.valueOf(++counter);

        this.state = States.FREE;
        this.startLocation = "";
        this.destination = "";
        this.startTime = new Time(8,0,0);
        this.endTime = new Time(24,0,0);
        this.clientName = "";
        this.clientPhone = "";
        this.clientEmail = "";
        //this.price=rand.nextInt(40)+10;

    }

    /**
     * Ctor
     * @param state
     * @param startLocation
     * @param destination
     * @param startTime
     * @param endTime
     * @param clientName
     * @param clientPhone
     * @param clientEmail
     */
    public Travel(States state, String startLocation, String destination, Time startTime, Time endTime, String clientName, String clientPhone, String clientEmail) {
        this.id=String.valueOf(++counter);
        this.state = state;
        this.startLocation = startLocation;
        this.destination = destination;
        this.startTime = startTime;
        this.endTime = endTime;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.clientEmail = clientEmail;
        //this.price=rand.nextInt(40)+10;

    }
    /**-------------------------Getters-------------------------------------------------------------
     */
    public String getId() { return id;}

    public States getState() {
        return state;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public String getDestination() {
        return destination;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public String getClientEmail() {
        return clientEmail;
    }
    /**-------------------------Setters-------------------------------------------------------------
     */
    public void setState(States state) {
        this.state = state;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public void setDestiation(String destination) {
        this.destination = destination;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }
    /**
     * toString function that represents the class Driver
     * @return String
     */
    @Override
    public String toString() {
        return "Travel"+ '\n'+
                "state=" + state +'\n'+
                ", startLocation='" + startLocation + '\n' +
                ", destination='" + destination + '\n'+
                ", startTime=" + startTime + '\n'+
                ", endTime=" + endTime + '\n'+
                ", clientName='" + clientName +  '\n'+
                ", clientPhone=" + clientPhone + '\n'+
                ", clientEmail='" + clientEmail ;
    }

    public String toStringLocation() {
        return  "Start Location: " + startLocation + '\n' +
                "Destination: " + destination + '\n';
    }

    public String toStringClientDetails() {
        return "Name: " + clientName + '\n' +
                "Phone: " + clientPhone + '\n'+
                "Email: " + clientEmail;
    }

    /**
     * equals function chesks if two travels are equal
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Travel travel = (Travel) obj;
        return this.getId().equals(travel.getId());
    }
}


