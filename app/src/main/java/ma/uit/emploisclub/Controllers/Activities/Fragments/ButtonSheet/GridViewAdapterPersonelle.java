package ma.uit.emploisclub.Controllers.Activities.Fragments.ButtonSheet;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ma.uit.emploisclub.Model.Coach;
import ma.uit.emploisclub.Model.Tache;
import ma.uit.emploisclub.Model.User;
import ma.uit.emploisclub.R;


class GridViewAdapterPersonelle extends BaseAdapter implements Filterable {

    private List<User> listeCoach ;
    private List<User> listeCoachFiltred ;
    private Context context ;

    public GridViewAdapterPersonelle(List<User> listeCoach, Context context) {
        this.listeCoach = listeCoach;
        this.listeCoachFiltred = listeCoach;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listeCoach.size();
    }

    @Override
    public Object getItem(int i) {
        return listeCoach.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View myView = view ;

        if(myView == null) myView = LayoutInflater.from(context).inflate(R.layout.item_grid_view,viewGroup,false);

        ((TextView)myView.findViewById(R.id.txtName)).setText(listeCoach.get(i).getNom() + " " + listeCoach.get(i).getPrenom());

        Log.i("test log ", listeCoach.get(0).getNom()+"");

        return myView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterRes = new FilterResults();
                if(charSequence == null || charSequence.length() == 0){
                    filterRes.count = listeCoachFiltred.size();
                    filterRes.values = listeCoachFiltred;
                }else{
                    String serachChr = charSequence.toString().toLowerCase();
                    List<User> searchRes = new ArrayList<>();

                    for (User c: listeCoachFiltred) {
                        if(c.getNom().toLowerCase().contains(serachChr) || c.getPrenom().toLowerCase().contains(serachChr)){
                            searchRes.add(c);
                        }
                    }

                    filterRes.count = searchRes.size();
                    filterRes.values = searchRes ;
                }
                return filterRes;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    listeCoach = (List<User>) filterResults.values;
                    notifyDataSetChanged();
            }
        };
        return filter;
    }
}
