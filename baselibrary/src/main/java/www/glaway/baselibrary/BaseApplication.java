package www.glaway.baselibrary;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

public abstract class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化SQLiteStudio
        SQLiteStudioService.instance().start(this);
        //初始化logger日志
        Logger.addLogAdapter(new AndroidLogAdapter());
        //初始化文件压缩/解压
        ZXingLibrary.initDisplayOpinion(this);
        //配置数据库
        initGreenDao();

    }


    public abstract  void initGreenDao();
}
