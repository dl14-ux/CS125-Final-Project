package com.example.cs125_final_project;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static androidx.constraintlayout.widget.Constraints.TAG;


public class ToDoFragment extends Fragment {

    private ArrayList<String> menuItems;

    public ToDoFragment() {
        //Empty
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_todo, container, false);

        loadData();

        Button buttonSave = view.findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                Toast.makeText(getActivity(), "List Saved!", Toast.LENGTH_SHORT).show();
            }
        });
        ListView listView = view.findViewById(R.id.task_list);

        final ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                menuItems
        );
        listView.setAdapter(listViewAdapter);

        FloatingActionButton fab = view.findViewById(R.id.add_task);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder todoTaskBuilder = new AlertDialog.Builder(getActivity());
                todoTaskBuilder.setTitle("Add a Task");
                todoTaskBuilder.setMessage("Describe it");
                final EditText todoET = new EditText(getActivity());
                todoTaskBuilder.setView(todoET);
                todoTaskBuilder.setPositiveButton("Add Task", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String todoTaskInput = todoET.getText().toString();
                        if (todoTaskInput != null || todoTaskInput.length() != 0) {
                            menuItems.add(todoTaskInput);
                            Toast.makeText(getActivity(), "Remember to Save!", Toast.LENGTH_SHORT).show();
                            listViewAdapter.notifyDataSetChanged();
                            saveData();
                        }
                    }
                });

                todoTaskBuilder.setNegativeButton("Cancel", null);
                todoTaskBuilder.create().show();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        // Remove task
                        menuItems.remove(pos);
                        // Refresh
                        listViewAdapter.notifyDataSetChanged();
                        // It worked
                        return true;
                    }
        });


        return view;
    }
    private void saveData() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(
                "shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(menuItems);
        editor.putString("task list", json);
        editor.apply();
    }
    private void loadData() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(
                "shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList>() {}.getType();
        menuItems = gson.fromJson(json, type);

        if (menuItems == null) {
            menuItems = new ArrayList<>();
        }
    }
}
