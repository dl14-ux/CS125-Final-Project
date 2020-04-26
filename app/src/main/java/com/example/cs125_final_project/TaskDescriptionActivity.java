package com.example.cs125_final_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TaskDescriptionActivity extends MainActivity {
    Button send_task;
    //UI
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_desc);
        back();
    }
    //Return to MainActivity when button pressed
    public void back() {
        send_task = findViewById(R.id.task_add_button);
        send_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

