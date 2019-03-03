package com.example.morganeankonina.android5779_3884_9325_5513_2.model.backend;


import com.example.morganeankonina.android5779_3884_9325_5513_2.entities.Driver;
import com.example.morganeankonina.android5779_3884_9325_5513_2.entities.Travel;
import com.example.morganeankonina.android5779_3884_9325_5513_2.model.datasource.DataBaseFB;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Interface Backend to describe the form of the DB classes
 */
public interface Backend
{
    /**
     *
     * @param travel
     * @throws Exception
     */
    public void addTravel(Travel travel, final DataBaseFB.Action<Long> action);

    /**The function returns a list of all travels
     *
     * @return
     */
    public ArrayList<String> getTravels();
    /**
     *
     * @param driver
     * @return
     * @throws Exception
     */
    public String addDriver(Driver driver) throws Exception;
    public ArrayList<Driver> getDrivers();
    /**
     *
     * @param driver
     * @return
     */
    public boolean checkDriverValid (Driver driver);

    public ArrayList<String> getNamesDrivers();
    //check
    public ArrayList<Travel> getAvailableTravel();
    public ArrayList<Travel> getFinsishTravels();
    public ArrayList<Travel> getTravelsByDriver(Driver driver);
    public ArrayList<Travel> getTravelsByCity(String city);
    public ArrayList<Travel> getTravelsInDistance(Double distance);
    public ArrayList<Travel> getTravelsInDistance(Date date);
    public ArrayList<Travel> getTravelsPrice(Double price);
    public ArrayList<Travel> getFilteredTravels(String word);
    public ArrayList<Travel> getAllTravelDrivers(Driver driver);
    public Driver valid(String usernme, String password);
}