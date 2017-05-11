package com.example.android.health.activities;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.health.R;
import com.example.android.health.adapter.ListOfDoctorAdapter;
import com.example.android.health.sharedPreferences.SessionManager;

public class DoctorListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {
    GridView doctorList;
    ListOfDoctorAdapter doctorAdapter;
    Intent intent=null;
    private SessionManager mainSessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);
        mainSessionManager = new SessionManager(DoctorListActivity.this);

        //Doctor Grid implementation starts here......
        doctorList= (GridView) findViewById(R.id.doctorList);
        doctorAdapter=new ListOfDoctorAdapter(this);
        doctorList.setAdapter(doctorAdapter);
        doctorList.setOnItemClickListener(this);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
       // getMenuInflater().inflate(R.menu.doctor_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_place) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.emergencyNumber) {
            startActivity(new Intent(DoctorListActivity.this, EmergencyActivity.class));
            // Handle the camera action
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch(i){
            case 0:
                intent=new Intent(DoctorListActivity.this,MapsActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent=new Intent(DoctorListActivity.this,CardiologistActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent=new Intent(DoctorListActivity.this,DentistActivity.class);
                startActivity(intent);
                break;
            case 3:
                intent=new Intent(DoctorListActivity.this,DermatologistActivity.class);
                startActivity(intent);
                break;
            case 4:
                intent=new Intent(DoctorListActivity.this,DietitionActivity.class);
                startActivity(intent);
                break;
            case 5:
                intent=new Intent(DoctorListActivity.this,EarnoseActivity.class);
                startActivity(intent);
                break;
            case 6:
                intent=new Intent(DoctorListActivity.this,GastroenterologistActivity.class);
                startActivity(intent);
                break;
            case 7:
                intent=new Intent(DoctorListActivity.this,GeneralphysicianActivity.class);
                startActivity(intent);
                break;
            case 8:
                intent=new Intent(DoctorListActivity.this,GynecologistActivity.class);
                startActivity(intent);
                break;
            case 9:
                intent=new Intent(DoctorListActivity.this,HomeopathActivity.class);
                startActivity(intent);
                break;
            case 10:
                intent=new Intent(DoctorListActivity.this,NeurologistActivity.class);
                startActivity(intent);
                break;
            case 11:
                intent=new Intent(DoctorListActivity.this,OphthalmologistActivity.class);
                startActivity(intent);
                break;
            case 12:
                intent=new Intent(DoctorListActivity.this,OrthopedistActivity.class);
                startActivity(intent);
                break;
            case 13:
                intent=new Intent(DoctorListActivity.this,PediatricianActivity.class);
                startActivity(intent);
                break;
            case 14:
                intent=new Intent(DoctorListActivity.this,PhysiotherapistActivity.class);
                startActivity(intent);
                break;
            case 15:
                intent=new Intent(DoctorListActivity.this,PsychiatristActivity.class);
                startActivity(intent);
                break;
            case 16:
                intent=new Intent(DoctorListActivity.this,UrologyActivity.class);
                startActivity(intent);
                break;
            case 17:
                intent=new Intent(DoctorListActivity.this,AyurvedaActivity.class);
                startActivity(intent);
                break;

        }
    }
}
