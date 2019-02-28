package com.example.morganeankonina.android5779_3884_9325_5513_2.model.datasource;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.morganeankonina.android5779_3884_9325_5513_2.entities.Driver;
import com.example.morganeankonina.android5779_3884_9325_5513_2.entities.Travel;
import com.example.morganeankonina.android5779_3884_9325_5513_2.model.backend.Backend;

import java.util.ArrayList;
import java.util.Date;

/**
 * Class DataBaseList to add travel in a list
 */
//Check later if works!!!!!!!!!!!!!!!!!!!!!!!
public class DataBaseList implements Backend {
    ArrayList<Travel> travels=new ArrayList<>();
    ArrayList<Driver> drivers=new ArrayList<>();
    Context context;

    /**
     * function addTravel to add travel to a list
     * @param travel
     * @throws Exception
     */
    //@Override
    public void addTravel(Travel travel) throws Exception {
        try{
            for (Travel item: travels)
            {
                if(item.equals(travel))
                    throw new Exception ("Travel already exist");
            }
            travels.add(travel);
            for (Travel item: travels) {
                System.out.println(item.toString());
            }
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public void addTravel(Travel travel, DataBaseFB.Action<Long> action) {

    }

    @Override
    public ArrayList<String> getTravels() {
        ArrayList<String> listTravels=new ArrayList<>();
        for(Travel item:travels){
            listTravels.add(item.toStringLocation());
        }
        return listTravels;
    }
    public String getTravel()
    {
        String travelsList="";
        if (travels.size()==0)
            return "No travels";
        for (Travel item:travels)
        {
            travelsList+=item.toString()+'\n';
        }
        return travelsList;
    }

    @Override
    public String addDriver(Driver driver) throws Exception {
        try{
            for (Driver item: drivers)
            {
                if(item.equals(driver))
                    throw new Exception ("Driver already exist");
            }
            drivers.add(driver);
            String allDrivers="";
            for (Driver item: drivers) {
                allDrivers+=item.toString();
            }
            return allDrivers;
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public ArrayList<Driver> getDrivers() {
        return drivers;
    }

    @Override
    public boolean checkDriverValid (Driver driver)
    {
        for(Driver item:drivers)
        {
            if (driver.equals(item))
                return true;
        }
        return false;
    }


    //////////////////////////////:
    @Override
    public ArrayList<String> getNamesDrivers() {
        return null;
    }

    @Override
    public ArrayList<Travel> getAvailableTravel() {
        return null;
    }

    @Override
    public ArrayList<Travel> getFinsishTravels() {
        return null;
    }

    @Override
    public ArrayList<Travel> getTravelsByDriver(Driver driver) {
        return null;
    }

    @Override
    public ArrayList<Travel> getTravelsByCity(String city) {
        return null;
    }

    @Override
    public ArrayList<Travel> getTravelsInDistance(Double distance) {
        return null;
    }

    @Override
    public ArrayList<Travel> getTravelsInDistance(Date date) {
        return null;
    }

    @Override
    public ArrayList<Travel> getTravelsPrice(Double price) {
        return null;
    }
    ///////////////////////////////////////
}
