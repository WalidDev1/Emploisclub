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
import android.widget.Toast;

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


    Button button;
    String newlog ,pass ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        button = (Button) findViewById(R.id.btnConnection);

//        newlog = ((TextInputEditText) findViewById(R.id.txtLogin)).getText().toString();
//        pass  = ((TextInputEditText) findViewById(R.id.txtPassword)).getText().toString();


        new GlobaleData();
        SwitchMaterial switchbtn = (SwitchMaterial) findViewById(R.id.setConnected);
        if(GlobaleData.GetVar("RemberMe",getApplicationContext()) != null && Boolean.parseBoolean(GlobaleData.GetVar("RemberMe",getApplicationContext())) == true){
            CheckUser(GlobaleData.GetVar("log",getApplicationContext()) ,GlobaleData.GetVar("pass",getApplicationContext()) );
        }

        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(((TextInputEditText) findViewById(R.id.txtLogin)).getText().toString() != "" && ((TextInputEditText) findViewById(R.id.txtPassword)).getText().toString() != ""){

                    GlobaleData.SaveVar("RemberMe",switchbtn.isChecked()+"",getApplicationContext());
                    CheckUser(((TextInputEditText) findViewById(R.id.txtLogin)).getText().toString(),((TextInputEditText) findViewById(R.id.txtPassword)).getText().toString());
                    GlobaleData.SaveVar("log",((TextInputEditText) findViewById(R.id.txtLogin)).getText().toString(),getApplicationContext());
                    GlobaleData.SaveVar("pass",((TextInputEditText) findViewById(R.id.txtPassword)).getText().toString(),getApplicationContext());
                }
            }
        });
    }

    public void CheckUser(String log , String password){
        GlobaleData.newFragment.show(getSupportFragmentManager(), "missiles");

        Intent intent= new Intent(ConnectionActivity.this , MainActivity.class);
        Call<UserListe> call = GlobaleData.apiInterface.CheckUser(log,password);
        call.enqueue(new Callback<UserListe>() {
            @Override
            public void onResponse(Call<UserListe> call, Response<UserListe> response) {
                UserListe userList = response.body();
                if(response.isSuccessful()){
                    GlobaleData.user = userList.data.get(0);
                    GlobaleData.newFragment.dismiss();

                    Toast.makeText(getApplicationContext(),"Vous etes connecter !",Toast.LENGTH_SHORT).show();
                    ConnectionActivity.this.startActivity(intent);
                }
                GlobaleData.newFragment.dismiss();
            }
            @Override
            public void onFailure(Call<UserListe> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getCause().toString(),Toast.LENGTH_SHORT).show();
                call.cancel();
                GlobaleData.newFragment.dismiss();
            }
        });

    }


}