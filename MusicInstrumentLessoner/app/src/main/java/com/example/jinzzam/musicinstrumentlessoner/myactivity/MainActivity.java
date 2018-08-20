package com.example.jinzzam.musicinstrumentlessoner.myactivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.jinzzam.musicinstrumentlessoner.R;
import com.example.jinzzam.musicinstrumentlessoner.myfragment.GroupFragment;
import com.example.jinzzam.musicinstrumentlessoner.myfragment.NotificationFragment;
import com.example.jinzzam.musicinstrumentlessoner.myfragment.StoreFragment;
import com.example.jinzzam.musicinstrumentlessoner.myfragment.TemplateFragment;
import com.example.jinzzam.musicinstrumentlessoner.myview.MyNavigationView;
import com.example.jinzzam.musicinstrumentlessoner.session.Session;
import com.example.jinzzam.musicinstrumentlessoner.toggle.MyActionBarDrawerToggle;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static MainActivity instance;
    private static Session session;
    private static GroupFragment groupFragment;
    private static NotificationFragment notificationFragment;
    private static StoreFragment storeFragment;
    private static TemplateFragment templateFragment;
    private static ImageView ivUserMain;

    private String userName;
    private String userEmail;

    {
        instance = this;
        session = Session.getInstance();
        groupFragment = GroupFragment.getInstance();
        notificationFragment = NotificationFragment.getInstance();
        storeFragment = StoreFragment.getInstance();
        templateFragment = TemplateFragment.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setUserNameAndEmail();
        checkPermission();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        MyActionBarDrawerToggle toggle = new MyActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        MyNavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.flFragment, groupFragment);

        fragmentTransaction.add(R.id.flFragment, notificationFragment);
        fragmentTransaction.commit();
    }


    private void setUserNameAndEmail() {
        userName = getIntent().getStringExtra("actLoginName");
        userEmail = getIntent().getStringExtra("actLoginEmail");
    }

    private void checkPermission() {
    }

    public static MainActivity getInstance() {
        if (instance == null)
            instance = new MainActivity();
        return instance;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}
