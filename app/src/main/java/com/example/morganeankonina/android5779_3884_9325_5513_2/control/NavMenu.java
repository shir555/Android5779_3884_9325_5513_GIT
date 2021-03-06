package com.example.morganeankonina.android5779_3884_9325_5513_2.control;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.morganeankonina.android5779_3884_9325_5513_2.R;
import com.example.morganeankonina.android5779_3884_9325_5513_2.entities.Driver;

//this is shir liya trying to save changes
public class NavMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Driver driver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent=getIntent();
        //driver= (Driver) intent.getSerializableExtra("driver");
        String pass=intent.getStringExtra("password" );
        String un=intent.getStringExtra("username" );
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.help) {
            Intent help= new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("https://gett.com/il/faq/drivers/"));
            startActivity(help);

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //////check
        FragmentManager manager=getFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        FilterAvailableTravels filterFrag=new FilterAvailableTravels();
        AvailableTravelsList listFrag=new AvailableTravelsList();
        AvailableTravelsDetails detailsFrag=new AvailableTravelsDetails();
        /////

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_avTravels) {
            Intent available = new Intent(NavMenu.this,AvailableTravels.class);
            //available.putExtra("username", driver.getUsername());
            //available.putExtra("password", driver.getPassword());
            startActivity(available);

            //transaction.add(R.id.filter_word,filterFrag, "AvailableTravelsFilterTag");
            //transaction.replace(R.id.availableList, listFrag, "AvailableTravelsListTag");
            //transaction.replace(R.id.availableDetails, detailsFrag, "AvailableTravelsDetailsTag");
            //transaction.commit();

        }
        else if (id == R.id.nav_myTavels) {
            Intent myTravels= new Intent(NavMenu.this, MyTravels.class);
            startActivity(myTravels);
        }
        else if (id == R.id.nav_exit) {
            finish();
            moveTaskToBack(true);        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
