package com.example.cs125_final_project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EmailFragment extends Fragment {

    public EditText mEmail;
    public EditText mSubject;
    public EditText mMessage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_email, container, false);
        mEmail = (EditText)view.findViewById(R.id.mailID);
        mSubject = (EditText)view.findViewById(R.id.subjectID);

        Button fab = view.findViewById(R.id.buttonSend);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();
            }
        });
        return view;
    }
    private void sendMail() {

        StringBuffer sb = new StringBuffer();
        for (String s : ToDoFragment.menuItems) {
            sb.append(s);
            sb.append(", ");
        }

        String mail = mEmail.getText().toString().trim();
        String message =
                sb.toString();
        String subject = mSubject.getText().toString().trim();

        //Send Mail
        JavaMailAPI javaMailAPI = new JavaMailAPI(getActivity(),mail,subject,message);

        javaMailAPI.execute();

    }

}
