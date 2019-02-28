package com.example.morganeankonina.android5779_3884_9325_5513_2.control;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.morganeankonina.android5779_3884_9325_5513_2.R;
import com.example.morganeankonina.android5779_3884_9325_5513_2.entities.Travel;
import com.example.morganeankonina.android5779_3884_9325_5513_2.model.backend.Backend;
import com.example.morganeankonina.android5779_3884_9325_5513_2.model.backend.BackendFactory;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AvailableTravelsList} interface
 * to handle interaction events.
 * Use the {@link AvailableTravelsList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AvailableTravelsList extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //****************************
    BackendFactory backendFactory=new BackendFactory();
    Backend backend=backendFactory.getInstance();

    ArrayAdapter<Travel> adapter;
    ArrayList<Travel> listTravels;
    ListView listView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public AvailableTravelsList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AvailableTravelsList.
     */
    // TODO: Rename and change types and number of parameters
    public static AvailableTravelsList newInstance(String param1, String param2) {
        AvailableTravelsList fragment = new AvailableTravelsList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_available_travels_list, container, false);


        try {
            listView = (ListView) view.findViewById(R.id.available_listView);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Travel travel = listTravels.get(position);
                    AvailableTravelsDetails fragDetailsTravels = (AvailableTravelsDetails) getActivity().getFragmentManager().findFragmentById("AvailableTravelsDetailsTag");
                    //fragDetailsTravels.insertDetailsTravel(travel);
                }
            });
            //updateListView(BackendFactory.getInstance(getActivity()).getAllDriverRides(((DriverActivity) getActivity()).getCurrentDriver()));//put all the current driver rides in listView
            return view;
        } catch (Exception e) {
            Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return view;
        }
    }

        /**
         * this function calls when the list that in listView of this fragment need to change
         * @param list new list that will be in the listView
         */
        public void updateListView(ArrayList<Travel> list)
        {
            try {
                listTravels = list;//put the new list
                adapter=new ArrayAdapter<Travel>(getActivity(),R.layout.my_ride_item,myList){//adapter that connect between
                    /**
                     * this function calls on every Ride in myList.it design the Ride looking in listView
                     * @param position the Ride in position place in myList
                     * @param convertView the current view
                     * @param parent the parent of view
                     */

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getActivity(), R.layout.my_ride_item, null);//init convertView
            }
            //take view widgets from view that define in my_ride_item xml file
            TextView nameTextView = (TextView) convertView.findViewById(R.id.name_my_ride_item);
            TextView dateTextView = (TextView) convertView.findViewById(R.id.date_my_ride_item);
            TextView sourceTextView = (TextView) convertView.findViewById(R.id.source_my_ride_item);
            TextView destTextView = (TextView) convertView.findViewById(R.id.dest_my_ride_item);
            Button addContact = (Button) convertView.findViewById(R.id.button_my_add_contact);
            addContact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {//implement the onClick of button that add client of ride to contacr in phone
                    View parent = (View) view.getParent().getParent().getParent().getParent().getParent();
                    ListView ls = (ListView) parent.getParent();//the listView
                    int position = ls.getPositionForView(parent);//get the position of the ride that the user click on it's button-TakeRide
                    Travel travel = myList.get(position);//get ride
                    //insert to contact
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
                    intent.putExtra(ContactsContract.Intents.Insert.NAME, ride.getClientName());
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE, ride.getClientPhone());
                    startActivity(intent);
                }
            });
            Ride ride=(Ride)myList.get(position);//get the ride
            //show the ride details in row of listView like this:
            nameTextView.setText(ride.getClientName());
            dateTextView.setText(ride.getEndTime().toString());
            sourceTextView.setText(ride.getSourceLocation());
            destTextView.setText(ride.getDestinationLocation());
            return convertView;
        }
    };

            listView.setAdapter(adapter);//set the view
}
        catch (Exception e)
                {
                System.out.println(e.getMessage());
                }

                }

    /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            checkedDrive = (Travel) listView.getItemAtPosition(position);
            String detail ="";
            detail += getString(R.string.name)+ ": "+ checkedDrive.getNameClient() + "\n"
                    + getString(R.string.phone)+ ": " + checkedDrive.getPhoneClient() + "\n"
                    + getString(R.string.start_point)+ ": " + checkedDrive.getStartPointString() +"\n";
            detailDrive.setText(detail);
            ButtonAddToContact.setEnabled(true);
        }
    });
*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


}
