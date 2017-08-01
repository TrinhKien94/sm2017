package com.tc.cmcglobal;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.tc.cmcglobal.fragments.LessionFragment;
import com.tc.cmcglobal.fragments.SettingFragment;
import com.tc.cmcglobal.fragments.TopFragment;

/**
 * Main Application class
 * @author gondzo
 */
public class MainActivity extends AppCompatActivity{
    private ViewPager mViewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*mViewPager = (ViewPager)findViewById(R.id.viewpager);
        mViewPager.setAdapter(new PagerHomeAdapter(getSupportFragmentManager()));
*/
        //setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_nav_main);
        //disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {

                            case R.id.action_item1:
                                selectedFragment = TopFragment.getInstance();
                                break;
                            case R.id.action_item2:
                                selectedFragment = LessionFragment.getInstance();
                                break;
                            case R.id.action_item3:
                                selectedFragment = SettingFragment.getInstance();
                                break;
                            case R.id.action_item4:
                                logout();
                                break;
                        }

                        if(item.getItemId() != R.id.action_item4) {
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.frame_layout, selectedFragment);
                            transaction.commit();

                        } else if (item.getItemId() == R.id.action_item4){
                            finish();
                        }
                        return true;
                    }

                    private void logout() {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                        alertDialogBuilder.setMessage("Are you sure you want to logout?");
                        alertDialogBuilder.setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        //Starting login activity
                                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    }
                                });

                        alertDialogBuilder.setNegativeButton("No",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {

                                    }
                                });

                        //Showing the alert dialog
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, TopFragment.getInstance());
        transaction.commit();
    }


}
