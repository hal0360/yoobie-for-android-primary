package nz.co.udenbrothers.yoobie;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import nz.co.udenbrothers.yoobie.abstractions.RootActivity;
import nz.co.udenbrothers.yoobie.wigets.PageView;
import nz.co.udenbrothers.yoobie.wigets.TabView;
import nz.co.udenbrothers.yoobie.wigets.WaveView;

public class MainActivity extends RootActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private  WaveView waveView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        waveView = findViewById(R.id.waveView);

        overlayPermission();

        PageView pageView =  findViewById(R.id.container);
        pageView.add(new HomeFragment());
        pageView.add(new ProfileFragment());
        pageView.add(new PrizeFragment());
        pageView.add(new SettingFragment());

        TabView tabView = findViewById(R.id.tabs);
        tabView.addPageListner(pageView);
        tabView.selected(t -> {
            if(t.getPosition() == 0){
                waveView.setProgress(0.83f);
                t.setIcon(R.drawable.dark_home);
            }
            else if(t.getPosition() == 1) {
                waveView.setProgress(0.63f);
                t.setIcon(R.drawable.dark_profile);
            }
            else if(t.getPosition() == 2) {
                waveView.setProgress(0.43f);
                t.setIcon(R.drawable.dark_prize);
            }
            else{
                waveView.setProgress(0.23f);
                t.setIcon(R.drawable.dark_setting);
            }
        });
        tabView.unSelected(t -> {
            if(t.getPosition() == 0) t.setIcon(R.drawable.light_home);
            else if(t.getPosition() == 1) t.setIcon(R.drawable.light_profile);
            else if(t.getPosition() == 2) t.setIcon(R.drawable.light_prize);
            else t.setIcon(R.drawable.light_setting);
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        delay(300, () -> waveView.setProgress(0.83f));
        overlayPermission();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) drawer.closeDrawer(GravityCompat.START);
        else super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {return false;}

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /*
        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }*/


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
