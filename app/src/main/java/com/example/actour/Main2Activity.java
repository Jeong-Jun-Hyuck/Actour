package com.example.actour;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import me.relex.circleindicator.CircleIndicator;

public class Main2Activity extends AppCompatActivity {
    FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        //슬라이더
        ViewPager vpPager = (ViewPager) findViewById(R.id.main2_view);
        adapterViewPager = new MainActivity.MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        vpPager.setClipToPadding(false);
        vpPager.setPadding(100, 0, 40, 0);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(vpPager);

        Toolbar tb = findViewById(R.id.Maintoolbar);
        setSupportActionBar(tb);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.myMenu_button:
                Intent profilesIntent = new Intent(Main2Activity.this,Profiles.class);
                 Main2Activity.this.startActivity(profilesIntent);
                return true;

            default:

                Toast.makeText(getApplicationContext(), "나머지 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);
        }
    }


    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:

                    return FirstmainPic.newInstance(0, "Page # 1");
                case 1:
                    return secondmainPic.newInstance(1, "Page # 2");
                case 2:
                    return thirdmainPic.newInstance(2, "Page # 3");
                default:
                    return null;
            }
        }
    }

}

