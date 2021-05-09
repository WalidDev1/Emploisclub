package ma.uit.emploisclub.Controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ma.uit.emploisclub.Controllers.Activities.Fragments.MainFragment;
import ma.uit.emploisclub.Controllers.Activities.Fragments.fragment_profile;
import ma.uit.emploisclub.Controllers.Activities.Fragments.three;
import ma.uit.emploisclub.R;

import static ma.uit.emploisclub.R.id.MyCustomToolBar;

public class MainActivity extends AppCompatActivity {

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
                            openFragment(three.newInstance("", ""));
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


