package com.example.phonenetworks.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.phonenetworks.R;

public class MainActivity extends AppCompatActivity {

    private Button simpleButton = null;
    private Button sortButtonDept = null;
    private Button sortButtonOp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.simpleButton = findViewById(R.id.bt_normal);
        this.sortButtonDept = findViewById(R.id.bt_trier_departement);
        this.sortButtonOp = findViewById(R.id.bt_trier_operateur);

        this.simpleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isNetworkAvailable(getApplicationContext())) {
                    Toast.makeText(getApplicationContext(),"Non connecté à Internet", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent i = new Intent(getApplicationContext(), ListDataActivity.class);
                i.putExtra(ListDataActivity.KEY_TYPE, "SAMPLE");

                startActivity(i);
            }
        });

        this.sortButtonDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isNetworkAvailable(getApplicationContext())) {
                    Toast.makeText(getApplicationContext(),"Non connecté à Internet", Toast.LENGTH_LONG).show();
                    return;
                }

                Toast.makeText(getApplicationContext(),"Non-implémenté", Toast.LENGTH_LONG).show();
            }
        });

        this.sortButtonOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isNetworkAvailable(getApplicationContext())) {
                    Toast.makeText(getApplicationContext(),"Non connecté à Internet", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent i = new Intent(getApplicationContext(), ListDataActivity.class);
                i.putExtra(ListDataActivity.KEY_TYPE, "SORT_OP");

                startActivity(i);
            }
        });
    }

    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            Log.d("NetworkCheck", "isNetworkAvailable: No");
            return false;
        }
        NetworkInfo[] info = connectivity.getAllNetworkInfo();
        if (info != null) {
            for (int i = 0; i < info.length; i++) {
                if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                    Log.d("NetworkCheck", "isNetworkAvailable: Yes");
                    return true;
                }
            }
        }
        return false;
    }
}