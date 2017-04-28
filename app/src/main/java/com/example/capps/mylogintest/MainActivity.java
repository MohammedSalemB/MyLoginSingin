package com.example.capps.mylogintest;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.capps.mylogintest.login.LoginFrag;
import com.example.capps.mylogintest.login.SignInFrag;

public class MainActivity extends AppCompatActivity implements LoginFrag.LoginInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        if (savedInstanceState ==null)
            MyUtils.addOrReplaceFragment(getSupportFragmentManager(),new LoginFrag()
                    ,android.R.id.content,false,null);

    }


    @Override
    public void openSignInFrag() {
        MyUtils.addOrReplaceFragment(getSupportFragmentManager(),new SignInFrag()
        ,android.R.id.content,true,null);
    }
}
