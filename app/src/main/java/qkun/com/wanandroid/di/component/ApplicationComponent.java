package qkun.com.wanandroid.di.component;

import android.content.Context;

import dagger.Component;
import qkun.com.wanandroid.di.mudule.ApplicationModule;
import qkun.com.wanandroid.di.scope.ContextLife;
import qkun.com.wanandroid.di.scope.PerApp;


@PerApp
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ContextLife("Application")
    Context getApplication();
}
