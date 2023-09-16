package com.vndevpro.android53_day4;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {

    private ArrayList<User> mListUsers;
    private Activity mActivity;

    public UserAdapter(ArrayList<User> listUsers, Activity activity) {
        this.mListUsers = listUsers;
        this.mActivity = activity;
    }

    @Override
    public int getCount() {
        return mListUsers != null ? mListUsers.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mListUsers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mActivity.getLayoutInflater().inflate(R.layout.layout_item_user, null, false);
        }

        ImageView imgAvatar = convertView.findViewById(R.id.imgAvatar);
        TextView tvUserName = convertView.findViewById(R.id.tvUserName);
        TextView tvInfo = convertView.findViewById(R.id.tvInfo);

        User user = mListUsers.get(position);
        tvUserName.setText(user.getUserName());
        tvInfo.setText("Age : " + user.getAge() + " | Email : " + user.getEmail() + " | Adds : " + user.getAddress());

        Glide.with(mActivity).load(user.getAvatar()).into(imgAvatar);

        return convertView;
    }
}

