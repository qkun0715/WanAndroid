package qkun.com.wanandroid.di.component;


import android.app.Activity;
import android.content.Context;

import dagger.Component;
import qkun.com.wanandroid.di.mudule.FragmentModule;
import qkun.com.wanandroid.di.scope.ContextLife;
import qkun.com.wanandroid.di.scope.PerFragment;

@PerFragment
@Component(dependencies = ApplicationComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {
    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    //进行 inject 注入
}
