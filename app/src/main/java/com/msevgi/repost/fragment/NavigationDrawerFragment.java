package com.msevgi.repost.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.msevgi.repost.R;
import com.msevgi.repost.event.NavigationItemSelect;
import com.msevgi.repost.provider.BusProvider;


public class NavigationDrawerFragment extends Fragment {

    private ActionBarDrawerToggle drawerToggle;

    private DrawerLayout drawerLayout;
    private ListView drawerListview;
    private View fragmentContainerView;

    private int currentSelectedPosition = 0;

    public NavigationDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        selectItem(currentSelectedPosition);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        drawerListview = (ListView) inflater.inflate(
                R.layout.fragment_navigation_drawer, container, false);
        drawerListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });
        drawerListview.setAdapter(new ArrayAdapter<String>(
                getActivity().getApplicationContext(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                new String[]{
                        getString(R.string.title_section1),
                        getString(R.string.title_section2),
                        getString(R.string.title_section3),
                }));
        drawerListview.setItemChecked(currentSelectedPosition, true);
        return drawerListview;
    }


    public boolean isDrawerOpen() {
        return drawerLayout != null && drawerLayout.isDrawerOpen(fragmentContainerView);
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar) {
        fragmentContainerView = getActivity().findViewById(fragmentId);
        this.drawerLayout = drawerLayout;
        drawerToggle = new ActionBarDrawerToggle(getActivity(), NavigationDrawerFragment.this.drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }


            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };

        this.drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                drawerToggle.syncState();
            }
        });


        this.drawerLayout.setDrawerListener(drawerToggle);
    }

    private void selectItem(int position) {
        currentSelectedPosition = position;
        if (drawerListview != null) {
            drawerListview.setItemChecked(position, true);
        }
        if (drawerLayout != null) {
            drawerLayout.closeDrawer(fragmentContainerView);
        }

        NavigationItemSelect item = new NavigationItemSelect(position);
        BusProvider.getInstance().post(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (drawerLayout != null && isDrawerOpen()) {
            inflater.inflate(R.menu.global, menu);
            showGlobalContextActionBar();
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        if (item.getItemId() == R.id.action_example) {
            Toast.makeText(getActivity(), "Example action.", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showGlobalContextActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setTitle(R.string.app_name);
    }

    private ActionBar getActionBar() {
        return ((ActionBarActivity) getActivity()).getSupportActionBar();
    }
}
