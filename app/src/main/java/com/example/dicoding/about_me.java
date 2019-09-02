package com.example.dicoding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class about_me extends AppCompatActivity {
    private Toolbar toolbar;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        toolbar = (Toolbar) findViewById(R.id.tl_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        this.setTitle("About me");
    }
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
