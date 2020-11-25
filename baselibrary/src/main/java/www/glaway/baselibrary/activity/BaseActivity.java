package www.glaway.baselibrary.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    //二维码扫码使用
//    protected Typeface tfLight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        tfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
    }


    /**
     * 初始化界面
     */
    public abstract void  initView();

    /**
     * 初始化数据
     */
    public abstract void  initData();
}
