package puzzleleaf.tistory.com.android_miniproject2;

import android.content.Context;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.gjiazhe.panoramaimageview.GyroscopeObserver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import puzzleleaf.tistory.com.android_miniproject2.adapter.ItemAdapter;
import puzzleleaf.tistory.com.android_miniproject2.object.ItemObject;


public class MainActivity extends AppCompatActivity{

    //Drawer Toolbar 변수
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.toolbar) Toolbar toolbar;

    //탭 레이아웃
    @BindView(R.id.tabLayout) TabLayout tabLayout;
    @BindString(R.string.menu1) String menu1;
    @BindString(R.string.menu2) String menu2;
    @BindString(R.string.menu3) String menu3;

    //View Changer
    @BindView(R.id.viewChanger) ImageView imageView;
    boolean viewChangerFlag =false;

    //이미지 할당
    @BindArray(R.array.item_img) TypedArray imgArr;
    @BindArray(R.array.item_menu) String[] menuStr;

    //Item
    ArrayList<ItemObject> itemObjects;
    AscendingDistance ascendingDistance = new AscendingDistance();
    DescendingPopularity descendingPopularity = new DescendingPopularity();
    AscendingRecent ascendingRecent = new AscendingRecent();

    //Recycler
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    ItemAdapter itemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        drawerToolbarInit();
        dataInit();
        recyclerViewInit();
        tabInit();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    //Drawer
    private void drawerToolbarInit() {
        setSupportActionBar(toolbar);
        //접근성 지원을 위한 열기 닫기에 해당하는 문자열 리소스 0 0
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
        tabLayout.addTab(tabLayout.newTab().setText(menu1));
        tabLayout.addTab(tabLayout.newTab().setText(menu2));
        tabLayout.addTab(tabLayout.newTab().setText(menu3));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0) {
                    Collections.sort(itemObjects,ascendingDistance);
                }
                else if(tab.getPosition()==1){
                    Collections.sort(itemObjects,descendingPopularity);
                }
                else{
                    Collections.sort(itemObjects,ascendingRecent);
                }
                recyclerView.smoothScrollToPosition(0);//맨 위로
                itemAdapter.notifyDataSetChanged();
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
        itemObjects = new ArrayList<>();
        for(int i=0;i<menuStr.length;i++) {
            ItemObject itemObject = new ItemObject(menuStr[i],imgArr.getResourceId(i,-1));
            itemObjects.add(itemObject);
        }
        Collections.sort(itemObjects,ascendingDistance);
    }

    private void recyclerViewInit() {
        //Manager init
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());

        //Adapter
        itemAdapter = new ItemAdapter(getApplicationContext(), itemObjects);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(itemAdapter);

    }

    //LayoutManager 변환
    public void viewChanger(View v) {
        if(!viewChangerFlag) {
            recyclerView.setLayoutManager(linearLayoutManager);
            imageView.setImageResource(R.drawable.ic_dashboard_black_24dp);
        }
        else{
            recyclerView.setLayoutManager(staggeredGridLayoutManager);
            imageView.setImageResource(R.drawable.ic_view_agenda_black_24dp);
        }

        itemAdapter.notifyDataSetChanged();
        viewChangerFlag= !viewChangerFlag;
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
//내림차순 정렬(인기도가 더 높은 순)
class DescendingPopularity implements Comparator<ItemObject> {
    //o1 이 더 작으면 양수 반환 // 같으면 0 반환  //더 크면 음수 반환
    @Override
    public int compare(ItemObject o1, ItemObject o2) {
        return o1.getPopularity() < o2.getPopularity() ? 1 : o1.getPopularity()==o2.getPopularity() ? 0 : -1;
    }
}
//오름차순 정렬
class AscendingDistance implements Comparator<ItemObject>{
    @Override
    public int compare(ItemObject o1, ItemObject o2) {
        return o1.getDistance() > o2.getDistance() ? 1 : o1.getDistance()==o2.getDistance() ? 0 : -1;
    }
}
//최신순
class AscendingRecent implements Comparator<ItemObject>{
    @Override
    public int compare(ItemObject o1, ItemObject o2) {
        return o1.getRecent()> o2.getRecent() ? 1 : o1.getRecent()==o2.getRecent() ? 0 : -1;
    }
}
