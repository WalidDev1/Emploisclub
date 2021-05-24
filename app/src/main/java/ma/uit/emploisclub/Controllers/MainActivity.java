package ma.uit.emploisclub.Controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import ma.uit.emploisclub.Controllers.Activities.Fragments.MainFragment;
import ma.uit.emploisclub.Controllers.Activities.Fragments.fragment_profile;
import ma.uit.emploisclub.Controllers.Activities.Fragments.AgendaFragment;
import ma.uit.emploisclub.Data.GlobaleData;
import ma.uit.emploisclub.Model.Seance;
import ma.uit.emploisclub.Model.Tache;
import ma.uit.emploisclub.R;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Seance> listeAllSeances ;
        //1 - FOR DESIGN

    BottomNavigationView bottomNavigation;
    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.action_android:
                            openFragment(MainFragment.newInstance("", ""));
                            return true;
                        case R.id.action_logo:
                            openFragment(AgendaFragment.newInstance("", ""));
                            return true;
                        case R.id.action_landscape:
                            openFragment(fragment_profile.newInstance("", ""));
                            return true;
                    }
                    return false;
                }
            };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation = (BottomNavigationView) findViewById(R.id.activity_main_bottom_navigation);
        this.configureBottomView();
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(MainFragment.newInstance("", ""));
        listeAllSeances = new ArrayList<>();
        GlobaleData.globaleListeTache.add(new Tache(0,"Tache 1"));
        GlobaleData.globaleListeTache.add(new Tache(1,"Tache 2"));
        GlobaleData.globaleListeTache.add(new Tache(2,"Tache 3"));
        GlobaleData.globaleListeTache.add(new Tache(3,"Tache 4"));
        GlobaleData.globaleListeTache.add(new Tache(4,"Tache 5"));
        GlobaleData.globaleListeTache.add(new Tache(5,"Tache 6"));
        GlobaleData.globaleListeTache.add(new Tache(6,"Tache 7"));
        GlobaleData.globaleListeTache.add(new Tache(7,"Tache 8"));
        GlobaleData.globaleListeTache.add(new Tache(8,"Tache 9"));
        GlobaleData.globaleListeTache.add(new Tache(9,"Tache 10"));
        GlobaleData.globaleListeTache.add(new Tache(10,"Tache 11"));

        for (Tache t :GlobaleData.globaleListeTache) Log.i("id test",""+t.getId());
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }



    private void configureBottomView() {
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

}


