package ma.uit.emploisclub.Controllers.Activities.Fragments.ButtonSheet;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ma.uit.emploisclub.Data.GlobaleData;
import ma.uit.emploisclub.Model.Coach;
import ma.uit.emploisclub.Model.Seance;
import ma.uit.emploisclub.Model.Tache;
import ma.uit.emploisclub.R;

public class ButtonSheetGrid extends BottomSheetDialogFragment {

    GridView listGridView ;
    GridViewAdapterPersonelle gridViewAdapter ;
    CoordinatorLayout coorlayout;
    List<Coach> listeCoachFilter ;
    public ButtonSheetGrid(CoordinatorLayout coorlayout) {
        // Required empty public constructor
        this.coorlayout = coorlayout ;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listeCoachFilter = new GlobaleData().globaleListeCoach ;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listGridView = view.findViewById(R.id.gridliste);
        gridViewAdapter = new GridViewAdapterPersonelle(listeCoachFilter,getContext());
        listGridView.setAdapter(gridViewAdapter);
        ((EditText)view.findViewById(R.id.editSearch)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                gridViewAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.button_sheet_all_user, container, false);

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        coorlayout.setVisibility(View.GONE);
    }
}
