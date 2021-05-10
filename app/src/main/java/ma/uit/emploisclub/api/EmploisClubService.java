package ma.uit.emploisclub.api;

import java.util.ArrayList;
import java.util.List;

import ma.uit.emploisclub.Model.Seance;
import ma.uit.emploisclub.R;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface EmploisClubService {
    @GET(R.string.GetAllTache+"")
    Call<ArrayList<Seance>> getAllTache();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://emploisclub.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
