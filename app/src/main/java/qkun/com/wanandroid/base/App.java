package qkun.com.wanandroid.base;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;

import qkun.com.wanandroid.di.component.ApplicationComponent;
import qkun.com.wanandroid.di.component.DaggerApplicationComponent;
import qkun.com.wanandroid.di.mudule.ApplicationModule;

public class App extends Application {
    private static App mInstance;
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initApplicationComponent();
        Utils.init(this);
    }

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

    /**
     * 初始化ApplicationComponent
     */
    private void initApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getmApplicationComponent() {
        return mApplicationComponent;
    }


//    static {
//        //设置全局的Header构建器
//        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
//            @Override
//            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
//                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
//                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
//            }
//        });
//        //设置全局的Footer构建器
//        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
//            @Override
//            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
//                //指定为经典Footer，默认是 BallPulseFooter
//                return new ClassicsFooter(context).setDrawableSize(20);
//            }
//        });
//    }
}
