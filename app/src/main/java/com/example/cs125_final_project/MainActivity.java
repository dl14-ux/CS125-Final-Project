package com.example.cs125_final_project;

import android.content.Intent;
import android.drm.DrmStore;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ArrayList<String> tasks;
    // This is where the tasks are added
    public ArrayAdapter<String> itemsAdapter;
    private ListView task_list;
    private DrawerLayout drawer;
    EditText tru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

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

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
