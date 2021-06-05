package ma.uit.emploisclub;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;

import ma.uit.emploisclub.Controllers.MainActivity;
import ma.uit.emploisclub.Data.GlobaleData;
import ma.uit.emploisclub.Loader.myLoader;
import ma.uit.emploisclub.Model.User;
import ma.uit.emploisclub.Model.UserListe;
import ma.uit.emploisclub.api.APIClient;
import ma.uit.emploisclub.api.EmploisClubCalls;
import ma.uit.emploisclub.api.EmploisClubService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConnectionActivity extends AppCompatActivity {

    EmploisClubService apiInterface;
    Button button;
    myLoader newFragment = new myLoader();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        button = (Button) findViewById(R.id.btnConnection);

        String newlog = ((TextInputEditText) findViewById(R.id.txtLogin)).getText().toString();
        String pass  = ((TextInputEditText) findViewById(R.id.txtPassword)).getText().toString();

        SwitchMaterial switchbtn = (SwitchMaterial) findViewById(R.id.setConnected);
        apiInterface = APIClient.getClient().create(EmploisClubService.class);
        new GlobaleData();
        Log.i("test Share get 1" ,GlobaleData.GetVar("RemberMe",getApplicationContext()) + "" );

//        if(GlobaleData.GetVar("RemberMe",getApplicationContext()) != null && Boolean.parseBoolean(GlobaleData.GetVar("RemberMe",getApplicationContext())) == true){
//            CheckUser(GlobaleData.GetVar("log",getApplicationContext()) ,GlobaleData.GetVar("pass",getApplicationContext()) );
//        }

        Log.i("test Share get 2" ,GlobaleData.GetVar("RemberMe",getApplicationContext()) + "" );

        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(newlog != "" && pass != ""){

                    Log.i("test Share get",newlog +" and " + pass);
//                            CheckUser(log,pass);
//                    GlobaleData.SaveVar("log",log,getApplicationContext());
//                    GlobaleData.SaveVar("pass",pass,getApplicationContext());
                }
            }
        });
    }

    public void CheckUser(String log , String password){
        newFragment.show(getSupportFragmentManager(), "missiles");

        Intent intent= new Intent(ConnectionActivity.this , MainActivity.class);
        Call<UserListe> call = apiInterface.CheckUser(log,password);
        call.enqueue(new Callback<UserListe>() {
            @Override
            public void onResponse(Call<UserListe> call, Response<UserListe> response) {
                UserListe userList = response.body();
                GlobaleData.user = userList.data.get(0);
                newFragment.dismiss();
                ConnectionActivity.this.startActivity(intent);
            }
            @Override
            public void onFailure(Call<UserListe> call, Throwable t) {
                Log.i("E",t.getCause().toString());
                call.cancel();
                newFragment.dismiss();
            }
        });

    }


}