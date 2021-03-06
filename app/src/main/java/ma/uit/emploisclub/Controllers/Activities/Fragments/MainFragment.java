package ma.uit.emploisclub.Controllers.Activities.Fragments;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.github.lguipeng.library.animcheckbox.AnimCheckBox;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ma.uit.emploisclub.ConnectionActivity;
import ma.uit.emploisclub.Controllers.Activities.Fragments.ButtonSheet.ButtonSheetGrid;
import ma.uit.emploisclub.Controllers.CardItem.CardModel;
import ma.uit.emploisclub.Controllers.CardItem.CardModelAdapter;
import ma.uit.emploisclub.Controllers.CardItem.ListeTacheAdapter;
import ma.uit.emploisclub.Data.GlobaleData;
import ma.uit.emploisclub.Model.Coach;
import ma.uit.emploisclub.Model.Tache;
import ma.uit.emploisclub.Model.UserListe;
import ma.uit.emploisclub.R;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    ViewPager viewPager ;
    ViewPager viewPagertache ;
    CardModelAdapter adapter ;
    LinearLayoutCompat btnVoirTout ;
    //#1 Defining a BottomSheetBehavior instance

    public static ListeTacheAdapter adapterTache ;
    List<CardModel> listemodel ;
    List<Tache> listetache;

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


        adapterTache = new ListeTacheAdapter(GlobaleData.globaleListeTache,this.getContext());
        viewPagertache = (ViewPager) getView().findViewById(R.id.listeTache);
        viewPagertache.setAdapter(adapterTache);
        CircleIndicator indicatorTache = (CircleIndicator) view.findViewById(R.id.indicatorTache);
        indicatorTache.setViewPager(viewPagertache);



        viewPagertache.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position){

                indicatorTache.animatePageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

        });

        LocalDate currentdate = LocalDate.now();
        CalendarView calendarView = (CalendarView) getView().findViewById(R.id.calendarView);

        Calendar calendarEvent = Calendar.getInstance();
        calendarEvent.set(2021,05,17);
        events.add(new EventDay(calendarEvent, R.drawable.bg_abonnement));
//        events.add(new EventDay(calendarEvent, new Drawable()));
//        events.add(new EventDay(calendarEvent, R.drawable.ic_close, Color.parseColor("#228B22")));
        calendarView.setEvents(events);

        Calendar initcalendar = Calendar.getInstance();
        initcalendar.set(currentdate.getYear(),currentdate.getMonthValue() -1 ,currentdate.getDayOfMonth());
        try {
            calendarView.setDate(initcalendar);
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }

       checkUser(Integer.parseInt(GlobaleData.user.getRole()) ,getView());

    }

    public void checkUser(int idRole , View view){
        view.findViewById(R.id.txtStateAppAdmin).setVisibility(View.GONE);
        view.findViewById(R.id.listeTache).setVisibility(View.GONE);
        view.findViewById(R.id.listeAdminCA).setVisibility(View.GONE);
        view.findViewById(R.id.txtactivity).setVisibility(View.GONE);
        view.findViewById(R.id.vvp).setVisibility(View.GONE);
        view.findViewById(R.id.indicator).setVisibility(View.GONE);
        view.findViewById(R.id.txtCoachState).setVisibility(View.GONE);
        view.findViewById(R.id.layoutTache).setVisibility(View.GONE);
        view.findViewById(R.id.indicatorTache).setVisibility(View.GONE);
        switch(idRole){
            case 1:

                view.findViewById(R.id.txtStateAppAdmin).setVisibility(View.VISIBLE);
                view.findViewById(R.id.listeAdminCA).setVisibility(View.VISIBLE);
                btnVoirTout = (LinearLayoutCompat) view.findViewById(R.id.btn_see_all);
                btnVoirTout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((CoordinatorLayout) view.findViewById(R.id.button_sheet)).setVisibility(View.VISIBLE);

                        ButtonSheetGrid bottomSheetFragment = new ButtonSheetGrid(((CoordinatorLayout) view.findViewById(R.id.button_sheet)));
                        bottomSheetFragment.show(getFragmentManager(), bottomSheetFragment.getTag());
                    }
                });

                Call<UserListe> call = GlobaleData.apiInterface.getAllUser();
                call.enqueue(new Callback<UserListe>() {
                    @Override
                    public void onResponse(Call<UserListe> call, Response<UserListe> response) {
                        UserListe userList = response.body();
                        if(response.isSuccessful()){

                            GlobaleData.globaleListeUser.addAll(userList.data);
                            Log.i("test main" ,GlobaleData.globaleListeUser.toString()+"" );
                            GlobaleData.newFragment.dismiss();
                        }
                        GlobaleData.newFragment.dismiss();
                    }
                    @Override
                    public void onFailure(Call<UserListe> call, Throwable t) {
                        Toast.makeText(getContext(),t.getCause().toString(),Toast.LENGTH_SHORT).show();
                        call.cancel();
                        GlobaleData.newFragment.dismiss();
                    }
                });
                break;
            case 3:

                view.findViewById(R.id.listeTache).setVisibility(View.VISIBLE);
                view.findViewById(R.id.txtCoachState).setVisibility(View.VISIBLE);
                view.findViewById(R.id.layoutTache).setVisibility(View.VISIBLE);
                view.findViewById(R.id.indicatorTache).setVisibility(View.VISIBLE);
                break;
            case 4:
                view.findViewById(R.id.listeTache).setVisibility(View.VISIBLE);
                view.findViewById(R.id.layoutTache).setVisibility(View.VISIBLE);
                view.findViewById(R.id.indicatorTache).setVisibility(View.VISIBLE);
                break;
            default :

                view.findViewById(R.id.txtactivity).setVisibility(View.VISIBLE);
                view.findViewById(R.id.vvp).setVisibility(View.VISIBLE);
                view.findViewById(R.id.indicator).setVisibility(View.VISIBLE);


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

//                indicator.createIndicators(GlobaleData.globaleListeTache.size(),0);

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
        }
    }
}