package www.glaway.baselibrary;

import android.app.Application;

import com.blankj.utilcode.util.ActivityUtils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.yc.toollib.crash.CrashHandler;
import com.yc.toollib.crash.CrashListener;
import com.yc.toollib.crash.CrashToolUtils;


public abstract class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化logger日志
        Logger.addLogAdapter(new AndroidLogAdapter());
        //初始化文件压缩/解压
        ZXingLibrary.initDisplayOpinion(this);
        //初始化奔溃信息
        initCrash();
        //配置数据库
        initGreenDao();

    }

    /**
     * 初始化崩溃捕获
     */
    private void initCrash() {
        CrashHandler.getInstance().init(this, new CrashListener() {
            /**
             * 重启app
             */
            @Override
            public void againStartApp() {
//                CrashToolUtils.reStartApp1(BaseApplication.this,1000);
                //CrashToolUtils.reStartApp2(App.this,1000, MainActivity.class);
                CrashToolUtils.reStartApp3(ActivityUtils.getTopActivity());
            }

            /**
             * 自定义上传crash，支持开发者上传自己捕获的crash数据
             * @param ex                        ex
             */
            @Override
            public void recordException(Throwable ex) {
                //自定义上传crash，支持开发者上传自己捕获的crash数据
                //StatService.recordException(getApplication(), ex);
            }
        });
    }


    public abstract  void initGreenDao();
}
