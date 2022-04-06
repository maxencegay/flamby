package com.flamby.citizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    ImageButton searchbutton;
    ImageButton accountbutton;
    ImageButton citizzbutton;
    ImageButton add_advertbutton;

    //ImageButton forpopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchbutton = (ImageButton) findViewById(R.id.researchbutton);
        accountbutton = (ImageButton) findViewById(R.id.button_account);
        citizzbutton = (ImageButton) findViewById(R.id.button_citizz);
        add_advertbutton = (ImageButton) findViewById(R.id.button_add_advert);

        //forpopup = (ImageButton) findViewById(R.id.button_forpopup_advert);

        accountbutton.setOnClickListener(this);
        add_advertbutton.setOnClickListener(this);
        searchbutton.setOnClickListener(this);
        //forpopup.setOnClickListener(this);


        /*
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
        */

        String[] type = {"Object/animal lost","paid work","accident","event","0","0","0"};
        String[] title = {"dog lost","mow the lawn","car accident","fun fair","0","0","0"};
        String[] description = {"i have lost my thomas","100$ paid","block the road","each attractions = less than 5$","0","0","0"};
        String[] address = {"3, super road","54, main road","8, belveder place","jackie main street","0","0","0"};
        String[] city = {"beynost","dublin","lyon","paris","0","0","0"};
        String[] region = {"auvergne-rhone-alpes","dublin county","auvergne-rhone-alpes","ile-de-france","0","0","0"};
        String[] department = {"ain","dublin","rhone","paris","0","0","0"};
        String[] mnemonic = {"advert1","advert2","advert3","advert1","0","0","0"};

        ArrayList<Advert> AdvertArrayList = new ArrayList<>();

        for(int i = 0; i < type.length;i++)
        {
            String img = "advert3";

            if(type[i] == "paid work")
            {
                img = "advert2";
            }
            if(type[i] == "accident")
            {
                img = "advert1";
            }
            if(type[i] == "event")
            {
                img = "advert4";
            }
            Advert advert = new Advert(type[i],title[i],description[i],address[i],city[i],region[i],department[i],img);
            AdvertArrayList.add(advert);

        }

        // get list view

        ListView AdvertListview = findViewById(R.id.listview);
        AdvertListview.setAdapter(new AdvertListAdapter(this,AdvertArrayList));

        /*
        AdvertListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(MainActivity.this,popupActivity.class));
            }
        });*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_account:
                //Intent intentLoadNewActivity = new Intent(MainActivity.this, accountactivity.class);
                //startActivity(intentLoadNewActivity);
                //setContentView(R.layout.accountactivity);
                break;
            case R.id.button_add_advert:
                //Intent intentLoadNewActivity2 = new Intent(MainActivity.this, reportactivity.class);
                //startActivity(intentLoadNewActivity2);
                //setContentView(R.layout.reportactivity);
                break;
            case R.id.researchbutton:
                //Intent intentLoadNewActivity3 = new Intent(MainActivity.this, researchactivity.class);
                //startActivity(intentLoadNewActivity3);
                //setContentView(R.layout.researchactivity);
                break;
            /*case R.id.button_forpopup_advert:
                setContentView(R.layout.popup_advert);
                break;*/
        }
    }
}