package com.example.Magpie;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("Home");

        bottomNavigationView = findViewById(R.id.homeBottomNav);

        getSupportFragmentManager().beginTransaction().replace(R.id.home_container, HomeFragment.getInstance(),
                HomeFragment.TAG).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.bottom_action_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_container, HomeFragment.getInstance(),
                                HomeFragment.TAG).commit();
                        break;
                    case R.id.bottom_action_account:
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_container, AccountFragment.getInstance(),
                                HomeFragment.TAG).commit();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}
