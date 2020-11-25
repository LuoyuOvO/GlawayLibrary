package www.glaway.glawaylibrary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.glaway.baselibrary.activity.BaseActivity;
import www.glaway.glawaylibrary.R;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.edit_username)
    EditText editUsername;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.img_icon)
    ImageView imgIcon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //状态栏不可见
//        BarUtils.setStatusBarVisibility(this,false);
        //状态栏是否浅色
//        BarUtils.setStatusBarLightMode(this,false);
        //设置状态栏颜色
//        BarUtils.setStatusBarColor(this, Color.RED);
        //透明状态栏
//        BarUtils.transparentStatusBar(this);
        //设置导航栏是否可见
//        BarUtils.setNavBarVisibility(this,false);
        //设置导航栏颜色
//        BarUtils.setNavBarColor(this, Color.RED);
        //自定义ActionBar，百度都有
//        getSupportActionBar().setCustomView(R.layout.activity_login);
//        getSupportActionBar().setDisplayShowCustomEnabled(true);
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP|ActionBar.DISPLAY_SHOW_CUSTOM);
        //为控件添加点击缩放效果
//        ClickUtils.applyPressedViewScale(imgIcon);
        //应用点击后对视图改变透明度
//        ClickUtils.applyPressedViewAlpha(imgIcon);
        //设置防抖点击
//        ClickUtils.applySingleDebouncing(imgIcon,v -> {
//            Logger.e("点击和头像");
//        });
        //设置多个控件一起防抖点击
//        View[] views = new View[]{imgIcon,btnSubmit};
//        ClickUtils.applyGlobalDebouncing(views,5000,v -> {
//            Logger.e("点击和头像");
//        });
        //点击打开二维码扫码界面
//        ClickUtils.applyPressedViewScale(imgIcon);
//        ClickUtils.applySingleDebouncing(imgIcon, v -> {
//            PermissionUtil.applyPermissions(PermissionUtil.PERMISSIONS_SCANQRCODE,call -> {
//                if (call){
//                    startActivity(new Intent(LoginActivity.this, QrcodeCaptureActivity.class));
//                }
//            });
//        });
        //等待dialgo
//        LoadingDialog loadingDialog = new LoadingDialog(this);
//        loadingDialog.show();

    }

    public void initView() {

    }

    public void initData() {

    }

    @OnClick({R.id.img_icon, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_icon:
                //时间选择
//                PickerShow pickerShow = new PickerShow();
//                pickerShow.showPickerDate(this,date -> {
//                    ToastUtil.showWarning(date+"");
//                });
                break;
            case R.id.btn_submit:
                startActivity(new Intent(LoginActivity.this, EchartActivity.class));
                break;
        }
    }
}
