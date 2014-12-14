package com.msevgi.repost.adapter;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.msevgi.repost.R;
import com.msevgi.repost.bean.response.UserResponseBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;

public class FriendListAdapter extends ArrayAdapter<UserResponseBean> {

    private ViewHolder viewHolder;

    public FriendListAdapter(Context context, int resource, List<UserResponseBean> objects) {
        super(context, resource, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout
                    .friends_row, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();
        UserResponseBean item = getItem(position);
        if (item != null) {
            viewHolder.textViewFullName.setText(item.getFull_name());
            viewHolder.textViewUserName.setText(item.getUsername());
            Picasso.with(getContext()).load(item.getProfile_picture()).into(viewHolder.imageViewProfile);
        }
        return convertView;
    }

    final static class ViewHolder {

        @InjectView(R.id.circleimageview_profile)
        protected CircleImageView imageViewProfile;
        @InjectView(R.id.textview_full_name)
        protected TextView textViewFullName;
        @InjectView(R.id.textview_user_name)
        protected TextView textViewUserName;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }

    }
}
