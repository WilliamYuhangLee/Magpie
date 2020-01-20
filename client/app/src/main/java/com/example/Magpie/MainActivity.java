package com.example.Magpie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
//import android.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

//    private Toolbar mainToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
//        mainToolbar.setTitle("haha");
//        setSupportActionBar(mainToolbar);
//        getActionBar().setDisplayShowTitleEnabled(true);
//
        getSupportActionBar().setTitle("Blog Posts");


//        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//        startActivity(intent);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;

    }
}
