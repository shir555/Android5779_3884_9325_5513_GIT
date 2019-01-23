package com.example.morganeankonina.android5779_3884_9325_5513_2.control;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.morganeankonina.android5779_3884_9325_5513_2.R;
import com.example.morganeankonina.android5779_3884_9325_5513_2.model.backend.Backend;
import com.example.morganeankonina.android5779_3884_9325_5513_2.model.backend.BackendFactory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyTravelsList.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyTravelsList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyTravelsList extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //****************************
    BackendFactory backendFactory=new BackendFactory();
    Backend backend=backendFactory.getInstance();

    ListView listView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MyTravelsList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyTravelsList.
     */
    // TODO: Rename and change types and number of parameters
    public static MyTravelsList newInstance(String param1, String param2) {
        MyTravelsList fragment = new MyTravelsList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_my_travels_list, container, false);
        listView=(ListView)view.findViewById(R.id.my_travels_list_view);
        new AsyncTask<Context, Void, Void>(){
            @Override
            protected Void doInBackground (Context... contexts)
            {
                try
                {
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(), R.layout.list_view_layout, R.id.rowItem, backend.getTravels());
                    listView.setAdapter(adapter);
                    return null;
                }
                catch(Exception e)
                {
                    Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                return null;
            }
        }.execute();

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
