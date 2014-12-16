package com.msevgi.repost.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.msevgi.repost.R;
import com.msevgi.repost.adapter.FriendListAdapter;
import com.msevgi.repost.constant.ApplicationConstants;
import com.msevgi.repost.event.GetFollowedByListEvent;
import com.msevgi.repost.event.GetFollowerListEvent;
import com.msevgi.repost.model.User;
import com.msevgi.repost.task.GetFollowedByTask;
import com.msevgi.repost.task.GetFollowersTask;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.InjectView;

public final class FragmentFriends extends BaseFragment implements View.OnClickListener {

    private HashMap<String, User> friendMap;
    private ArrayList<User> list;
    private FriendListAdapter adapter;
    @InjectView(R.id.listview_friends)
    protected ListView listViewFriends;

    @NonNull
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_friends;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new GetFollowersTask(ApplicationConstants.getFollowersUrl()).execute();
        list = new ArrayList<>();
        adapter = new FriendListAdapter(getActivity(), 0, list);
        adapter.setNotifyOnChange(true);
        listViewFriends.setAdapter(adapter);
    }

    @Subscribe
    public void getFollowerList(GetFollowerListEvent getFollowerListEvent) {
        friendMap = getFollowerListEvent.getFollowerList();
        new GetFollowedByTask(ApplicationConstants.getFollowedByUrl()).execute();
    }

    @Subscribe
    public void getFollowedByList(GetFollowedByListEvent getFollowedByListEvent) {
        for (int i = 0; i < getFollowedByListEvent.getFollowedByList().size(); i++) {
            String id = getFollowedByListEvent.getFollowedByList().get(i).getId();
            if (friendMap.containsKey(id)) {
                friendMap.get(id).setType(2);
            } else
                friendMap.put(id, getFollowedByListEvent.getFollowedByList().get(i));
        }

        list.addAll(friendMap.values());
        adapter.notifyDataSetChanged();
    }

    private void addFooter() {
        View footerView = ((LayoutInflater) getActivity().getApplicationContext().getSystemService(Context
                .LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.listview_footer, null, false);
        Button buttonLoadMore = (Button) footerView.findViewById(R.id.button_load_more);
        buttonLoadMore.setOnClickListener(this);
        listViewFriends.addFooterView(footerView);
    }

    @Override
    public void onClick(View v) {

    }
}
