package com.example.morganeankonina.android5779_3884_9325_5513_2.model.datasource;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import com.example.morganeankonina.android5779_3884_9325_5513_2.entities.Driver;
import com.example.morganeankonina.android5779_3884_9325_5513_2.entities.Travel;
import com.example.morganeankonina.android5779_3884_9325_5513_2.model.backend.Backend;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;

/**
 * Class DataBaseFB to add travel in a FB
 */
public class DataBaseFB implements Backend {

    public interface Action<T> {
        void onSuccess(T obj);

        void onFailure(Exception exception);

        void onProgress(String status, double percent);
    }

    public interface NotifyDataChange<T> {
        void OnDataChanged(T obj);

        void onFailure(Exception exception);
    }

    static DatabaseReference travelsRef;
    static ArrayList<Travel> travelsList;

    static DatabaseReference driversRef;
    static ArrayList<Travel> driversList;

    static {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        travelsRef = database.getReference("Travel");
        travelsList = new ArrayList<>();
        travelsRef = database.getReference("Driver");
        travelsList = new ArrayList<>();
    }

    @Override
    public void addTravel(final Travel travel, final Action<Long> action) {
        String key = String.valueOf(travel.getId());
        travelsRef.child(key).setValue(travel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                action.onSuccess(new Long(travel.getId()));
                action.onProgress("upload travel data", 100);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                action.onFailure(e);
                action.onProgress("error upload travel data", 100);
            }
        });
    }

    /**
     * The function addTravel to add a travel to the DB
     *
     * @param
     * @throws Exception
     */
    /*@Override
    public void addTravel(Travel travel) throws Exception {
        try {
            //FirebaseDatabase database = FirebaseDatabase.getInstance();
            //DatabaseReference myRef = database.getReference("Travel");
            //String key = String.valueOf(travel.getId());
            //myRef.child(key).setValue(travel);
            ////String key= Integer.toString(travel.getId());
            //myRef.child(key).setValue(travel.toString());
        } catch (Exception e) {
            throw e;
        }
    }*/

    ///////////////////////////////////////:
    @Override
    public ArrayList<String> getTravels() {
        return null;
    }

    @Override
    public String toString() {
        return "DatabaseFB{}";
    }


    public void addDriverToFirebase(final Driver driver, final Action<String> action) {
        //String key = String.valueOf(driver.getId());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Driver");
        String key = String.valueOf(driver.getId());
        myRef.child(key).setValue(driver);
        ////String key= Integer.toString(travel.getId());
        (myRef.child(key).setValue(driver.toString())).addOnSuccessListener(new OnSuccessListener<Void>(){
        //driversRef.child(driver.getId()).setValue(driver).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                //action.onSuccess(new Long(driver.getId()));
                action.onSuccess(driver.getName());

                action.onProgress("upload driver data", 100);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                action.onFailure(e);
                action.onProgress("error upload driver data", 100);
            }
        });
    }

    @Override
    public String addDriver(Driver driver) throws Exception {
        addDriverToFirebase(driver, new Action<String>() {
            @Override
            public void onSuccess(String obj) {
                System.out.println("Successful");            }

            @Override
            public void onFailure(Exception exception) {
                System.out.println("Failure");
            }

            @Override
            public void onProgress(String status, double percent) {
                System.out.println("Progress");
            }
        });
        return null;
    }

    @Override
    public ArrayList<Driver> getDrivers() {
        return null;
    }

    @Override
    public boolean checkDriverValid(Driver driver) {
        return false;
    }

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
//////////////////////////////////////////////
}
