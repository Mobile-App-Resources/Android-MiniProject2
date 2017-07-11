package puzzleleaf.tistory.com.android_miniproject2;

import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import puzzleleaf.tistory.com.android_miniproject2.adapter.TabPagerAdapter;


public class MainActivity extends AppCompatActivity {

    //Drawer Toolbar 변수
    private DrawerLayout drawer;
    private Toolbar toolbar;

    //탭 레이아웃
    private TabLayout tabLayout;
    private TabPagerAdapter pagerAdapter;

    int test = 5;
    //Viewpager
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerToolbarInit();
        tabInit();
        viewPagerInit();

    }

    //Drawer
    private void drawerToolbarInit()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        //접근성 지원을 위한 열기 닫기에 해당하는 문자열 리소스
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, 0, 0){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    //Tab
    private void tabInit()
    {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.addTab(tabLayout.newTab().setText("가나다"));
        tabLayout.addTab(tabLayout.newTab().setText("가나다"));
        tabLayout.addTab(tabLayout.newTab().setText("가나다"));


        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

    }

    //ViewPager
    private void viewPagerInit()
    {
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(2);
        pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount(),test);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
