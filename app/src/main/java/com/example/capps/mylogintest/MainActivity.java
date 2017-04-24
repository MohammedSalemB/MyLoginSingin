package com.example.capps.mylogintest;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.capps.mylogintest.login.LoginFrag;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        if (savedInstanceState ==null)
            setFragment(new LoginFrag());

    }


    private void setFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content,fragment)
                .commit();
    }
}
