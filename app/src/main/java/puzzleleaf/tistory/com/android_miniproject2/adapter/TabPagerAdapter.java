package puzzleleaf.tistory.com.android_miniproject2.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import puzzleleaf.tistory.com.android_miniproject2.fragment.Menu;

//FragmentPagerAdapter는 화면을 슬라이딩으로 전환할 때 한 번 생성된(화면에 보인) Fragment를 계속 메모리상 가지고 있습니다. 이전 Fragment로 슬라이딩을 해서 돌아간다고 하면 이전에 생성된 Fragment로 돌아가는 겁니다.
//        FragmentStatePagerAdapter는 화면이 전환될 때 이전(화면에서 보이지 않는) Fragment는 메모리상 제거(destroy)가 됩니다. Adapter의 Fragment가 많거나 갯수를 알수없을 때 메모리관련 이슈를 위해 사용하는것이 좋다고 하네요

public class TabPagerAdapter extends FragmentPagerAdapter {

    // Count number of tabs
    private int tabCount;
    private int viewFlag;

    public TabPagerAdapter(FragmentManager fm, int tabCount, int viewFlag) {
        super(fm);
        this.tabCount = tabCount;
        this.viewFlag = viewFlag;
    }


    //ViewPager 새로고침시 필요한 부분들
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
    //ViewPager 새로고침 End

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        Log.d("qwe",String.valueOf(viewFlag));
        switch (position) {

            case 0:
                fragment = new Menu();
                break;
            case 1:
                fragment = new Menu();
                break;
            case 2:
                fragment = new Menu();
                break;
            default:
                fragment =new Menu();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
