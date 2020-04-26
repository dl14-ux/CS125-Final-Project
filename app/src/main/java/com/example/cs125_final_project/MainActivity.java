package com.example.cs125_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
        tasks.add("Add a task!");

        // implements remove
        removeTask();
        //implements add
        addTask();
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
    private void addTask() {
        FloatingActionButton fab = findViewById(R.id.add_task);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent taskDesc = new Intent(getApplicationContext(), TaskDescriptionActivity.class);
                startActivity(taskDesc);
            }
        });
    }
}
