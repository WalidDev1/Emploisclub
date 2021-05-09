package ma.uit.emploisclub.Model;

import org.joda.time.DateTime;

import java.util.Date;

public class Seance implements Comparable<Seance>{
    private int id ;
    private int moniteurId ;
    private String name;
    private DateTime date_start;
    private float duree;
    private boolean is_done = false ;

    public Seance(int id, int moniteurId, String name, DateTime date_start, float duree, boolean is_done, boolean collapsed, String comment) {
        this.id = id;
        this.moniteurId = moniteurId;
        this.name = name;
        this.date_start = date_start;
        this.duree = duree;
        this.is_done = is_done;
        this.collapsed = collapsed;
        this.comment = comment;
    }

    private boolean collapsed = false ;
    private String comment ;

    public Seance() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCollapsed() {
        return collapsed;
    }

    public void setCollapsed(boolean collapsed) {
        this.collapsed = collapsed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMoniteurId() {
        return moniteurId;
    }

    public void setMoniteurId(int moniteurId) {
        this.moniteurId = moniteurId;
    }

    public DateTime getDate_start() {
        return date_start;
    }

    public void setDate_start(DateTime date_start) {
        this.date_start = date_start;
    }

    public float getDuree() {
        return duree;
    }

    public void setDuree(float duree) {
        this.duree = duree;
    }

    public boolean isIs_done() {
        return is_done;
    }

    public void setIs_done(boolean is_done) {
        this.is_done = is_done;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int compareTo(Seance seance) {
        return getDate_start().compareTo(seance.getDate_start());
    }
}
