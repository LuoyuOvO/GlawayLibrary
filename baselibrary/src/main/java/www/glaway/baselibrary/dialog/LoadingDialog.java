package www.glaway.baselibrary.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import www.glaway.baselibrary.R;

/**
 * 加载dialog
 * <p>
 * style中设置加载背景是否有遮罩
 * <style name="LoadingDialog" parent="@android:style/Theme.Holo.Dialog.NoActionBar">
 * <item name="android:windowBackground">@android:color/transparent</item>
 * <item name="android:backgroundDimEnabled">false</item>
 * </style>
 * <p>
 * 列：
 * LoadingDialog loadingDialog = new LoadingDialog(this,"正在登录...",R.mipmap.ic_dialog_loading);
 * loadingDialog.show();
 */
public class LoadingDialog extends Dialog {

    private String mMessage;
    private Integer mImageId;
    private boolean mCancelable;
    private RotateAnimation mRotateAnimation;

    public LoadingDialog(@NonNull Context context) {
        this(context, R.style.LoadingDialog, "请等待...", R.mipmap.ic_dialog_loading, false);
    }

    public LoadingDialog(@NonNull Context context, String message) {
        this(context, R.style.LoadingDialog, message, R.mipmap.ic_dialog_loading, false);
    }

    public LoadingDialog(@NonNull Context context, String message, int imageId) {
        this(context, R.style.LoadingDialog, message, imageId, false);
    }

    public LoadingDialog(@NonNull Context context, int themeResId, String message, int imageId, boolean cancelable) {
        super(context, themeResId);
        mMessage = message;
        mImageId = imageId;
        mCancelable = cancelable;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.dialog_loading);
        // 设置窗口大小
        WindowManager windowManager = getWindow().getWindowManager();
        int screenWidth = windowManager.getDefaultDisplay().getWidth();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.3f;
        attributes.width = screenWidth / 4;
        attributes.height = attributes.width;
        getWindow().setAttributes(attributes);
        setCancelable(mCancelable);

        TextView tv_loading = findViewById(R.id.tv_loading);
        ImageView iv_loading = findViewById(R.id.iv_loading);
        tv_loading.setText(mMessage);

        iv_loading.setImageResource(mImageId);

        iv_loading.measure(0, 0);
        mRotateAnimation = new RotateAnimation(0, 360, iv_loading.getMeasuredWidth() / 2, iv_loading.getMeasuredHeight() / 2);
        mRotateAnimation.setInterpolator(new LinearInterpolator());
        mRotateAnimation.setDuration(1000);
        mRotateAnimation.setRepeatCount(-1);
        iv_loading.startAnimation(mRotateAnimation);
    }

    @Override
    public void dismiss() {
        mRotateAnimation.cancel();
        super.dismiss();
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 屏蔽返回键
            return mCancelable;
        }
        return super.onKeyDown(keyCode, event);
    }
}