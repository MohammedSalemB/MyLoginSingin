package com.example.capps.mylogintest;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

/**
 * Created by Gg on 4/23/2017.
 */

public class MyUtils {

    private static MyUtils instance;
    private Context context;

    private MyUtils(Context context) {
        this.context = context;
    }

    public static MyUtils getInstance(Context context) {
        if (instance == null)
            instance = new MyUtils(context);
        return instance;
    }


    public boolean isEmptylValid(TextInputLayout layoutINput, EditText editText,String error ){
        String text = editText.getText().toString().trim();
        if(TextUtils.isEmpty(text)){
            layoutINput.setError(error);
            editText.requestFocus();
            return false;
        }else
            return true;
    }

    public boolean isEmptylValid(TextInputLayout layoutINput, EditText editText, @StringRes int error){
        String text = editText.getText().toString().trim();
        if(TextUtils.isEmpty(text)){
            layoutINput.setError(context.getString(error));
            editText.requestFocus();
            return false;
        }else
            return true;
    }

    public boolean isEmailValid(String email, TextInputLayout mEmailInputLayout, EditText mEemail ){
        email = email.trim();
        if(TextUtils.isEmpty(email)){
            mEmailInputLayout.setError(context.getResources().getString(R.string.empty_email));
            mEemail.requestFocus();
            return false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//        }else if(!email.matches("Constant.EMAIL_PATTERENT")){
            mEmailInputLayout.setError(context.getResources().getString(R.string.non_valid_email));
            mEemail.requestFocus();
            return false;
        }else
            return true;

    }

    public  boolean isPasswordValid(String password, TextInputLayout mPasswordInputLayout, EditText mPassword){
        if(TextUtils.isEmpty(password)){
            mPasswordInputLayout.setError(context.getResources().getString(R.string.empty_password));
            mPassword.requestFocus();
            return false;
        }else if(password.length() < 8 ){
            mPasswordInputLayout.setError(context.getResources().getString(R.string.non_valid_password));
            mPassword.requestFocus();
            return false;
        }else
            return true;

    }

}
