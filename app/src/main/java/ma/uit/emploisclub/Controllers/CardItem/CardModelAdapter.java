package ma.uit.emploisclub.Controllers.CardItem;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

import ma.uit.emploisclub.R;

public class CardModelAdapter extends PagerAdapter {

    private List<CardModel> listeModel ;
    private LayoutInflater layoutInflater ;
    private Context context ;

    public CardModelAdapter(List<CardModel> listeModel, Context context) {
        this.listeModel = listeModel;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listeModel.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.itemcard , container , false);
//        Button testbtn = (Button)
        container.addView(view,0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
