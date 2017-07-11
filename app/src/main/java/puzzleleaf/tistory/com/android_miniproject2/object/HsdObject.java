package puzzleleaf.tistory.com.android_miniproject2.object;

import android.util.Log;

/**
 * Created by cmtyx on 2017-07-11.
 */

public class HsdObject {

    private String title="";
    private int price=0;
    private String menu="";
    private int res;
    private boolean isChecked;

    public HsdObject(String menu, int res) {
        splitStr(menu);
        this.res = res;
    }

    private void splitStr(String menu) {
       this.title = menu.split("@")[0];
        this.price = Integer.parseInt(menu.split("@")[1]);
        Log.d("qwe",title);
        Log.d("qwe",String.valueOf(price));
    }

    public String getTitle(){
        return title;
    }

    public int getPrice(){
        return price;
    }

    public int getRes() {
        return res;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
