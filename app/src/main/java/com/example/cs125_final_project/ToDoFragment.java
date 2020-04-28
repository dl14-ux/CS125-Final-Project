package com.example.cs125_final_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ToDoFragment extends Fragment {

    public ToDoFragment() {
        //Empty
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_todo, container, false);

        final ArrayList<String> menuItems = new ArrayList<>();
        ListView listView = view.findViewById(R.id.task_list);
        final EditText task = view.findViewById(R.id.editText);

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
                final String taskOnClick = task.getText().toString();
                if (taskOnClick != null && taskOnClick.length() != 0) {
                    menuItems.add(taskOnClick);
                    listViewAdapter.notifyDataSetChanged();
                    task.getText().clear();
                } else {
                    Toast.makeText(getActivity(), "Enter Something!", Toast.LENGTH_SHORT).show();
                }
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
}
