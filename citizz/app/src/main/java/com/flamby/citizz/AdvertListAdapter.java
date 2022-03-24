package com.flamby.citizz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdvertListAdapter extends BaseAdapter {

    private Context context;
    private List<Advert> advertList;
    private LayoutInflater inflater;

    public AdvertListAdapter (Context context,List<Advert> advertList){
        this.context = context;
        this.advertList = advertList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return advertList.size();
    }

    @Override
    public Advert getItem(int position) {
        return advertList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater.inflate(R.layout.list_item,null);

        // get info about item
        Advert currentItem = getItem(i);
        String type = currentItem.getType();
        String title = currentItem.getTitle();
        String mnemonic = currentItem.getMnemonic();

        // get item icon view
        ImageView itemIconView = view.findViewById(R.id.profilpicture);
        String resourceName = "ic_" + mnemonic;
        int resId = context.getResources().getIdentifier(resourceName,"drawable", context.getPackageName());
        itemIconView.setImageResource(resId);

        // get item name view
        TextView typeview = view.findViewById(R.id.kindadvert);
        typeview.setText(type);

        TextView titleview = view.findViewById(R.id.advertTitle);
        titleview.setText(title);

        return view;
    }
}
