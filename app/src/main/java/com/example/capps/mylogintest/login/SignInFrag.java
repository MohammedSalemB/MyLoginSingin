package com.example.capps.mylogintest.login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
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
 * LoaderManager.LoaderCallbacks<Object>
 */

public class SignInFrag extends Fragment implements View.OnClickListener,LoaderManager.LoaderCallbacks<Boolean> {



    private final String DIALOG_FRAG="dialog_frag";
    private MyDialog mDialog;
    private MyUtils myUtils;

    @BindView(R.id.username_edit_text)
    EditText mUserName;
    @BindView(R.id.first_name_edit_text)
    EditText mFirstName;
    @BindView(R.id.last_name_edit_text)
    EditText mLastName;
    @BindView(R.id.mobile_edit_text)
    EditText mMobile;
    @BindView(R.id.email_edit_text)
    EditText mEemail;
    @BindView(R.id.password_edit_text)
    EditText mPassword;
    @BindView(R.id.confirm_password_edit_text)
    EditText mConfirmPassword;

    @BindView(R.id.username_text_input_layout)
    TextInputLayout mUserNameInputLayout;
    @BindView(R.id.first_name_text_input_layout)
    TextInputLayout mFirstNameInputLayout;
    @BindView(R.id.last_name_text_input_layout)
    TextInputLayout mLastNameInputLayout;
    @BindView(R.id.mobile_text_input_layout)
    TextInputLayout mMobileInputLayout;
    @BindView(R.id.email_text_input_layout)
    TextInputLayout mEmailInputLayout;
    @BindView(R.id.password_text_input_layout)
    TextInputLayout mPasswordInputLayout;
    @BindView(R.id.confirm_password_text_input_layout)
    TextInputLayout mConfirmPasswordInputLayout;

    @BindView(R.id.submit_button)
    Button mSubmit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.signin_linear_frag,container,false);

        ButterKnife.bind(this,view);
        myUtils = MyUtils.getInstance(getContext());

        setListners();

//        if (mSubmit == null)
//            mSubmit = (Button) view.findViewById(R.id.submit_button);


        return view;
    }

    private void setListners() {
        mSubmit.setOnClickListener(this);
    }

    private boolean check_value_valid(){

        mUserNameInputLayout.setError(null);
        mFirstNameInputLayout.setError(null);
        mLastNameInputLayout.setError(null);
        mMobileInputLayout.setError(null);
        mEmailInputLayout.setError(null);
        mPasswordInputLayout.setError(null);
        mConfirmPasswordInputLayout.setError(null);



        if(!myUtils.isEmptylValid(mUserNameInputLayout,mUserName,R.string.userName_empty))
            return false;
        if(!myUtils.isEmptylValid(mFirstNameInputLayout,mFirstName,R.string.firstName_empty))
            return false;
        if(!myUtils.isEmptylValid(mLastNameInputLayout,mLastName,R.string.lastName_empty))
            return false;
        if(!myUtils.isEmptylValid(mMobileInputLayout,mMobile,R.string.mobile_empty))
            return false;


        if(!myUtils.isEmailValid(mEmailInputLayout,mEemail))
            return false;
        else if(!myUtils.isPasswordValid(mPasswordInputLayout,mPassword))
            return false;
        else if(!myUtils.isConfirmPasswordValid(mPassword.getText().toString(),mPasswordInputLayout,mPassword))
            return false;
        else
            return true;

    }

    private void attemptSignin(){
        if(check_value_valid()){
            showDialog();
            //network checking
            Bundle arge = new Bundle();
            //put your values in bundle
            // ...
            getActivity().getSupportLoaderManager().restartLoader(8,arge,this).forceLoad();
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


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit_button:
                attemptSignin();
                break;
            default:
                Toast.makeText(getContext(),"Error_string", Toast.LENGTH_SHORT).show();
                break;
        }
    }




    @Override
    public Loader<Boolean> onCreateLoader(int id, Bundle args) {

        return new MyAnysTask(getActivity(),args);
    }

    @Override
    public void onLoadFinished(Loader<Boolean> loader, Boolean data) {

        if(mDialog != null)
            mDialog.dismiss();
        if (data){
            onSignSuccess();
        }
        else
            onSignFaild();
    }

    private void onSignSuccess() {
        mUserName.setText("");
        mFirstName.setText("");
        mLastName.setText("");
        mMobile.setText("");
        mEemail.setText("");
        mPassword.setText("");
        mConfirmPassword.setText("");
        //next step
        Toast.makeText(getActivity(),R.string.success_login,Toast.LENGTH_SHORT).show();
    }

    private void onSignFaild() {
        Toast.makeText(getActivity(), R.string.faild_login,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoaderReset(Loader<Boolean> loader) {

    }


    static class MyAnysTask extends AsyncTaskLoader<Boolean> {

        public MyAnysTask(Context context, Bundle arag) {
            super(context);
        }

        @Override
        public Boolean loadInBackground() {

            try {
                Thread.sleep(3500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        }
    }
}
