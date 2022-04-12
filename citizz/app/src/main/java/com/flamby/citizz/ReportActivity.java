package com.flamby.citizz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity implements View.OnClickListener{

    Button Publish_button;

    Connection connect;

    EditText txttile_report, txtdescription_report, txtregion_report, txtdepartment_report, txtcity_report, txtadress_report;
    Spinner mySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_report_activity);

        mySpinner = (Spinner) findViewById(R.id.spinner_report);

        txttile_report = findViewById(R.id.tile_report);
        txtdescription_report = findViewById(R.id.description_report);
        txtregion_report = findViewById(R.id.region_report);
        txtdepartment_report = findViewById(R.id.department_report);
        txtcity_report = findViewById(R.id.city_report);
        txtadress_report = findViewById(R.id.adress_report);

        Publish_button = findViewById(R.id.report_view);

        Publish_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.report_view:
                if (validateData()){
                    try {
                        if(database()){
                            Intent intent = new Intent(this, MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Intent intent = new Intent(this, ReportActivity.class);
                            startActivity(intent);
                        }
                    } catch (SQLException throwables) {
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                    }
                }
                break;
        }
    }

    public boolean database() throws SQLException {
        ConnectionDatabaseHelper connectionHelper = new ConnectionDatabaseHelper();
        connect = connectionHelper.connectionclass("45.155.170.63", "project_oop", "postgres", "123456", "5432");
        if(connect!=null){
            try {
                FileInputStream in = openFileInput("save.txt");
                BufferedReader br= new BufferedReader(new InputStreamReader(in));

                br.readLine();
                br.readLine();
                String id = br.readLine();
                br.close();

                String text = mySpinner.getSelectedItem().toString();

                String mnemonic;

                switch (text) {
                    case "Paid work":
                        mnemonic = "advert2";
                        break;
                    case "Accident":
                        mnemonic = "advert1";
                        break;
                    case "Events":
                        mnemonic = "advert4";
                        break;
                    default:
                        mnemonic = "advert3";
                        break;
                }

                String query = "INSERT INTO advert (id, type, title, description, address, city, region, department, mnemonic, account_id) " +
                        "VALUES (nextval('advert_id'), '"+ text +"', '"+ txttile_report.getText().toString() +"', '"+ txtdescription_report.getText().toString() +"', '"+ txtadress_report.getText().toString() +"', '"+ txtcity_report.getText().toString() +"', '"+ txtregion_report.getText().toString() +"', '"+ txtdepartment_report.getText().toString() +"', '"+ mnemonic +"', "+ id +");";
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);

                connect.close();
                return true;

            } catch (IOException e) {
                connect.close();
                return true;
            }
        }
        else{
            String ConnectionResult = "Check Connection";
            System.out.println(ConnectionResult);
            return false;
        }

    }

    private boolean validateData(){
        if(txttile_report.getText().toString().equals("")) {
            return false;
        }
        if(txtdescription_report.getText().toString().equals("")) {
            return false;
        }
        if(txtregion_report.getText().toString().equals("")) {
            return false;
        }
        if(txtdepartment_report.getText().toString().equals("")) {
            return false;
        }
        if(txtcity_report.getText().toString().equals("")) {
            return false;
        }
        if(txtadress_report.getText().toString().equals("")) { ;
            return false;
        }
        return true;
    }
}
