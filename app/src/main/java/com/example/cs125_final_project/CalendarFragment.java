package com.example.cs125_final_project;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.applikeysolutions.cosmocalendar.selection.RangeSelectionManager;
import com.applikeysolutions.cosmocalendar.settings.lists.connected_days.ConnectedDays;
import com.applikeysolutions.cosmocalendar.utils.SelectionType;
import com.applikeysolutions.cosmocalendar.view.CalendarView;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class CalendarFragment extends Fragment {

    CalendarView calendarView;
    Button button;
    String startDate, endDate;
    String useless;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        //Code goes here

        calendarView = view.findViewById(R.id.cosmo_calendar);
        button = view.findViewById(R.id.selectButton);

        //Set Orientation 0 = Horizontal | 1 = Vertical
        calendarView.setCalendarOrientation(0);

        calendarView.setWeekendDays(new HashSet(){{
            add(Calendar.SATURDAY);
            add(Calendar.SUNDAY);
        }});


        //Set First day of the week
        calendarView.setFirstDayOfWeek(Calendar.SUNDAY);

        //Range Selection
        calendarView.setSelectionType(SelectionType.SINGLE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calendarView.getSelectionManager() instanceof RangeSelectionManager) {
                    RangeSelectionManager rangeSelectionManager = (RangeSelectionManager) calendarView.getSelectionManager();
                    if(rangeSelectionManager.getDays() != null) {
                        startDate = rangeSelectionManager.getDays().first.toString();
                        endDate = rangeSelectionManager.getDays().second.toString();
                    } else {
                        Toast.makeText(getActivity(), "Invalid Selection", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "sup", Toast.LENGTH_LONG).show();
            }
        });


        //Setting task dates
        Calendar calendar = Calendar.getInstance();
        Set<Long> days = new TreeSet<>();
        days.add(calendar.getTimeInMillis());

        //Define colors
        int textColor = Color.parseColor("#ff0000");
        int selectedTextColor = Color.parseColor("#ff4000");
        int disabledTextColor = Color.parseColor("#ff8000");
        ConnectedDays connectedDays = new ConnectedDays(days, textColor, selectedTextColor, disabledTextColor);

        //Connect days to calendar
        calendarView.addConnectedDays(connectedDays);



        return view;
    }
}