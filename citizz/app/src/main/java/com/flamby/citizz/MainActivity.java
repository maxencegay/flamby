package com.flamby.citizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_account:
                //Intent intentLoadNewActivity = new Intent(MainActivity.this, reportactivity.class);
                //startActivity(intentLoadNewActivity);
                break;
            case R.id.button_add_advert:
                //Intent intentLoadNewActivity2 = new Intent(MainActivity.this, accountactivity.class);
                //startActivity(intentLoadNewActivity2);
                break;
            case R.id.researchbutton:
                //Intent intentLoadNewActivity3 = new Intent(MainActivity.this, researchactivity.class);
                //startActivity(intentLoadNewActivity3);
                break;
        }
    }
}