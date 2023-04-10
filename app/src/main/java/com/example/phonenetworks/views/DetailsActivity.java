package com.example.phonenetworks.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phonenetworks.R;
import com.example.phonenetworks.models.Fields;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Fields data = (Fields) getIntent().getSerializableExtra("details");

        ImageView preview = findViewById(R.id.iv_preview_details);
        preview.setImageResource(data.getImage());

        TextView code = findViewById(R.id.tv_codeoperator_details);
        code.setText("Code : " + data.getCodeOp()+"");

        TextView operator = findViewById(R.id.tv_operator_details);
        operator.setText(data.getNomOp()+"");

        TextView networks = findViewById(R.id.tv_networks_details);
        networks.setText(data.getTechnologies()+"");

        TextView idDept = findViewById(R.id.tv_iddept_details);
        idDept.setText(data.getInseeDep()+"");

        TextView dept = findViewById(R.id.tv_dept_details);
        dept.setText(data.getNomDep()+"");

        TextView ville = findViewById(R.id.tv_ville_details);
        ville.setText(data.getNomCom()+"");

        TextView region = findViewById(R.id.tv_region_details);
        region.setText(data.getNomReg()+"");

        TextView site = findViewById(R.id.tv_site_details);
        site.setText("Site : " + data.getNumSite()+"");

        TextView cooX = findViewById(R.id.tv_coo_x_details);
        TextView cooY = findViewById(R.id.tv_coo_y_details);

        String x = "Longitude: ";
        x += data.getCoordonnees().get(0)+"";

        String y = "Latitude: ";
        y += data.getCoordonnees().get(1)+"";

        cooX.setText(x);
        cooY.setText(y);
    }
}