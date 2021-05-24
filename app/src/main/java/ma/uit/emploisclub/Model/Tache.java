package ma.uit.emploisclub.Model;

public class Tache {

    int id ;
    String nomTache ;
    boolean isDone = false ;


    public Tache(int id, String nomTache) {
        this.id = id ;
        this.nomTache = nomTache;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomTache() {
        return nomTache;
    }

    public void setNomTache(String nomTache) {
        this.nomTache = nomTache;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

}
