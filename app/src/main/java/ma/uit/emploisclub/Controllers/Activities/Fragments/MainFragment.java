package ma.uit.emploisclub.Controllers.Activities.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ma.uit.emploisclub.Controllers.CardItem.CardModel;
import ma.uit.emploisclub.Controllers.CardItem.CardModelAdapter;
import ma.uit.emploisclub.R;
import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

ViewPager viewPager ;
    CardModelAdapter adapter ;
List<CardModel> listemodel ;


    public MainFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        // create a ViewInteractor obj needed to interact with the CustomizableCalendar
//        final YourViewInteractorClass calendarViewInteractor = new YourViewInteractorClass();
//
//        // create an AUCalendar object (a Calendar wrapper that operates as a singleton and provides all the updates)
//        AUCalendar auCalendar = AUCalendar.getInstance(calendar);
//
//        // this is needed to receives all Calendar updates (using RxJava 2)
//        subscriptions.add(
//                auCalendar.observeChangesOnCalendar().subscribe(new Consumer<AUCalendar.ChangeSet>() {
//                    @Override
//                    public void accept(AUCalendar.ChangeSet changeSet) throws Exception {
//                        // with ChangeSet you can be aware of which Calendar fields are changed
//                        // you can use changeSet.isFieldChanged(...) passing the name of the field;
//                        // name of the fields can be retrieved using CalendarFields interface;
//                        // AUCalendar is already updated because it's a singleton,
//                        // so for retrieving the updated data you can just use AUCalendar getters
//                    }
//                })
//        );
//
//        // injecting the ViewInteractor to the CustomizableCalendar View
//        customizableCalendar.injectViewInteractor(calendarViewInteractor)
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<EventDay> events = new ArrayList<>();



        // Inflate the layout for this fragment
        listemodel = new ArrayList<>();
        listemodel.add(new CardModel());

        listemodel.add(new CardModel());

        listemodel.add(new CardModel());

        listemodel.add(new CardModel());

        listemodel.add(new CardModel());

        listemodel.add(new CardModel());

        listemodel.add(new CardModel());
        adapter = new CardModelAdapter(listemodel,this.getContext());
        viewPager = (ViewPager) getView().findViewById(R.id.vvp);
        viewPager.setAdapter(adapter);
        CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);

        indicator.createIndicators(listemodel.size(),0);


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position){

                indicator.animatePageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        LocalDate currentdate = LocalDate.now();
        CalendarView calendarView = (CalendarView) getView().findViewById(R.id.calendarView);

//        events.add(new EventDay(calendar, R.drawable.ic_close));
//        events.add(new EventDay(calendar, new Drawable()));
//        events.add(new EventDay(calendar, R.drawable.ic_close, Color.parseColor("#228B22")));
//
//
//        maxcalendar.set(currentdate.getYear(),currentdate.getMonthValue(),1);
        Calendar initcalendar = Calendar.getInstance();
        initcalendar.set(currentdate.getYear(),currentdate.getMonthValue() -1 ,currentdate.getDayOfMonth());
        try {
            calendarView.setDate(initcalendar);
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }
//
    }
}