package ma.uit.emploisclub.api;

import java.util.ArrayList;
import java.util.List;

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
//    @GET("GetTache.php")
//    Call<EmploisClubCalls.dataReceived> getAllTache();

    @FormUrlEncoded
    @POST("CheckUser.php")
    Call<UserListe> CheckUser(@Field("username") String log, @Field("password") String  password );

}
