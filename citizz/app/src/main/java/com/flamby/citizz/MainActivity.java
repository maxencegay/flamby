package com.flamby.citizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //for database
    Connection connect;
    String ConnectionResult="";

    ImageButton searchbutton;
    ImageButton accountbutton;
    ImageButton citizzbutton;
    ImageButton add_advertbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchbutton = (ImageButton) findViewById(R.id.researchbutton);
        accountbutton = (ImageButton) findViewById(R.id.button_account);
        citizzbutton = (ImageButton) findViewById(R.id.button_citizz);
        add_advertbutton = (ImageButton) findViewById(R.id.button_add_advert);

        accountbutton.setOnClickListener(this);
        add_advertbutton.setOnClickListener(this);
        searchbutton.setOnClickListener(this);

        try {
            search();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_account:
                Intent intentLoadNewActivity = new Intent(this, Profil.class);
                startActivity(intentLoadNewActivity);
                //setContentView(R.layout.profile_page);
                break;
            case R.id.button_add_advert:
                Intent intentLoadNewActivity2 = new Intent(MainActivity.this, ReportActivity.class);
                startActivity(intentLoadNewActivity2);
                //setContentView(R.layout.main_report_activity);
                break;
            case R.id.researchbutton:
                Intent intentLoadNewActivity3 = new Intent(this, Search.class);
                startActivity(intentLoadNewActivity3);
                //setContentView(R.layout.search_page);
                break;
            case R.id.button_citizz:
                Intent intentLoadNewActivity4 = new Intent(this, MainActivity.class);
                startActivity(intentLoadNewActivity4);
                break;
            /*case R.id.button_forpopup_advert:
                setContentView(R.layout.popup_advert);
                break;*/
        }
    }

    //for database
    public void database() throws SQLException {

        ConnectionDatabaseHelper connectionHelper = new ConnectionDatabaseHelper();
        connect = connectionHelper.connectionclass("45.155.170.63", "project_oop", "postgres", "123456", "5432");
        if(connect!=null){
            String query = "SELECT * FROM advert ORDER BY id DESC LIMIT 10;";
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(query);

            ArrayList<Advert> AdvertArrayList = new ArrayList<>();

            while (rs.next()) {
                Advert advert = new Advert(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
                AdvertArrayList.add(advert);
            }

            ListView AdvertListview = findViewById(R.id.listview);
            AdvertListview.setAdapter(new AdvertListAdapter(this,AdvertArrayList));

            connect.close();
        }
        else{
            ConnectionResult = "Check Connection";
            System.out.println(ConnectionResult);
        }
    }

    public void search() throws IOException, SQLException {
        try{
            FileInputStream in = openFileInput("search.txt");
            BufferedReader br= new BufferedReader(new InputStreamReader(in));
            if (br.readLine().equals("True")){
                ConnectionDatabaseHelper connectionHelper = new ConnectionDatabaseHelper();
                connect = connectionHelper.connectionclass("45.155.170.63", "project_oop", "postgres", "123456", "5432");
                if(connect!=null){
                    String mnemonic = br.readLine();
                    String region = br.readLine();
                    String department = br.readLine();
                    String city = br.readLine();

                    region = "Rhône-Alpes";
                    department = "Ain";
                    city = "Beynost";

                    String query = "SELECT * FROM advert WHERE (city, region, department, mnemonic) = ('"+ city +"','"+ region +"','"+ department +"','"+ mnemonic +"') ORDER BY id DESC LIMIT 10;";
                    Statement st = connect.createStatement();
                    ResultSet rs = st.executeQuery(query);

                    ArrayList<Advert> AdvertArrayList = new ArrayList<>();

                    while (rs.next()) {
                        Advert advert = new Advert(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
                        AdvertArrayList.add(advert);
                        System.out.println(rs.getString(2));
                    }

                    ListView AdvertListview = findViewById(R.id.listview);
                    AdvertListview.setAdapter(new AdvertListAdapter(this,AdvertArrayList));

                    connect.close();
                    FileOutputStream out = this.openFileOutput("search.txt", MODE_PRIVATE);
                    out.write("False".getBytes());
                    out.close();
                }
            }
            else{
                try {
                    database();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        } catch (IOException | SQLException e) {
            try {
                database();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}