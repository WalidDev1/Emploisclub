package ma.uit.emploisclub.Data;

import java.util.ArrayList;

import ma.uit.emploisclub.Model.Seance;
import ma.uit.emploisclub.Model.Tache;
import ma.uit.emploisclub.Model.User;

public class GlobaleData  {

    public static ArrayList<Seance> globaleListe = new ArrayList<>() ;
    public static ArrayList<Tache> globaleListeTache = new ArrayList<>();

    public static Tache getTacheById(int id){
        for ( Tache t : globaleListeTache ) {
            if(t.getId() == id )
                return t ;
        }
        return null ;
    }

    public static User user = new User(1,"Soussi","Walid","Hay el fath rabat tbon mok","0651022772" ,"walidsoussidev@gmail",3);


}
