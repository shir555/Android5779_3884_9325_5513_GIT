package com.example.morganeankonina.android5779_3884_9325_5513_2.model.backend;


import com.example.morganeankonina.android5779_3884_9325_5513_2.entities.Driver;
import com.example.morganeankonina.android5779_3884_9325_5513_2.entities.Travel;

import java.util.ArrayList;
import java.util.List;

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
    public void addTravel(Travel travel) throws Exception;

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

}