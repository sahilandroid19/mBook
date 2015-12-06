package com.hfad.mbook;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;


public class Detail extends Activity {
    private int position;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //getting intent
        position = getIntent().getIntExtra("position", 0);
        //replacing fragments
        switch (position){
            case 0:
                fragment = new Detail1();
                getActionBar().setTitle("Daniel in the lion's den");
                break;
            case 1:
                fragment = new Detail2();
                getActionBar().setTitle("Action Comics");
                break;
            case 2:
                fragment = new Detail3();
                getActionBar().setTitle("My Big Book Of Action Stickers");
                break;
            case 3:
                fragment = new Detail4();
                getActionBar().setTitle("Big Hero");
                break;
        }
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.detail_frag, fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }
}