package ma.uit.emploisclub.Data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;

import ma.uit.emploisclub.Loader.myLoader;
import ma.uit.emploisclub.Model.Coach;
import ma.uit.emploisclub.Model.Seance;
import ma.uit.emploisclub.Model.Tache;
import ma.uit.emploisclub.Model.User;
import ma.uit.emploisclub.api.APIClient;
import ma.uit.emploisclub.api.EmploisClubService;

public class GlobaleData  {


    public static EmploisClubService apiInterface = APIClient.getClient().create(EmploisClubService .class);
    public static myLoader newFragment = new myLoader();
    public static ArrayList<Seance> globaleListe = new ArrayList<>() ;
    public static ArrayList<Tache> globaleListeTache = new ArrayList<>();

    public static ArrayList<User> globaleListeUser = new ArrayList<User>();


    public static Tache getTacheById(int id){
        for ( Tache t : globaleListeTache ) {
            if(t.getId() == id )
                return t ;
        }
        return null ;
    }

    public static void SaveVar(String key , String val , Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, val);
        editor.commit();
    }

    public static String GetVar(String key,Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String res = prefs.getString(key,null);
        return res ;
    }

    public static User user = new User("1","Soussi","Walid","Hay el fath rabat ","0651022772" ,"walidsoussidev@gmail","1");


}
