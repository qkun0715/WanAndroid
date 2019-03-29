package qkun.com.wanandroid.di.mudule;

import android.content.Context;



import dagger.Module;
import dagger.Provides;
import qkun.com.wanandroid.base.App;
import qkun.com.wanandroid.di.scope.ContextLife;
import qkun.com.wanandroid.di.scope.PerApp;

/**
 * Created by QKun on 2018/10/31.
 * application module 提供application   依赖提供方 类似于工厂类
 */

@Module
public class ApplicationModule {
    private App mApplication;

    public ApplicationModule(App application) {
        mApplication = application;
    }

    /**
     * Provides     标识生产对象的方法
     * PerApp       自定义单例
     * ContextLife  自定义别名方法
     * @return
     */
    @Provides
    @PerApp
    @ContextLife("Application")
    public Context provideApplicationContext(){
        return mApplication.getApplicationContext();
    }
}
