package com.example.shrinkio.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.shrinkio.R;

import java.util.ArrayList;

 public class ListeningAdapter extends BaseAdapter {


    Context context;
    LayoutInflater layoutInflater;
    ArrayList<User> users;
    public ListeningAdapter(Context con, ArrayList<User> users)
    {
        context=con;
        layoutInflater = LayoutInflater.from(context);
        this.users=users;
    }
    @Override
    public int getCount() {
        return users.size();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.activity_listening_adapter, null, false);
            holder = new ViewHolder();
            holder.fullname = (TextView) convertView.findViewById(R.id.username);
            holder.country = (TextView) convertView.findViewById(R.id.country);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        User user=users.get(position);
        holder.fullname.setText(user.getUsername());
        holder.country.setText(user.getCountry());
        return convertView;
    }
    public class ViewHolder {
        TextView fullname, country;
    }
    @Override
    public Object getItem(int position) {
        return users.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
}