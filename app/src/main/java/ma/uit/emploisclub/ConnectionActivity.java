package ma.uit.emploisclub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ma.uit.emploisclub.Controllers.MainActivity;

public class ConnectionActivity extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        button = (Button) findViewById(R.id.btnConnection);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent= new Intent(ConnectionActivity.this , MainActivity.class);
                ConnectionActivity.this.startActivity(intent);
            }
        });
    }

}