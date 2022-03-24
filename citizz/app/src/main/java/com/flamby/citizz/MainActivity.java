package com.flamby.citizz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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
}