package puzzleleaf.tistory.com.android_miniproject2;

import android.content.res.TypedArray;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import java.util.ArrayList;

import puzzleleaf.tistory.com.android_miniproject2.adapter.ItemAdapter;
import puzzleleaf.tistory.com.android_miniproject2.object.HsdObject;


public class MainActivity extends AppCompatActivity {

    //Drawer Toolbar 변수
    private DrawerLayout drawer;
    private Toolbar toolbar;

    //탭 레이아웃
    private TabLayout tabLayout;

    //View Changer
    private ImageView imageView;
    private boolean flag =false;

    //이미지 할당
    TypedArray imgArr;
    String[] menuStr;

    //Item
    ArrayList<HsdObject> hsdObjects;

    //Recycler
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgArr = getResources().obtainTypedArray(R.array.hsd_img);
        menuStr = getResources().getStringArray(R.array.hsd_menu);
        drawerToolbarInit();
        tabInit();
        dataInit();
        recyclerViewInit();
        uiInit();

    }

    //Drawer
    private void drawerToolbarInit() {
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
    private void tabInit() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.addTab(tabLayout.newTab().setText("메뉴순"));
        tabLayout.addTab(tabLayout.newTab().setText("가격순"));
        tabLayout.addTab(tabLayout.newTab().setText("가나다"));


        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

    }

    private void dataInit() {
        hsdObjects = new ArrayList<>();
        for(int i=0;i<menuStr.length;i++) {
            HsdObject hsdObject = new HsdObject(menuStr[i],imgArr.getResourceId(i,-1));
            hsdObjects.add(hsdObject);
        }

    }

    private void recyclerViewInit() {
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        //Manager init
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);


        linearLayoutManager = new LinearLayoutManager(getApplicationContext());

        //Adapter
        itemAdapter = new ItemAdapter(getApplicationContext(),hsdObjects);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(itemAdapter);

    }

    private void uiInit() {
        imageView = (ImageView)findViewById(R.id.viewChanger);
    }

    public void viewChanger(View v) {
        if(!flag) {
            recyclerView.setLayoutManager(linearLayoutManager);
            imageView.setImageResource(R.drawable.ic_dashboard_black_24dp);
        }
        else{
            recyclerView.setLayoutManager(staggeredGridLayoutManager);
            imageView.setImageResource(R.drawable.ic_view_agenda_black_24dp);
        }

        itemAdapter.notifyItemChanged(0);
        flag = !flag;
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
