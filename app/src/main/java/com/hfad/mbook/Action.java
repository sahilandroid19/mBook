package com.hfad.mbook;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;


public class Action extends Fragment {
    private String[] name = new String[10];
    private int[] type = new int[10];
    private int i=0;
    private RecyclerView view;

    public Action() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = (RecyclerView)inflater.inflate(R.layout.fragment_action, container, false);
        //getting data
        try{
            SQLiteOpenHelper helper = new mBookData(getActivity());
            SQLiteDatabase db = helper.getReadableDatabase();
            //getting data
            Cursor cursor = db.query("DATA", new String[] {"NAME", "TYPE"}, "TITLE=?", new String[] {"Action"},
                    null, null, null);
            //navigating cursor
            if(cursor.moveToFirst()){
                name[i] = cursor.getString(0);
                type[i] = cursor.getInt(1);
            }
            for(int j=0;j<3;j++) {
                if (cursor.moveToNext()) {
                    i++;
                    name[i] = cursor.getString(0);
                    type[i] = cursor.getInt(1);
                }
            }
            cursor.close();
            db.close();
        }catch(SQLiteException e){
            e.printStackTrace();
        }
        //passing data
        mBookAdapter adapter = new mBookAdapter(name, type);
        view.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        view.setLayoutManager(manager);
        //responding to clicks
        adapter.setListener(new mBookAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(inflater.getContext(), Detail.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
        return view;
    }
}

