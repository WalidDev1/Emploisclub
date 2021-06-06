package ma.uit.emploisclub.api;

import java.util.ArrayList;
import java.util.List;

import ma.uit.emploisclub.Model.ListeTache;
import ma.uit.emploisclub.Model.ResponseStatusAPI;
import ma.uit.emploisclub.Model.Seance;
import ma.uit.emploisclub.Model.User;
import ma.uit.emploisclub.Model.UserListe;
import ma.uit.emploisclub.R;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EmploisClubService {


    @GET("GetTache.php")
    Call<ListeTache> getAllTache();

    @FormUrlEncoded
    @POST("AddTache.php")
    Call<ResponseStatusAPI> AddTache(@Field("id") String id, @Field("tacheType") String  tachetype , @Field("name") String  name );

    @FormUrlEncoded
    @POST("RemoveTache.php")
    Call<ResponseStatusAPI> DeleteTache(@Field("id") String id, @Field("idTache") String  tachetype );


    @FormUrlEncoded
    @POST("CheckUser.php")
    Call<UserListe> CheckUser(@Field("username") String log, @Field("password") String  password );


}
