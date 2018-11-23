package com.dedykuncoro.haversine.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.dedykuncoro.haversine.R;
import com.dedykuncoro.haversine.app.AppController;
import com.dedykuncoro.haversine.module.Jarak;

import java.util.List;

/**
 * Created by Kuncoro on 03/29/2016.
 */
public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Jarak> jarakItems;
    ImageLoader imageLoader;

    public CustomListAdapter(Activity activity, List<Jarak> jarakItems) {
        this.activity = activity;
        this.jarakItems = jarakItems;
    }

    @Override
    public int getCount() {
        return jarakItems.size();
    }

    @Override
    public Object getItem(int location) {
        return jarakItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.gambar);
        TextView nama = (TextView) convertView.findViewById(R.id.nama);
        TextView jarak = (TextView) convertView.findViewById(R.id.jarak);

        Jarak j = jarakItems.get(position);

        thumbNail.setImageUrl(j.getGambar(), imageLoader);
        nama.setText(j.getNama());
        jarak.setText(j.getJarak()+" Km");

        return convertView;
    }

}