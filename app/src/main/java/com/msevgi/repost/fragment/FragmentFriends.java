package com.msevgi.repost.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.msevgi.repost.R;
import com.msevgi.repost.Task.GetFollowersTask;
import com.msevgi.repost.adapter.FriendListAdapter;
import com.msevgi.repost.bean.response.UserResponseBean;
import com.msevgi.repost.event.GetUserListEvent;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import butterknife.InjectView;

public final class FragmentFriends extends BaseFragment {

    @InjectView(R.id.listview_friends)
    protected ListView listViewFriends;

    @NonNull
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_friends;
    }

    private ArrayList<UserResponseBean> friendList;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("alo");
        new GetFollowersTask("https://api.instagram" +
                ".com/v1/users/309977638/follows?access_token=309977638.7ea2e29" +
                ".5839ab106efd414b982f0f76fa701d7b").execute();
        friendList = new ArrayList<>();

        FriendListAdapter adapter = new FriendListAdapter(getActivity(), 0, friendList);
        adapter.setNotifyOnChange(true);
        listViewFriends.setAdapter(adapter);
    }

    @Subscribe
    public void getUserList(GetUserListEvent getUserListEvent) {
        friendList.addAll(getUserListEvent.getFriendList());
        View footerView = ((LayoutInflater) getActivity().getApplicationContext().getSystemService(Context
                .LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.listview_footer, null, false);
        listViewFriends.addFooterView(footerView);
    }
}
