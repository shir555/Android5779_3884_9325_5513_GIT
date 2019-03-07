package com.example.morganeankonina.android5779_3884_9325_5513_2.model.datasource;

import android.support.annotation.NonNull;

import com.example.morganeankonina.android5779_3884_9325_5513_2.entities.Driver;
import com.example.morganeankonina.android5779_3884_9325_5513_2.entities.Travel;
import com.example.morganeankonina.android5779_3884_9325_5513_2.model.backend.Backend;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class DataBaseFB to add travel in a FB
 */
public class DataBaseFB implements Backend {

    public DataBaseFB() {
/*       //driversRef.keepSynced(true);
        //travelsRef.keepSynced(true);

        notifyToTravelList(new NotifyDataChange<List<Travel>>() {//register listener to rideRefChildEventListener,serviceRefChildEventListener
        @Override
        public void onDataChanged(List<Travel> obj) { }
        @Override
        public void onFailure(Exception exception) { }
    });*/
/*    notifyToDriverList(new NotifyDataChange<List<Driver>>() {//register listener to driverRefChildEventListener
        @Override
        public void onDataChanged(List<Driver> obj) { }
        @Override
        public void onFailure(Exception exception) { }
    });*/
}

    public interface Action<T> {
        void onSuccess(T obj);

        void onFailure(Exception exception);

        void onProgress(String status, double percent);
    }

    public interface NotifyDataChange<T> {
        void onDataChanged(T obj);

        void onFailure(Exception exception);
    }

    static DatabaseReference travelsRef;
    static ArrayList<Travel> travelsList;

    static DatabaseReference driversRef;
    static ArrayList<Driver> driversList;

    static {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        travelsRef = database.getReference("Travel");
        travelsList = new ArrayList<>();
        driversRef = database.getReference("Driver");
        driversList = new ArrayList<>();
    }

    private static ChildEventListener travelRefChildEventListener;//listener of the travels
    private static ChildEventListener driverRefChildEventListener;//listener of the Drivers
    private static ChildEventListener serviceListener;//listener of the rides list in the fire base for the service-broadcast

    public void notifyToTravelList(final NotifyDataChange<List<Travel>> notifyDataChange) {
        try {
            if (notifyDataChange != null) {
                if (travelRefChildEventListener != null) {//if there is already an event
                    if (serviceListener != null) {//if there is already an event
                        notifyDataChange.onFailure(new Exception("unsuccessful data change"));
                        return;
                    } else {
                        serviceListener = new ChildEventListener() {//create service Listener event
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                notifyDataChange.onDataChanged(travelsList);
                            }

                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                            }

                            @Override
                            public void onChildRemoved(DataSnapshot dataSnapshot) {
                            }

                            @Override
                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        };
                        travelsRef.addChildEventListener(serviceListener);//add rideRef the serviceListener event
                        return;
                    }
                }
                travelsList.clear();
                travelRefChildEventListener = new ChildEventListener() {//write rideRefListener event
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {//react when there is child added in firebase
                        try {
                            Travel travel = convertDataSnapshotToTravel(dataSnapshot);//the func returns ride from the dataSnapshot
                            travelsList.add(travel);
                            notifyDataChange.onDataChanged(travelsList);
                        } catch (Exception E) {
                            System.out.println(E.getMessage());
                        }
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {//react when there is child changed in firebase
                        try {
                            Travel travel = convertDataSnapshotToTravel(dataSnapshot);//the func returns ride from the dataSnapshot
                            for (int i = 0; i < travelsList.size(); i++) {
                                if (!travelsList.get(i).equals(travel) && travelsList.get(i).getId() ==travel.getId()) {
                                    travelsList.set(i, travel);
                                    break;
                                }
                            }
                            notifyDataChange.onDataChanged(travelsList);
                        } catch (Exception E) {
                            System.out.println(E.getMessage());
                        }
                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {//react when there is child removed from firebase
                        try {
                            Travel travel = convertDataSnapshotToTravel(dataSnapshot);//the func returns ride from the dataSnapshot

                            for (int i = 0; i < travelsList.size(); i++) {
                                if (travelsList.get(i).equals(travel)) {
                                    travelsList.remove(i);
                                    break;
                                }
                            }
                            notifyDataChange.onDataChanged(travelsList);
                        } catch (Exception E) {
                            System.out.println(E.getMessage());
                        }

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        notifyDataChange.onFailure(databaseError.toException());

                    }
                };
                travelsRef.addChildEventListener(travelRefChildEventListener);//add rideRef the rideRefListener event

            }
        }
        catch (Exception E)
        {
            System.out.println(E.getMessage());
        }
    }

    /**
     * this function call when we want to stop listener to firebase
     */
    public void stopNotifyToRideList() {
        try {
            if (travelRefChildEventListener != null) {
                travelsRef.removeEventListener(travelRefChildEventListener);//remove listener
                travelRefChildEventListener = null;
            }
        }
        catch (Exception E)
        {
            System.out.println(E.getMessage());
        }
    }



    //////driver
    /**
     * this function call in constructor of DatabaseFB.it's start the event listeners for driverRef
     * @param driverNotifyDataChange - iterface implement that define reacting for a changes in firebase
     */
    public void notifyToDriverList(final NotifyDataChange<List<Driver>> driverNotifyDataChange) {
        try {
            if (driverNotifyDataChange != null) {
                if (driverRefChildEventListener != null) {//if there is already listener
                    driverNotifyDataChange.onFailure(new Exception("unNotify drivers list"));
                    return;
                }
                //driversList.clear();
                driverRefChildEventListener = new ChildEventListener() {//write driverRefListener event
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {//react when there is child added to firebase
                        Driver driver = dataSnapshot.getValue(Driver.class); //it converts driver from the dataSnapshot
                        driversList.add(driver);
                        driverNotifyDataChange.onDataChanged(driversList);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {//react when there is child changed in firebase
                        Driver driver = dataSnapshot.getValue(Driver.class); //it converts driver from the dataSnapshot

                        for (int i = 0; i < driversList.size(); i++) {
                            if (driversList.get(i).equals(driver)) {
                                driversList.set(i, driver);
                                break;
                            }
                        }
                        driverNotifyDataChange.onDataChanged(driversList);
                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {//react when there is child removed from firebase
                        Driver driver = dataSnapshot.getValue(Driver.class); //it converts driver from the dataSnapshot
                        for (int i = 0; i < driversList.size(); i++) {
                            if (driversList.get(i).equals(driver)) {
                                driversList.remove(i);
                                break;
                            }
                        }
                        driverNotifyDataChange.onDataChanged(driversList);

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        driverNotifyDataChange.onFailure(databaseError.toException());

                    }
                };
                driversRef.addChildEventListener(driverRefChildEventListener);//add driverRef the driverRefListener event

            }
        } catch (Exception E) {
            System.out.println(E.getMessage());
        }
    }

    /**
     * this function call when we want to stop listener to firebase
     */
   /* public void stopNotifyToDriverList() {
        try {
            if (driverRefChildEventListener != null) {
                driversRef.removeEventListener(driverRefChildEventListener);//remove event from driverRef
                driverRefChildEventListener = null;
            }
        }
        catch (Exception E)
        {
            System.out.println(E.getMessage());
        }
    }*/

    @Override
    public void addTravel(final Travel travel, final Action<Long> action) {
        String key = String.valueOf(travel.getId());
        travelsRef.child(key).setValue(travel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                action.onSuccess(new Long(travel.getId()));
                System.out.println("hello");
                travelsList.add(travel);
                System.out.println(travelsList);
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
        String key = driver.getId();
        System.out.println("problemeeee");
        //myRef.child(key).setValue(driver);
        driversRef.child(key).setValue(driver.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
            //driversRef.child(driver.getId()).setValue(driver).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                //action.onSuccess(new Long(driver.getId()));
                action.onSuccess(driver.getId());
                driversList.add(driver);
                System.out.println(driversList);
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
                System.out.println("Successful");
            }
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

        for(Driver item:driversList)
        {
            if (driver.equals(item))
                return true;
        }
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

    @Override
    public Driver getDriverByDetails(String un, String pass) {
        return valid(un, pass);
    }

    /////////////////////////////////////////////
    @Override
    public ArrayList<Travel> getFilteredTravels(String word) {
        ArrayList<Travel> matchingTravels = new ArrayList<>();
        for (Travel item : travelsList) {
            if (item.getClientName().equals(word) || item.getClientEmail().equals(word) ||
                    item.getClientPhone().equals(word) || item.getStartLocation().equals(word) ||
                    item.getDestination().equals(word)) {
                matchingTravels.add(item);
            }
        }
        return matchingTravels;
    }

    @Override
    public ArrayList<Travel> getAllTravelDrivers(Driver driver) {
        ArrayList<Travel> matchingTravels = new ArrayList<>();
        for (Travel item : travelsList) {
            if (driver.getRefTravel().equals(item)) {
                matchingTravels.add(item);
            }
        }
        return matchingTravels;
    }

    @Override
    public Driver valid(String username, String password){
        try {
            for (Driver item : driversList) {
                if (username.equals(item.getUsername()) && password.equals(item.getPassword())) {
                    return item;
                }

            }
            return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public Travel convertDataSnapshotToTravel(DataSnapshot dataSnapshot )
    {
        try {
            Travel.States state =Travel.States.valueOf(dataSnapshot.child("rideStatus").getValue().toString());
            String startLocation = (String) dataSnapshot.child("sourceLocation").getValue();
            String destination = (String) dataSnapshot.child("destinationLocation").getValue();
            long beginningTimeHours = (long) dataSnapshot.child("beginningTime").child("hours").getValue();
            long beginningTimeMinutes = (long) dataSnapshot.child("beginningTime").child("minutes").getValue();
            long beginningTimeSeconds = (long) dataSnapshot.child("beginningTime").child("seconds").getValue();
            Time startTime = new Time((int) beginningTimeHours, (int) beginningTimeMinutes, (int) beginningTimeSeconds);
            long endTimeHours = (long) dataSnapshot.child("endTime").child("hours").getValue();
            long endTimeMinutes = (long) dataSnapshot.child("endTime").child("minutes").getValue();
            long endTimeSeconds = (long) dataSnapshot.child("endTime").child("seconds").getValue();

            Time endTime = new Time((int) endTimeHours, (int) endTimeMinutes, (int) endTimeSeconds);
            String clientName = (String) dataSnapshot.child("clientName").getValue();
            String clientPhone = (String) dataSnapshot.child("clientPhone").getValue();
            String clientEmail = (String) dataSnapshot.child("clientEmail").getValue();
            return new Travel( state,startLocation, destination,startTime, endTime,  clientName,  clientPhone, clientEmail);
        }
        catch (Exception E)
        {
            System.out.println(E.getMessage());
            return (new Travel());
        }
    }
}




