package com.flamby.citizz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //for database
    Connection connect;
    String ConnectionResult="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Advert> AdvertList = new ArrayList<>();
        AdvertList.add(new Advert("Object/animal lost","dog lost","i have lost my thomas",
                "3,super road","lyon","auvergne-rhonesalpes","rhone","advert1"));
        AdvertList.add(new Advert("Object/animal lost","cat lost","i have lost my thomas",
                "3,super road","lyon","auvergne-rhonesalpes","rhone","advert2"));
        AdvertList.add(new Advert("Object/animal lost","horse lost","i have lost my thomas",
                "3,super road","lyon","auvergne-rhonesalpes","rhone","advert3"));

        AdvertList.add(new Advert("Object/animal lost","dog lost","i have lost my thomas",
                "3,super road","lyon","auvergne-rhonesalpes","rhone","advert1"));
        AdvertList.add(new Advert("Object/animal lost","cat lost","i have lost my thomas",
                "3,super road","lyon","auvergne-rhonesalpes","rhone","advert2"));
        AdvertList.add(new Advert("Object/animal lost","horse lost","i have lost my thomas",
                "3,super road","lyon","auvergne-rhonesalpes","rhone","advert3"));

        // get list view
        ListView AdvertListview = findViewById(R.id.listview);
        AdvertListview.setAdapter(new AdvertListAdapter(this,AdvertList));
    }

    //for database
    public void test(View v) throws SQLException {

        TextView TextView = (TextView) findViewById(R.id.research);

        ConnectionDatabaseHelper connectionHelper = new ConnectionDatabaseHelper();
        //connect = connectionHelper.connectionclass("10.0.2.2", "postgres", "postgres", "Titi01700!", "5432");
        connect = connectionHelper.connectionclass("45.155.170.63", "project_oop", "postgres", "123456", "5432");
        if(connect!=null){
                /*String query = "CREATE SCHEMA IF NOT EXISTS \"25410\"";
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);*/

            String query = "SELECT * FROM account FETCH FIRST 1 ROWS ONLY;";
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(query);

            rs.next();

            String s = rs.getString("name");

            rs.next();

            try{

                String ss = rs.getString("id");

                System.out.println(ss);

            }
            catch (Exception ex){

            }


            TextView.setText(s + " ");


            while (rs.next()) {
                //TextView.setText(rs.getString(1));
            }
        }
        else{
            ConnectionResult = "Check Connection";
            TextView.setText(ConnectionResult);
        }
        /*try{

        }
        catch (Exception ex){

        }*/
    }
}