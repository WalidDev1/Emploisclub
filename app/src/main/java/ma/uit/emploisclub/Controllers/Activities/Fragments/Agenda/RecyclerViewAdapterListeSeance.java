package ma.uit.emploisclub.Controllers.Activities.Fragments.Agenda;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ma.uit.emploisclub.Model.Seance;
import ma.uit.emploisclub.R;

public class RecyclerViewAdapterListeSeance extends ArrayAdapter<Seance> implements View.OnClickListener{

    private ArrayList<Seance> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtBbr;
        TextView txtDescription;
        TextView txtTime;
    }

    public RecyclerViewAdapterListeSeance(ArrayList<Seance> data, Context context) {
        super(context, R.layout.liste_item_time_header, data);
        this.dataSet = data;
        this.mContext=context;

    }



    @Override
    public void onClick(View v) {

        Log.i("c","Click");
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Seance dataModel=(Seance)object;
//        switch (v.getId())
//        {
//            case R.id.item_info:
//                Snackbar.make(v, "Release date " +dataModel.getFeature(), Snackbar.LENGTH_LONG)
//                        .setAction("No action", null).show();
//                break;
//        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Seance dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;
        Calendar calander = Calendar.getInstance();
        DateFormat outputformat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");




        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.liste_item_time_header, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.textView_countryName);
            viewHolder.txtBbr = (TextView) convertView.findViewById(R.id.textView_population);
            viewHolder.txtTime = (TextView) convertView.findViewById(R.id.text_time);
            convertView.findViewById(R.id.textComment).setVisibility(View.GONE);
            viewHolder.txtDescription = (TextView) convertView.findViewById(R.id.textComment);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

            result=convertView;
        }

        if(dataModel != null){
            if(calander.get(Calendar.MONTH)+1 >= dataModel.getDate_start().getMonthOfYear() && calander.get(Calendar.DAY_OF_MONTH) >= dataModel.getDate_start().getDayOfMonth()){
                // on ajout 8 a l'heur pour avoir le format 24h
                if(calander.get(Calendar.HOUR_OF_DAY) >= dataModel.getDate_start().getHourOfDay() && calander.get(Calendar.MINUTE) >= dataModel.getDate_start().getMinuteOfHour()){

                    ((ConstraintLayout) result.findViewById(R.id.constaintHeader)).setAlpha((float) 0.5);
                    ((LinearLayout) result.findViewById(R.id.layout_des)).setVisibility(View.VISIBLE);


                }
            }
        }


        lastPosition = position;
        viewHolder.txtName.setText(dataModel.getName()+"");
//        viewHolder.txtBbr.setText(dataModel.getMoniteurId()+"");
        viewHolder.txtTime.setText(dataModel.getDate_start().getHourOfDay()+" : "+dataModel.getDate_start().getMinuteOfHour());
        viewHolder.txtDescription.setText(dataModel.getComment()+"");
        // Return the completed view to render on screen
        return convertView;
    }
}