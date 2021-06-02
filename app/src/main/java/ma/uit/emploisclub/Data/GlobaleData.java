package ma.uit.emploisclub.Data;

import java.util.ArrayList;

import ma.uit.emploisclub.Model.Coach;
import ma.uit.emploisclub.Model.Seance;
import ma.uit.emploisclub.Model.Tache;
import ma.uit.emploisclub.Model.User;

public class GlobaleData  {

    public static ArrayList<Seance> globaleListe = new ArrayList<>() ;
    public static ArrayList<Tache> globaleListeTache = new ArrayList<>();
    public static ArrayList<Coach> globaleListeCoach = new ArrayList<Coach>(){{
        add(new Coach(1,"soussi","walid","hay el fath","06572739","walidsoussidev@gmail.com",3,1,5,null,null,4.5));
        add(new Coach(1,"Rami","basma","hay el fath","06572739","walidsoussidev@gmail.com",3,1,5,null,null,4.5));
        add(new Coach(1,"Essamit","Yassir","hay el fath","06572739","walidsoussidev@gmail.com",3,1,5,null,null,4.5));
        add(new Coach(1,"Ramdani","Chaimae","hay el fath","06572739","walidsoussidev@gmail.com",3,1,5,null,null,4.5));
        add(new Coach(1,"Bourkia","Simo","hay el fath","06572739","walidsoussidev@gmail.com",3,1,5,null,null,4.5));
    }};
    public static Tache getTacheById(int id){
        for ( Tache t : globaleListeTache ) {
            if(t.getId() == id )
                return t ;
        }
        return null ;
    }

    public static User user = new User(1,"Soussi","Walid","Hay el fath rabat tbon mok","0651022772" ,"walidsoussidev@gmail",4);


}
