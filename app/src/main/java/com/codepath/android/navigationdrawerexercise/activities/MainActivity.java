package com.codepath.android.navigationdrawerexercise.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.codepath.android.navigationdrawerexercise.R;
import com.codepath.android.navigationdrawerexercise.fragments.FamilyGuyFragment;
import com.codepath.android.navigationdrawerexercise.fragments.FuturamaFragment;
import com.codepath.android.navigationdrawerexercise.fragments.SimpsonsFragment;
import com.codepath.android.navigationdrawerexercise.fragments.SouthParkFragment;

import static android.R.attr.fragment;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private NavigationView nvDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();
        mDrawer.addDrawerListener(drawerToggle);

        nvDrawer = ( NavigationView)findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });
    }

    private void selectDrawerItem(MenuItem item) {
        Fragment fragment = null;
        Class fragmentClass;

        switch(item.getItemId()){
            case R.id.family_guy:
                fragmentClass= FamilyGuyFragment.class;
                break;
            case R.id.futurama:
                fragmentClass= FuturamaFragment.class;
                break;
            case R.id.south_park:
                fragmentClass= SouthParkFragment.class;
                break;
            case R.id.the_simpsons:
                fragmentClass= SimpsonsFragment.class;
                break;
            default:
                fragmentClass=SimpsonsFragment.class;
        }

        try{
            fragment =(Fragment)fragmentClass.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent,fragment).commit();

        item.setChecked(true);
        setTitle(item.getTitle());
        mDrawer.closeDrawers();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer,toolbar,R.string.drawer_open,R.string.drawer_close );
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
