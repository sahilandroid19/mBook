package com.hfad.mbook;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Favorites extends Fragment {
private View view;
    private SQLiteDatabase db;
    private Cursor cursor1, cursor2, cursor3, cursor4;
    private String[] name = new String[10];
    private int[] type = new int[10];
    private int i=0;

    public Favorites() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favorites, container, false);
        //getting reference
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycle);
        //getting data
        try{
            SQLiteOpenHelper helper = new mBookData(inflater.getContext());
            db = helper.getReadableDatabase();
            cursor1 = db.query("DATA", new String[] {"NAME", "TYPE"}, "NAME=? AND VALUE=?", new String[]{"Daniel in the lion's den", Integer.toString(1)},
                    null, null, null);
            if(cursor1.moveToFirst()){
                name[i] = cursor1.getString(0);
                type[i] = cursor1.getInt(1);
                i++;
            }
            cursor2 = db.query("DATA", new String[] {"NAME", "TYPE"}, "NAME=? AND VALUE=?", new String[]{"Action Comics", Integer.toString(1)},
                    null, null, null);
            if(cursor2.moveToFirst()){
                name[i] = cursor2.getString(0);
                type[i] = cursor2.getInt(1);
                i++;
            }
            cursor3 = db.query("DATA", new String[] {"NAME", "TYPE"}, "NAME=? AND VALUE=?", new String[]{"My Big Book Of Action Stickers", Integer.toString(1)},
                    null, null, null);
            if(cursor3.moveToFirst()){
                name[i] = cursor3.getString(0);
                type[i] = cursor3.getInt(1);
                i++;
            }
            cursor4 = db.query("DATA", new String[] {"NAME", "TYPE"}, "NAME=? AND VALUE=?", new String[]{"Big Hero", Integer.toString(1)},
                    null, null, null);
            if(cursor4.moveToFirst()){
                name[i] = cursor4.getString(0);
                type[i] = cursor4.getInt(1);
                i++;
            }

        }catch (SQLiteException e){
            e.printStackTrace();
        }
        //passing data to adapter
      mBookAdapter  adapter = new mBookAdapter(name, type);
        recyclerView.setAdapter(adapter);
        //setting layout manager
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        return view;
    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor1.close();
        cursor2.close();
        cursor3.close();
        cursor4.close();
        db.close();
    }

}
