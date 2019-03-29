package qkun.com.wanandroid.di.component;


import android.app.Activity;
import android.content.Context;

import dagger.Component;
import qkun.com.wanandroid.di.mudule.ActivityModule;
import qkun.com.wanandroid.di.scope.ContextLife;
import qkun.com.wanandroid.di.scope.PerActivity;

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    @ContextLife("activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    //只要获取ActivityActivityComponent  就可以在下面的类中依赖成功
}
