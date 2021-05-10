package ma.uit.emploisclub.Controllers.Activities.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.MotionEventCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
import ma.uit.emploisclub.Controllers.Activities.Fragments.Agenda.Customdialog;
import ma.uit.emploisclub.Controllers.Activities.Fragments.Agenda.RecyclerViewAdapterListeSeance;
import ma.uit.emploisclub.Model.Seance;
import ma.uit.emploisclub.R;
import ma.uit.emploisclub.api.EmploisClubCalls;

import static ma.uit.emploisclub.Controllers.MainActivity.getScreenWidth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link three#newInstance} factory method to
 * create an instance of this fragment.
 */
public class three extends Fragment implements Customdialog.CustomdialogListener ,  EmploisClubCalls.Callbacks{
    private int first_position = -1;
    private View first_view;
    private static RecyclerViewAdapterListeSeance adapter;
    private final String[] array = {"Hello", "World", "Android", "is", "Awesome", "World", "Android", "is", "Awesome", "World", "Android", "is", "Awesome", "World", "Android", "is", "Awesome"};
    SwipeMenuListView listView;
    ArrayList<Seance> dataModels; // liste Globale
    ArrayList<Seance> dataModelsSelectedDay; // liste Filtrer delon le jour selectionner
    HorizontalCalendar horizontalCalendar ;
    SwipeMenuCreator creator = new SwipeMenuCreator() {

        @Override
        public void create(SwipeMenu menu) {
            Context contextapp = getContext();
            // create "open" item


            // create "delete" item
            SwipeMenuItem spaceitem = new SwipeMenuItem(
                    contextapp);
            // set item background
            spaceitem.setBackground(R.color.white);
            // set item width
            spaceitem.setWidth((int) (getScreenWidth(contextapp) * 0.05));
            // add to menu
            menu.addMenuItem(spaceitem);

            // create "delete" item
            SwipeMenuItem deleteItem = new SwipeMenuItem(
                    contextapp);
            // set item background
            deleteItem.setBackground(R.drawable.bg_row_delete);
            // set item width
            deleteItem.setWidth((int) (getScreenWidth(contextapp) * 0.20));
            // set a icon
            deleteItem.setIcon(R.drawable.ic_icone_delete);
            // add to menu
            menu.addMenuItem(deleteItem);
        }
    };
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public three() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment three.
     */
    // TODO: Rename and change types and number of parameters
    public static three newInstance(String param1, String param2) {
        three fragment = new three();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_three, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LocalDate currentdate = LocalDate.now();
        CalendarView calendarView = (CalendarView) getView().findViewById(R.id.calendarView);
        Calendar initcalendar = Calendar.getInstance();
        initcalendar.set(currentdate.getYear(),currentdate.getMonthValue() -1 ,currentdate.getDayOfMonth());
        try {
            calendarView.setDate(initcalendar);
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }

        LinearLayout liste_element = (LinearLayout) getView().findViewById(R.id.liste_date);
        //
        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, 0);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);



//        datePickerTimeline.setInitialDate(2021,05,20);
        ViewGroup mainConstraintLayout = (ViewGroup) getView().findViewById(R.id.MainConstrainte);
//        Animation animation1 = AnimationUtils.loadAnimation(this.getContext(),R.anim.calendar_out);
        listView = (SwipeMenuListView) getView().findViewById(R.id.list);

        dataModels = new ArrayList<>();
        dataModelsSelectedDay = new ArrayList<>();
        dataModels.add(new Seance(1, 2,"test", new DateTime(2021,4,9,10,11), (float) 34.90,false, false, ""));

        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                boolean visible = false;
                mainConstraintLayout.setBackgroundColor(getResources().getColor(R.color.horseColor));
                TransitionManager.beginDelayedTransition(mainConstraintLayout);
                calendarView.setVisibility(View.GONE);
                liste_element.setVisibility(View.VISIBLE);
                Calendar clickedDayCalendar = eventDay.getCalendar();

                if(horizontalCalendar == null){
                    horizontalCalendar = new HorizontalCalendar.Builder(getActivity(), R.id.datePickerTimeline)
                            .range(startDate, endDate)
                            .configure()
                            .formatTopText("MM")
                            .end()
                            .datesNumberOnScreen(5)
                            .defaultSelectedDate(clickedDayCalendar)
                            .build();
                    horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
                        @Override
                        public void onDateSelected(Calendar date, int position) {

                            getTacheAt(date);
                        }
                    });
                    EmploisClubCalls.fetchUserFollowing(three.this);
                }


                horizontalCalendar.getCalendarView().setVisibility(View.VISIBLE);
                horizontalCalendar.selectDate(clickedDayCalendar, true);
                horizontalCalendar.refresh();
//                datePickerTimeline.setInitialDate(clickedDayCalendar.get(Calendar.YEAR),clickedDayCalendar.get(Calendar.MONTH),clickedDayCalendar.get(Calendar.DAY_OF_MONTH));

            }
        });


        liste_element.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int action = MotionEventCompat.getActionMasked(event);
                switch(action) {
                    case (MotionEvent.ACTION_MOVE) :
                        mainConstraintLayout.setBackgroundColor(getResources().getColor(R.color.white));
                        TransitionManager.beginDelayedTransition(mainConstraintLayout);
                        calendarView.setVisibility(View.VISIBLE);
                        liste_element.setVisibility(View.GONE);
                        horizontalCalendar.getCalendarView().setVisibility(View.GONE);
                return true;
                    default :
                        return true;
                }
            }
        });

        // seconde phase calandrar

       adapter = new RecyclerViewAdapterListeSeance(dataModelsSelectedDay, this.getContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (first_position != -1 && first_position != position) {
                    Seance dataModel = dataModels.get(first_position);
                    first_view.findViewById(R.id.textComment).setVisibility(View.GONE);
                    dataModel.setCollapsed(false);
                    first_view.findViewById(R.id.image_drop_down).setRotation(0);
                }
                Seance dataModel = dataModels.get(position);
                if (dataModel.isCollapsed() == false) {
                    first_position = position;
                    first_view = view;
                    view.findViewById(R.id.textComment).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.image_drop_down).setRotation(180);
                    dataModel.setCollapsed(true);
                } else {

                    view.findViewById(R.id.textComment).setVisibility(View.GONE);
                    dataModel.setCollapsed(false);
                    view.findViewById(R.id.image_drop_down).setRotation(0);
                }
            }
        });
        listView.setMenuCreator(creator);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                dataModels.remove(dataModelsSelectedDay.get(position));
                getTacheAt(horizontalCalendar.getSelectedDate());
                return false;
            }

            // erreur lors de la suppression rah ma khedamache lya la position hit endi deux liste meni tan supprimer liste 1 fune position l element na pas la meme position fla deusieme liste ils faut presendre selon les id

        });

        // ADD tache
        Button btnaddTache = (Button) getView().findViewById(R.id.add_tache);
        btnaddTache.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Customdialog cdd =new Customdialog();
                cdd.show(getFragmentManager(),"");
                cdd.setTargetFragment(three.this, 1);
            }
        });
    }

    public ArrayList<Seance> getTacheAt(Calendar val){
        dataModelsSelectedDay.clear();
        for (Seance s: dataModels) {
            if(s.getDate_start().getDayOfMonth() == val.get(Calendar.DAY_OF_MONTH) && s.getDate_start().getMonthOfYear() == val.get(Calendar.MONTH)){
                dataModelsSelectedDay.add(s);
            }
        }
        Collections.sort(dataModelsSelectedDay);
        adapter.notifyDataSetChanged();
        listView.post(new Runnable() {
            @Override
            public void run() {
                listView.setSelection(dataModelsSelectedDay.size()-1);
            }
        });
        return null ;
    }

    public void setTache(Customdialog.TacheSend val) {
        Log.i("i",""+horizontalCalendar.getSelectedDate().get(Calendar.YEAR)+horizontalCalendar.getSelectedDate().get(Calendar.MONTH)+horizontalCalendar.getSelectedDate().get(Calendar.DAY_OF_MONTH));
        dataModels.add(new Seance(145, 2342,val.name, new DateTime(horizontalCalendar.getSelectedDate().get(Calendar.YEAR),horizontalCalendar.getSelectedDate().get(Calendar.MONTH),horizontalCalendar.getSelectedDate().get(Calendar.DAY_OF_MONTH),val.heur,val.minute), (float) 34.90, true,false, val.description));
        getTacheAt(horizontalCalendar.getSelectedDate());
    }


    @Override
    public void onResponse(@Nullable ArrayList<Seance> Seances) {
        for (Seance s: Seances) {
            Log.i("i",""+s.getName());
        }
    }

    @Override
    public void onFailure() {
        Log.i("i","error");
    }
}