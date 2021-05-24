package ma.uit.emploisclub.Controllers.Activities.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.joda.time.DateTime;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import ma.uit.emploisclub.Controllers.Activities.Fragments.Agenda.RecyclerViewAdapterListeSeance;
import ma.uit.emploisclub.Data.GlobaleData;
import ma.uit.emploisclub.Model.Seance;
import ma.uit.emploisclub.R;

import static android.app.Activity.RESULT_OK;
import static ma.uit.emploisclub.Controllers.MainActivity.getScreenWidth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_profile extends Fragment {

    private static final int RESULT_LOAD_IMG = 123 ;
    TextView idUser ;
    TextView typeCompte ;
    TextInputLayout editNom ;
    TextInputLayout editPrenom ;
    TextInputLayout editAdresse ;
    TextInputLayout editTel ;
    ImageButton btnImage ;
    ImageView imageProfileView ;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_profile.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_profile newInstance(String param1, String param2) {
        fragment_profile fragment = new fragment_profile();
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        imageProfileView = (ImageView) getView().findViewById(R.id.shapeableImageView) ;
        typeCompte = (TextView) getView().findViewById(R.id.typeProfile);
        idUser = (TextView) getView().findViewById(R.id.idProfile);
        editNom= (TextInputLayout) getView().findViewById(R.id.nomProfile);
        editPrenom = (TextInputLayout) getView().findViewById(R.id.prenomProfile);
        editAdresse = (TextInputLayout) getView().findViewById(R.id.AdresseMail);
        editTel = (TextInputLayout) getView().findViewById(R.id.telProfile);
        btnImage = (ImageButton) getView().findViewById(R.id.btnAddImage) ;
        initInformation();
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickAddImage();
            }
        });
    }

    public void initInformation(){
        switch (GlobaleData.user.getRole()){
            case 1 :
                typeCompte.setText("Compte administrateur");
                break ;
            case 2 :
                typeCompte.setText("Compte client");
                break ;
            case 3 :
                typeCompte.setText("Compte coach");
                break;
            default:
                break;
        }

        idUser.setText("Id : "+GlobaleData.user.getId());
        editNom.getEditText().setText(GlobaleData.user.getNom());
        editPrenom.getEditText().setText(GlobaleData.user.getPrenom());
        editAdresse.getEditText().setText(GlobaleData.user.getAdresse());
        editTel.getEditText().setText(GlobaleData.user.getTel());

    }

    public void onClickAddImage(){
        Intent photoPickerIntent = new Intent();
        photoPickerIntent.setType("image/*");
        photoPickerIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(photoPickerIntent ,"Selectionner une image"), RESULT_LOAD_IMG);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK && data != null)  imageProfileView.setImageURI(data.getData());
    }
}