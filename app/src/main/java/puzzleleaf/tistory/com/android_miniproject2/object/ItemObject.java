package puzzleleaf.tistory.com.android_miniproject2.object;


/**
 * Created by cmtyx on 2017-07-11.
 */

public class ItemObject {

    private String title="";
    private String contents="";
    private int distance = 0;
    private int popularity = 0;
    private int recent = 0;
    private String item="";
    private int res;
    private boolean isChecked;

    public ItemObject(String item, int res) {
        splitStr(item);
        this.res = res;
    }

    //데이터 분해하기
    private void splitStr(String menu) {
       this.title = menu.split("@")[0];
        this.contents =menu.split("@")[1];
        this.distance = Integer.valueOf(menu.split("@")[2]);
        this.popularity = Integer.valueOf(menu.split("@")[3]);
        this.recent = Integer.valueOf(menu.split("@")[4]);
    }

    public String getTitle(){
        return title;
    }

    public int getDistance() {
        return distance;
    }

    public int getPopularity() {
        return popularity;
    }

    public int getRecent() {
        return recent;
    }

    public String getContents() {
        return contents;
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
