package com.example.morganeankonina.android5779_3884_9325_5513_2.control;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.morganeankonina.android5779_3884_9325_5513_2.R;
import com.example.morganeankonina.android5779_3884_9325_5513_2.entities.Travel;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class AvailableTravelsDetails extends Fragment {

    TextView clientName;
    TextView clientPhone;
    TextView clientEmail;
    TextView start;
    TextView destination;

    public AvailableTravelsDetails() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_available_travels_details, container, false);
        try {
            //get all the textViews in view
            clientName = view.findViewById(R.id.available_details_name);
            clientPhone = view.findViewById(R.id.available_details_phone);
            clientEmail = view.findViewById(R.id.available_details_email);
            start = view.findViewById(R.id.available_details_start);
            destination = view.findViewById(R.id.available_details_dest);
            return view;
        }
        catch (Exception e)
        {
            Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return view;
        }
    }

    /**
     * this function get a ride and put his details in the last fragment.calls when the user click on listView element.
     * @param travel is the ride that user see it's details
     * pay attention that the ride details that will show dependent in the ride status.
     */
    public void updateRide(Travel travel)
    {
        try {
            //put a left drawable in all the textViews in this details fragment
            addLeftDrawable(getResources().getDrawable(R.drawable.ic_action_little_phone), clientPhone);
            addLeftDrawable(getResources().getDrawable(R.drawable.ic_action_little_email), clientEmail);
            addLeftDrawable(getResources().getDrawable(R.drawable.ic_action_little_location), start);
            addLeftDrawable(getResources().getDrawable(R.drawable.ic_action_little_location), destination);

            //put in all the textViews the values of the ride
            clientName.setText("Name: " + travel.getClientName());
            clientPhone.setText("Phone: " + travel.getClientPhone());
            clientEmail.setText("Email: " + travel.getClientEmail());
            start.setText("From: " + travel.getStartLocation());
            destination.setText("To: " + travel.getDestination());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void refresh() {
        try {
            //make all the left drawable of the textViews empty
            clientPhone.setCompoundDrawables(null, null, null, null);
            clientEmail.setCompoundDrawables(null, null, null, null);
            start.setCompoundDrawables(null, null, null, null);
            destination.setCompoundDrawables(null, null, null, null);

            //make all the text of the textViews ""
            clientName.setText("");
            clientPhone.setText("");
            clientEmail.setText("");
            start.setText("");
            destination.setText("");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * this function put a left image from drawable in textView.
     * @param d is wanted drawable image.
     * @param e is the textView that we want to put image in.
     */
    public void addLeftDrawable(Drawable d, TextView e)
    {
        try {
            d.setBounds(0, 0, 60, 60);
            e.setCompoundDrawables(d, null, null, null);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
