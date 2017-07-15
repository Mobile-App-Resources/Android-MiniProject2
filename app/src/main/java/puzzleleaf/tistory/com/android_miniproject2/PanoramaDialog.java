package puzzleleaf.tistory.com.android_miniproject2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gjiazhe.panoramaimageview.GyroscopeObserver;
import com.gjiazhe.panoramaimageview.PanoramaImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cmtyx on 2017-07-13.
 */

public class PanoramaDialog extends AppCompatActivity {

    @BindView(R.id.panoramaImage) PanoramaImageView panoramaImageView;
    GyroscopeObserver gyroscopeObserver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_item);
        overridePendingTransition(0,0); //시작 애니메이션 x
        ButterKnife.bind(this);
        panoramaInit(getIntent().getIntExtra("res",0));
    }

    private void panoramaInit(int res){
        gyroscopeObserver = new GyroscopeObserver();
        gyroscopeObserver.setMaxRotateRadian(Math.PI / 9);
        if(res==0){
            Toast.makeText(getApplicationContext(),"이미지 로딩 오류",Toast.LENGTH_SHORT).show();
            return;
        }
       // Glide.with(getApplicationContext()).load(res).into(panoramaImageView); 왜 Glide 적용이 안될까요??
        panoramaImageView.setImageResource(res);
        panoramaImageView.setGyroscopeObserver(gyroscopeObserver);
    }
    @Override
    protected void onResume() {
        super.onResume();
        gyroscopeObserver.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0,0); //종료 애니메이션 x
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gyroscopeObserver.unregister();
    }

}
