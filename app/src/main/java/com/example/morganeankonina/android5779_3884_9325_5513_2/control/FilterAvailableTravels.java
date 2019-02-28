package com.example.morganeankonina.android5779_3884_9325_5513_2.control;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.morganeankonina.android5779_3884_9325_5513_2.R;
import com.example.morganeankonina.android5779_3884_9325_5513_2.entities.Travel;
import com.example.morganeankonina.android5779_3884_9325_5513_2.model.backend.Backend;
import com.example.morganeankonina.android5779_3884_9325_5513_2.model.backend.BackendFactory;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class FilterAvailableTravels extends Fragment {

    BackendFactory backendFactory=new BackendFactory();
    Backend backend= backendFactory.getInstance();
    ArrayList<Travel> travelList;

    Button search;
    EditText serachWord;
    public FilterAvailableTravels() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter_available_travels, container, false);
        search=(Button)view.findViewById(R.id.filter_buttonSearch);
        String searchWord=((EditText)view.findViewById(R.id.filter_search)).getText().toString();
        travelList=backend.getFilterTravels(serachWord);
        AvailableTravelsList fragListTravels = (AvailableTravelsList) getActivity().getFragmentManager().findFragmentByTag("AvailableTravelsDetailsTag");
        fragListTravels.updateListView(travelList); 
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
