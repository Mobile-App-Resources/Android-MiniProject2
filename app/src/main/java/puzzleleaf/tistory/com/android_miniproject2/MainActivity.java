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
import java.util.Collections;
import java.util.Comparator;

import puzzleleaf.tistory.com.android_miniproject2.adapter.ItemAdapter;
import puzzleleaf.tistory.com.android_miniproject2.object.HsdObject;


public class MainActivity extends AppCompatActivity{

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
        dataInit();
        recyclerViewInit();
        tabInit();
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

        tabLayout.addTab(tabLayout.newTab().setText("이름순"));
        tabLayout.addTab(tabLayout.newTab().setText("최저가"));
        tabLayout.addTab(tabLayout.newTab().setText("최고가"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0) {
                    DescendingObj descendingObj = new DescendingObj();
                    Collections.sort(hsdObjects,descendingObj);
                    itemAdapter.notifyDataSetChanged();
                }
                else if(tab.getPosition()==1){
                    AscendingPrice ascendingPrice = new AscendingPrice();
                    Collections.sort(hsdObjects,ascendingPrice);
                    itemAdapter.notifyDataSetChanged();
                }
                else{
                    DescendingPrice descendingPrice = new DescendingPrice();
                    Collections.sort(hsdObjects,descendingPrice);
                    itemAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    //저장 데이터 초기화
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

    //LayoutManager 변환
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

class DescendingObj implements Comparator<HsdObject>{

    @Override
    public int compare(HsdObject o1, HsdObject o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}
//내림차순 정렬 - 비싼 가격순
class DescendingPrice implements Comparator<HsdObject>
{
    //o1 이 더 작으면 양수 반환 // 같으면 0 반환  //더 크면 음수 반환
    @Override
    public int compare(HsdObject o1, HsdObject o2) {
        return o1.getPrice() < o2.getPrice() ? 1 : o1.getPrice()==o2.getPrice() ? 0 : -1;
    }
}
//오름차순 정렬 - 싼 가격순
class AscendingPrice implements Comparator<HsdObject>{

    @Override
    public int compare(HsdObject o1, HsdObject o2) {
        return o1.getPrice() > o2.getPrice() ? 1 : o1.getPrice()==o2.getPrice() ? 0 : -1;
    }
}
