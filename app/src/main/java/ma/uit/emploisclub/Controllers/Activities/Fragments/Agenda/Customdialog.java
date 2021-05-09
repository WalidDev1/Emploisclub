package ma.uit.emploisclub.Controllers.Activities.Fragments.Agenda;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableScheduledFuture;

import ma.uit.emploisclub.Controllers.Activities.Fragments.three;
import ma.uit.emploisclub.Model.Seance;
import ma.uit.emploisclub.R;

public class Customdialog extends DialogFragment implements View.OnClickListener {

    public Activity c;
    public Dialog d;
    public ImageButton btndrop;
    public boolean collapsed = false ;
    private String blockCharacterSet = "~#^|$%&*!:,.\"'<>/\\@()+=-_";
    CustomdialogListener listnner ;

    public interface CustomdialogListener{
        void setTache(TacheSend val);
    }

    public static class TacheSend {
        public  String name ;
        public int heur ;
        public int minute ;
        public String description;

        public TacheSend(String name, int heur, int minute, String description) {
            this.name = name;
            this.heur = heur;
            this.minute = minute;
            this.description = description;
        }


    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        // request a window without the title
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_tache, container, false);
        btndrop = (ImageButton) view.findViewById(R.id.image_drop_down);
        EditText heur = (EditText) view.findViewById(R.id.edit_heur);
        EditText name = (EditText) view.findViewById(R.id.name);
        EditText second = (EditText) view.findViewById(R.id.edit_minute);
        heur.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here

                // yourEditText...
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (count != 0 ){
                    if(  Integer.parseInt(s.toString()) < 0 || Integer.parseInt(s.toString()) > 23 ){
                        Toast.makeText(Customdialog.super.getContext(),"Lay hdik safi \uD83D\uDE11",Toast.LENGTH_SHORT).show();
                        heur.getText().clear();
                    }
                    if(s.length() == 2){
                        second.setFocusable(true);
                        second.requestFocus();
                    }
                }
            }
        });
        second.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (i2 != 0 ){
                    if(  Integer.parseInt(charSequence.toString()) < 0 || Integer.parseInt(charSequence.toString()) > 59 ){
                        Toast.makeText(Customdialog.super.getContext(),"Lay hdik safi \uD83D\uDE11",Toast.LENGTH_SHORT).show();
                        second.getText().clear();
                    }
                    if(charSequence.length() == 2){
                        name.setFocusable(true);
                        name.requestFocus();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btndrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewbtnDrop) {
                if (collapsed == false) {
                    ((EditText)  view.findViewById(R.id.textComment)).setVisibility(View.VISIBLE);
                    btndrop.setRotation(180);
                    collapsed=true;
                } else {

                    ((EditText) view.findViewById(R.id.textComment)).setVisibility(View.GONE);
                    collapsed= false;
                    btndrop.setRotation(0);
                }
            }
        });

        ((Button) view.findViewById(R.id.btn_add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getTacheFromLayout() != null){
                    listnner.setTache(getTacheFromLayout());
                    getDialog().dismiss();
                }
            }
        });
        return view ;
    }


    public TacheSend getTacheFromLayout(){
        try{
            int getHeur = Integer.parseInt(((EditText) getView().findViewById(R.id.edit_heur)).getText().toString());
            int getMinute = Integer.parseInt(((EditText) getView().findViewById(R.id.edit_minute)).getText().toString());
            String name = ((EditText) getView().findViewById(R.id.name)).getText().toString();
            String description = ((EditText) getView().findViewById(R.id.textComment)).getText().toString();
            if( !name.equals("") && !description.equals("")){
                return new TacheSend(name , getHeur, getMinute , description );
            }
        }catch (Exception e){
            Toast.makeText(Customdialog.super.getContext(),"Erreur :"+e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        return null ;
    }

    @Override
    public void onAttach(Context context) {
        try{
            listnner = (CustomdialogListener) getTargetFragment();
        }catch (ClassCastException e){
            Log.e("TAG", "onAttach: ClassCastException: " + e.getMessage() );
        }
        super.onAttach(context);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            default:
                break;
        }
        dismiss();
    }



}