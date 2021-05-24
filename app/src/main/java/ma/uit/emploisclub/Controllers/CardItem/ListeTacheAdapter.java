package ma.uit.emploisclub.Controllers.CardItem;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.github.lguipeng.library.animcheckbox.AnimCheckBox;

import java.util.List;

import ma.uit.emploisclub.Controllers.Activities.Fragments.MainFragment;
import ma.uit.emploisclub.Data.GlobaleData;
import ma.uit.emploisclub.Model.Tache;
import ma.uit.emploisclub.R;


public  class ListeTacheAdapter extends PagerAdapter {

    private List<Tache> listeTache ;
    private LayoutInflater layoutInflater ;
    private Context context ;
    public TextView text1 ;
    public TextView text2 ;
    public TextView text3 ;
    public TextView text4 ;

    public AnimCheckBox button1 ;
    public AnimCheckBox button2 ;
    public AnimCheckBox button3 ;
    public AnimCheckBox button4 ;


    public ListeTacheAdapter(List<Tache> listeTache, Context context) {
        this.listeTache = listeTache;
        this.context = context;
    }

    @Override
    public int getCount() {
        return (listeTache.size()/4)+1;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.liste_tache , container , false);

        text1 = (TextView) view.findViewById(R.id.txt1);
        text2 = (TextView) view.findViewById(R.id.txt2);
        text3 = (TextView) view.findViewById(R.id.txt3);
        text4 = (TextView) view.findViewById(R.id.txt4);

        button1 = (AnimCheckBox) view.findViewById(R.id.btn1);
        button4 = (AnimCheckBox) view.findViewById(R.id.btn4);
        button2 = (AnimCheckBox) view.findViewById(R.id.btn2);
        button3 = (AnimCheckBox) view.findViewById(R.id.btn3);


        if(position == 0){

            text1.setText(listeTache.get(position).getNomTache());
            setIfCheked(button1,listeTache.get(position).getId());
            text2.setText(listeTache.get(position + 1).getNomTache());
            setIfCheked(button2,listeTache.get(position + 1).getId());
            text3.setText(listeTache.get(position + 2).getNomTache());
            setIfCheked(button3,listeTache.get(position + 2).getId());
            text4.setText(listeTache.get(position + 3).getNomTache());
            setIfCheked(button4,listeTache.get(position + 3).getId());
            button1.setOnCheckedChangeListener(new AnimCheckBox.OnCheckedChangeListener(){
                @Override
                public void onChange(AnimCheckBox view, boolean checked) {
                    listeTache.get(position).setDone(checked);
                }
            });

            button2.setOnCheckedChangeListener(new AnimCheckBox.OnCheckedChangeListener(){
                @Override
                public void onChange(AnimCheckBox view, boolean checked) {
                    listeTache.get(position + 1).setDone(checked);
                }
            });

            button3.setOnCheckedChangeListener(new AnimCheckBox.OnCheckedChangeListener(){
                @Override
                public void onChange(AnimCheckBox view, boolean checked) {
                    listeTache.get(position + 2).setDone(checked);
                }
            });

            button4.setOnCheckedChangeListener(new AnimCheckBox.OnCheckedChangeListener(){
                @Override
                public void onChange(AnimCheckBox view, boolean checked) {
                   listeTache.get(position + 3).setDone(checked);
                }
            });

        }else{

            try{
                text1.setText(listeTache.get(position + (position*3)).getNomTache());
                setIfCheked(button1,listeTache.get(position + (position*3)).getId());
                button1.setOnCheckedChangeListener(new AnimCheckBox.OnCheckedChangeListener(){
                    @Override
                    public void onChange(AnimCheckBox view, boolean checked) {
                        listeTache.get(position + (position*3)).setDone(checked);
                    }
                });
            }catch(Exception e){
                text1.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
            }

            try{
                text2.setText(listeTache.get(position + 1 + (position*3)).getNomTache());

                setIfCheked(button2,listeTache.get(position + 1 + (position*3)).getId());
                button2.setOnCheckedChangeListener(new AnimCheckBox.OnCheckedChangeListener(){
                    @Override
                    public void onChange(AnimCheckBox view, boolean checked) {
                        listeTache.get(position + 1+(position*3)).setDone(checked);
                    }
                });
            }catch(Exception e){
                text2.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
            }

            try{
                text3.setText(listeTache.get(position + 2 + (position*3)).getNomTache());

                setIfCheked(button3,listeTache.get(position + 2 + (position*3)).getId());
                button3.setOnCheckedChangeListener(new AnimCheckBox.OnCheckedChangeListener(){
                    @Override
                    public void onChange(AnimCheckBox view, boolean checked) {
                        listeTache.get(position + 2+(position*3)).setDone(checked);
                    }
                });
            }catch(Exception e){
                text3.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);
            }

            try{
                text4.setText(listeTache.get(position + 3 + (position*3)).getNomTache());

                setIfCheked(button4,listeTache.get(position + 3 + (position*3)).getId());
                button4.setOnCheckedChangeListener(new AnimCheckBox.OnCheckedChangeListener(){
                    @Override
                    public void onChange(AnimCheckBox view, boolean checked) {
                        listeTache.get(position + 3+(position*3)).setDone(checked);
                    }
                });
            }catch(Exception e){
                text4.setVisibility(View.INVISIBLE);
                button4.setVisibility(View.INVISIBLE);
            }








        }


//

        container.addView(view,0);

        return view;
    }

    public void setIfCheked(AnimCheckBox btn , int id){
        Log.i("id ",""+GlobaleData.getTacheById(id).getNomTache()+" vs "+GlobaleData.getTacheById(id).isDone());
      btn.setChecked(GlobaleData.getTacheById(id).isDone(),true);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View)object);
    }


}
