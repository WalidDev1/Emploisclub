package ma.uit.emploisclub.api;

import android.util.Log;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import ma.uit.emploisclub.Model.Seance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmploisClubCalls {

    public class dataReceived {
        @SerializedName("data")
        @Expose
        List<Seance> listeSeance ;

        public List<Seance> getListeSeance() {
            return listeSeance;
        }
    }
    // 1 - Creating a callback
    public interface Callbacks {
        void onResponse(@Nullable dataReceived Seance);
        void onFailure();
    }

    // 2 - Public method to start fetching users following by Jake Wharton
    public static void fetchUserFollowing(Callbacks callbacks){

        // 2.1 - Create a weak reference to callback (avoid memory leaks)
        final WeakReference<Callbacks> callbacksWeakReference = new WeakReference<Callbacks>(callbacks);

        // 2.2 - Get a Retrofit instance and the related endpoints
        EmploisClubService emploisClubService = EmploisClubService.retrofit.create(EmploisClubService.class);

        // 2.3 - Create the call on Github API
        Call<dataReceived> call = emploisClubService.getAllTache();
        // 2.4 - Start the call
        call.enqueue(new Callback<dataReceived>() {



            @Override
            public void onResponse(Call<dataReceived> call, Response<dataReceived> response) {

                // 2.5 - Call the proper callback used in controller (MainFragment)
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onResponse(response.body());

            }

            @Override
            public void onFailure(Call<dataReceived> call, Throwable t) {
                // 2.5 - Call the proper callback used in controller (MainFragment)
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onFailure();
                Log.i("E", ""+call.toString()+"  "+t.getMessage());
            }
        });
    }
}
