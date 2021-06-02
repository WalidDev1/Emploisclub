package ma.uit.emploisclub.Model;

import java.util.ArrayList;

public class Coach extends User{

    int id ;
    int nbrClient ;
    ArrayList<Tache> listeTache ;
    ArrayList<Seance> listeSeance ;
    double score ;

    public Coach() {
    }

    public Coach(int id, String nom, String prenom, String adresse, String tel, String mail, int role, int id1, int nbrClient, ArrayList<Tache> listeTache, ArrayList<Seance> listeSeance, double score) {
        super(id, nom, prenom, adresse, tel, mail, role);
        this.id = id1;
        this.nbrClient = nbrClient;
        this.listeTache = listeTache;
        this.listeSeance = listeSeance;
        this.score = score;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getNbrClient() {
        return nbrClient;
    }

    public void setNbrClient(int nbrClient) {
        this.nbrClient = nbrClient;
    }

    public ArrayList<Tache> getListeTache() {
        return listeTache;
    }

    public void setListeTache(ArrayList<Tache> listeTache) {
        this.listeTache = listeTache;
    }

    public ArrayList<Seance> getListeSeance() {
        return listeSeance;
    }

    public void setListeSeance(ArrayList<Seance> listeSeance) {
        this.listeSeance = listeSeance;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public float CaculPrecentTache(){
        int count = 0 ;
        for (Tache t: listeTache ) {
            if(t.isDone) count ++ ;
        }
        return ((count * listeTache.size())/listeTache.size())*100 ;
    }



}
