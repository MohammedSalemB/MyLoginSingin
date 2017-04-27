package com.example.capps.mylogintest;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Gg on 4/23/2017.
 */

public class MyDialog extends DialogFragment {

    //Constant
    final static String TITLE_ = "title";
    final static String MESSAGE_ = "message";
    final static String CUSTOM_DIALOG_ = "custom_dialog";
    private MyDialogListner mHost;
    private MyUtils myUtils;
    private int layoutId;
    public static MyDialog newInstance(String title, String mes, @Nullable Integer customDialog) {
        Bundle args = new Bundle();
        args.putString(TITLE_,title);
        args.putString(MESSAGE_,mes);
        if (customDialog != null)
            args.putInt(CUSTOM_DIALOG_,customDialog);
        MyDialog fragment = new MyDialog();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Fragment parentFragment = getParentFragment();
        if (parentFragment instanceof MyDialogListner)
            mHost = (MyDialogListner) parentFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Fragment parentFragment = getParentFragment();
        if (parentFragment instanceof MyDialogListner)
            mHost = (MyDialogListner) parentFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myUtils = MyUtils.getInstance(getContext());
        layoutId = getArguments().getInt(CUSTOM_DIALOG_,-1);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (layoutId < 0) {
            ProgressDialog dialog = new ProgressDialog(getContext());
//        AlertDialog dialog = new AlertDialog.Builder(getContext()).create();
            dialog.setTitle(getArguments().getString(TITLE_));
            dialog.setMessage(getArguments().getString(MESSAGE_));
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setButton(AlertDialog.BUTTON_NEUTRAL,
                    getString(R.string.login_dialog_cancle_button),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO: 4/23/2017 cancel network login using interface with method done state(completed,canceled),@Nullable data
                            dialog.dismiss();
                        }
                    });

            return dialog;
        }else
            return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (layoutId >= 0) {
            View view = inflater.inflate(layoutId, container, false);
            final EditText email = (EditText) view.findViewById(R.id.email_edit_text);
            final TextInputLayout layoutEmail = (TextInputLayout) view.findViewById(R.id.email_text_input_layout);

            view.findViewById(R.id.submit_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( myUtils.isEmailValid(email.getText().toString(),
                            layoutEmail,
                            email) )
                    dismiss();
                }
            });
            return view;
        }
        else
            return super.onCreateView(inflater, container, savedInstanceState);
    }




    interface MyDialogListner{
        void dialogDone(String email);
    }
}
