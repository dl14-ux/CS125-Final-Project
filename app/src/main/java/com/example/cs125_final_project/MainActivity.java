package com.example.cs125_final_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ArrayList<String> tasks;
    // This is where the tasks are added
    public ArrayAdapter<String> itemsAdapter;
    private ListView task_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Creates list to add tasks
        task_list = findViewById(R.id.task_list);
        tasks = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, tasks);
        task_list.setAdapter(itemsAdapter);

        // implements remove
        removeTask();
        //implements add
        addTask();
        taskDescription();
    }

    // When held, the item is deleted (maybe change this to a button next to each task?)
    private void removeTask() {
        task_list.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        // Remove task
                        tasks.remove(pos);
                        // Refresh
                        itemsAdapter.notifyDataSetChanged();
                        // It worked
                        return true;
                    }

                });
    }
    //Goes to secondary activity: TaskDescriptionActivity
    private void addTask() {
        FloatingActionButton fab = findViewById(R.id.add_task);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText task = findViewById(R.id.editText);
                final String taskOnClick = task.getText().toString();
                if (taskOnClick != null && taskOnClick.length() != 0) {
                    itemsAdapter.add(taskOnClick);
                    itemsAdapter.notifyDataSetChanged();
                    task.getText().clear();
                }
            }
        });
    }
    private void taskDescription() {
        task_list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        Intent intent = new Intent(getApplicationContext(), TaskDescriptionActivity.class);
                        startActivity(intent);
                    }
                });
    }
}
