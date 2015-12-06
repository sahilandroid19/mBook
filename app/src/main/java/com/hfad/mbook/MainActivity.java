package com.hfad.mbook;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends Activity {
    private String[] options;
    private ListView listView;
    private Fragment fragment;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private int positions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null) {
            positions = savedInstanceState.getInt("positions");
            selectItem(positions);
        }else{
            selectItem(0);
        }
        //giving enteries to listView
        options = getResources().getStringArray(R.array.optionss);
        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, options));
        //attaching listener to listView
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 selectItem(position);
            }
        };
        listView.setOnItemClickListener(listener);
        //setting drawer listener
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close){
          @Override
        public void onDrawerOpened(View view){
              super.onDrawerOpened(view);
              invalidateOptionsMenu();
          }
            @Override
        public void onDrawerClosed(View view){
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(toggle);
        //getting icon
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        //adding back stack listener
        getFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    @Override
                    public void onBackStackChanged() {
                        Fragment fragment1 = getFragmentManager().findFragmentByTag("tag");
                        if(fragment1 instanceof Favorites){
                            getActionBar().setTitle(options[0]);
                            listView.setItemChecked(0, true);
                        }if(fragment1 instanceof Action){
                            getActionBar().setTitle(options[1]);
                            listView.setItemChecked(1, true);
                        }if(fragment1 instanceof SelfHelp){
                            getActionBar().setTitle(options[2]);
                            listView.setItemChecked(2, true);
                        }if(fragment1 instanceof Children){
                            getActionBar().setTitle(options[3]);
                            listView.setItemChecked(3, true);
                        }
                    }
                });
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        boolean state = drawerLayout.isDrawerOpen(listView);
        MenuItem item = menu.findItem(R.id.action_settings).setVisible(!state);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onPostCreate(Bundle bundle){
    super.onPostCreate(bundle);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration configuration){
        super.onConfigurationChanged(configuration);
        toggle.onConfigurationChanged(configuration);
    }

    public void selectItem(int position){
        positions = position;
        switch (position) {
            case 0:
                fragment = new Favorites();
                break;
            case 1:
                fragment = new Action();
                break;
            case 2:
                fragment = new SelfHelp();
                break;
            case 3:
                fragment = new Children();
                break;
        }
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frag_container, fragment, "tag");
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.addToBackStack(null);
            transaction.commit();
        //changing action bar title
        options = getResources().getStringArray(R.array.optionss);
        getActionBar().setTitle(options[position]);
        //closing drawer
         drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        listView = (ListView)findViewById(R.id.listView);
        drawerLayout.closeDrawer(listView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
        bundle.putInt("positions", positions);
    }
}
