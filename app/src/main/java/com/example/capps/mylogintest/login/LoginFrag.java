package com.example.capps.mylogintest.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.capps.mylogintest.MyDialog;
import com.example.capps.mylogintest.MyUtils;
import com.example.capps.mylogintest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gg on 4/23/2017.
 */

public class LoginFrag extends Fragment implements View.OnClickListener {

    private final String DIALOG_FRAG="dialog_frag";
    private MyDialog mDialog;
    private MyUtils myUtils;

    @BindView(R.id.email_edit_text)
    EditText mEemail;
    @BindView(R.id.password_edit_text)
    EditText mPassword;
    @BindView(R.id.forget_password_text_view)
    TextView mForgetPassword;
    @BindView(R.id.email_text_input_layout)
    TextInputLayout mEmailInputLayout;
    @BindView(R.id.password_text_input_layout)
    TextInputLayout mPasswordInputLayout;
    @BindView(R.id.submit_button)
    Button mSubmit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_linear_frag,container,false);
        ButterKnife.bind(this,view);
        myUtils = MyUtils.getInstance(getContext());

        mSubmit.setOnClickListener(this);
        mForgetPassword.setOnClickListener(this);

//        if (mSubmit == null)
//            mSubmit = (Button) view.findViewById(R.id.submit_button);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }

    private boolean check_value_valid(){

        mEmailInputLayout.setError(null);
        mPasswordInputLayout.setError(null);

        String email = mEemail.getText().toString();
        String password = mPassword.getText().toString();

        if(!myUtils.isEmailValid(email,mEmailInputLayout,mEemail))
            return false;
        else if(!myUtils.isPasswordValid(password,mPasswordInputLayout,mPassword))
            return false;
        else
            return true;

    }

    private void attemptLogin(){
        if(check_value_valid()){
            showDialog();
            //network checking
        }

    }

    private void showDialog(){
        mDialog = MyDialog.newInstance(
                getString(R.string.login_dialog_title),
                getString(R.string.login_dialog_message),
                null);

        mDialog.setCancelable(false);
        mDialog.show(getChildFragmentManager(),DIALOG_FRAG);
    }


//    private boolean isEmailValid(String email){
//        email = email.trim();
//        if(TextUtils.isEmpty(email)){
//            mEmailInputLayout.setError(getResources().getString(R.string.empty_email));
//            mEemail.requestFocus();
//            return false;
//        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
////        }else if(!email.matches("Constant.EMAIL_PATTERENT")){
//            mEmailInputLayout.setError(getResources().getString(R.string.non_valid_email));
//            mEemail.requestFocus();
//            return false;
//        }else
//            return true;
//
//    }
//
//    private boolean isPasswordValid(String password){
//        if(TextUtils.isEmpty(password)){
//            mEmailInputLayout.setError(getResources().getString(R.string.empty_password));
//            mPassword.requestFocus();
//            return false;
//        }else if(password.length() < 8 ){
//            mEmailInputLayout.setError(getResources().getString(R.string.non_valid_password));
//            mPassword.requestFocus();
//            return false;
//        }else
//            return true;
//
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit_button:
                attemptLogin();
                break;
            case R.id.forget_password_text_view:
                showForgetPasswordDialog();
                break;
            default:
                Toast.makeText(getContext(),"Error_string", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void showForgetPasswordDialog() {
        mDialog = MyDialog.newInstance(
                "",
                "",
                R.layout.forget_password_dialog_frag);

//        mDialog.setCancelable(false);
        mDialog.show(getChildFragmentManager(),DIALOG_FRAG);
    }
}
