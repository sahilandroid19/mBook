package com.hfad.mbook;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;



public class Detail2 extends Fragment{
    private String story;
    private View view;
    private int hint = 0;
    private SQLiteDatabase db;
    private Cursor cursor;
    private LayoutInflater inflaters;

    public Detail2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflaters = inflater;
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_detail2, container, false);
        //attaching checkbox
        CheckBox checkBox1 = (CheckBox) view.findViewById(R.id.checkbox2);
        checkBox1.setOnClickListener(listener);
        try {
            SQLiteOpenHelper helper = new mBookData(inflater.getContext());
            db = helper.getReadableDatabase();
            //getting data
            cursor = db.query("DATA", new String[]{"STORY", "VALUE"}, "NAME=?", new String[]{"Action Comics"},
                    null, null, null);
            //navigating cursor
            if (cursor.moveToFirst()) {
                story = cursor.getString(0);
                hint = cursor.getInt(1);
            }
            //getting cursor value

        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        TextView textView = (TextView) view.findViewById(R.id.detail2);
        textView.setText(story);
        if (hint == 1) {
            CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox2);
            checkBox.setChecked(true);
        }

        return view;
    }

    //called when check box is clicked
    public void check2() {
        int state;
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox2);
        if(checkBox.isChecked()){
            state = 1;
        }else{
            state = 0;
        }
        try {
            SQLiteOpenHelper helper = new mBookData(inflaters.getContext());
            db = helper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("VALUE", state);
            db.update("DATA", values, "NAME=?", new String[]{"Action Comics"});
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    //responding to click
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            check2();
        }
    };

    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }

}
