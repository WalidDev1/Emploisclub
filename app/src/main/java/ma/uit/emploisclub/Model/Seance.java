package ma.uit.emploisclub.Model;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Seance implements Comparable<Seance>{
    @SerializedName("id")
    @Expose
    private int id ;
//
//    private int moniteurId = 0 ;

    @SerializedName("description")
    @Expose
    private String comment ;

    @SerializedName("tacheType")
    @Expose
    int typeTache ;

    @SerializedName("isFinised")
    @Expose
    int isFinised ;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("dateStart")
    @Expose
    private String date_start;

    private boolean collapsed = false ;

    public Seance(int id, String name,int typeTache ,Boolean collapsed , int isFinised, String date_start,  String comment) {
        this.id = id;
//        this.moniteurId = moniteurId;
        this.name = name;
        this.date_start = date_start;
        this.isFinised = isFinised;
        this.typeTache = typeTache;
        this.collapsed = collapsed;
        this.comment = comment;
    }




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

//    public int getMoniteurId() {
//        return moniteurId;
//    }
//
//    public void setMoniteurId(int moniteurId) {
//        this.moniteurId = moniteurId;
//    }

    public DateTime getDate_start()  {
        try{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return new DateTime(dateFormat.parse(date_start)) ;
        }catch(Exception e){
            Log.i("E",""+e.getMessage());
        }
        return null ;

    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

//

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int compareTo(Seance seance) {
        try {
            return getDate_start().compareTo(seance.getDate_start());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0 ;
    }
}
