package com.example.testapprentissagetabbedactivity.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testapprentissagetabbedactivity.R;
import com.example.testapprentissagetabbedactivity.recyclerview.AdapterRecycler;
import com.example.testapprentissagetabbedactivity.recyclerview.Item;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {
    private RecyclerView mRecyclerView;
    private AdapterRecycler mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Item> exampleList;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
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
        createList();
        ajoutList(R.drawable.ic_android,"Line 1", "line2");
        ajoutList(R.drawable.ic_audio,"Line 3", "line4");
        ajoutList(R.drawable.ic_sun,"Line 5", "line6");
    }
    public void createList(){
        exampleList = new ArrayList<>();
    }
    public void ajoutList(int objet ,String txt1, String txt2){
        exampleList.add(new Item(objet, txt1, txt2));
    }
    public void setRecyclerView(View rootview) {
        mRecyclerView = rootview.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());

        mAdapter = new AdapterRecycler(exampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new AdapterRecycler.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                changeItem(position,"clicked");
            }

            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });





    }
    public void removeItem(int position){
        exampleList.remove(position);
        //mAdapter.notifyDataSetChanged();
        mAdapter.notifyItemRemoved(position);
    }

    public void changeItem(int position,String text)
    {
        exampleList.get(position).changeText1(text);
        mAdapter.notifyItemChanged(position);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment1_layout, container, false);
         setRecyclerView(rootView);
        return  rootView ;
    }
}