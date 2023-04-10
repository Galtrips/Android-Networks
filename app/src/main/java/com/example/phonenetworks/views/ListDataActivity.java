package com.example.phonenetworks.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.phonenetworks.R;
import com.example.phonenetworks.adapters.NetworkAdapter;
import com.example.phonenetworks.controllers.ListDataController;
import com.example.phonenetworks.managers.NetworkDataManagerCallBack;
import com.example.phonenetworks.models.Fields;
import com.example.phonenetworks.models.Networks;
import com.example.phonenetworks.models.Record;
import com.example.phonenetworks.utils.EndlessScrollListener;

import java.sql.Array;
import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {

    private ListDataController mainActivityController = new ListDataController();
    private ArrayList<Record> datas = null;

    private NetworkAdapter adapter = null;

    public final static String KEY_TYPE = "DATA_TYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        this.datas = new ArrayList();
        adapter = new NetworkAdapter(datas);
        ListView lv = findViewById(R.id.lv_networks);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent details = new Intent(getApplicationContext(), DetailsActivity.class);
                Fields data = datas.get(i).getFields();
                details.putExtra("details", data);
                startActivity(details);
            }
        });

        lv.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to your AdapterView
                loadNextDataFromApi(page);
                // or loadNextDataFromApi(totalItemsCount);
                return true; // ONLY if more data is actually being loaded; false otherwise.
            }
        });

        String type = getIntent().getStringExtra(KEY_TYPE);

        switch (type) {
            case "SORT_DEPT":

                break;

            case "SORT_OP":
                getDataSorted(0);
                break;

            case "SAMPLE":
                getSimpleData(0);
                adapter.notifyDataSetChanged();
                break;

            default:
                Toast.makeText(this,"Le type de données demandés est incorrect", Toast.LENGTH_LONG);
                Log.e("ListData", "Le type de donnée est inccorect");
                break;

        }
    }

    private void getSimpleData(int offset) {
        mainActivityController.getSimpleNetworks(offset, new NetworkDataManagerCallBack() {
            @Override
            public void getDataResponseSuccess(Networks networks) {
                datas.addAll(networks.getRecords());
                adapter.setArray(datas);
                adapter.notifyDataSetChanged();
                addMap();
            }

            @Override
            public void getDataResponseError(String message) {
                Toast.makeText(getApplicationContext(),"une erreur est apparue lors de la recherche de données", Toast.LENGTH_LONG);
                Log.e("ListData", "une erreur est apparue lors de la recherche de données");
            }

        });
    }

    private void getDataSorted(int offset) {
        mainActivityController.getNetworksSorted(offset, new NetworkDataManagerCallBack() {
            @Override
            public void getDataResponseSuccess(Networks networks) {
                datas.addAll(networks.getRecords());
                adapter.setArray(datas);
                adapter.notifyDataSetChanged();
                addMap();
            }

            @Override
            public void getDataResponseError(String message) {
                Toast.makeText(getApplicationContext(),"une erreur est apparue lors de la recherche de données", Toast.LENGTH_LONG);
                Log.e("ListData", "une erreur est apparue lors de la recherche de données");
            }

        });
    }

    private void getSortedDataByOperator(int offset) {
        mainActivityController.getSimpleNetworks(offset, new NetworkDataManagerCallBack() {
            @Override
            public void getDataResponseSuccess(Networks networks) {
                datas.addAll(networks.getRecords());
                datas.sort((a, b) -> a.getFields().getNomOp().compareTo(b.getFields().getNomOp()));
                adapter.setArray(datas);
                adapter.notifyDataSetChanged();
                addMap();
            }

            @Override
            public void getDataResponseError(String message) {
                Toast.makeText(getApplicationContext(),"une erreur est apparue lors de la recherche de données", Toast.LENGTH_LONG);
                Log.e("ListData", "une erreur est apparue lors de la recherche de données");
            }

        });
    }

    private void getSortedDataByDept(int offset) {
        mainActivityController.getSimpleNetworks(offset, new NetworkDataManagerCallBack() {
            @Override
            public void getDataResponseSuccess(Networks networks) {
                datas.addAll(networks.getRecords());
                datas.sort((a, b) -> a.getFields().getInseeDep().compareTo(b.getFields().getInseeDep()));
                adapter.setArray(datas);
                adapter.notifyDataSetChanged();
                addMap();
            }

            @Override
            public void getDataResponseError(String message) {
                Toast.makeText(getApplicationContext(),"une erreur est apparue lors de la recherche de données", Toast.LENGTH_LONG);
                Log.e("ListData", "une erreur est apparue lors de la recherche de données");
            }

        });
    }

    private void addMap() {
        ImageView maps = findViewById(R.id.iv_maps);
        maps.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), MapsActivity.class);

            mainActivityController.addCache(this.datas);

            startActivity(i);
        });
    }

    // Append the next page of data into the adapter
    // This method probably sends out a network request and appends new data items to your adapter.
    public void loadNextDataFromApi(int offset) {
        // Send an API request to retrieve appropriate paginated data
        //  --> Send the request including an offset value (i.e `page`) as a query parameter.
        //  --> Deserialize and construct new model objects from the API response
        //  --> Append the new data objects to the existing set of items inside the array of items
        //  --> Notify the adapter of the new items made with `notifyDataSetChanged()`
        this.getSimpleData(offset);
        adapter.notifyDataSetChanged();
    }

}