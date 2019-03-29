package qkun.com.wanandroid.di.mudule;


import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import qkun.com.wanandroid.di.scope.ContextLife;
import qkun.com.wanandroid.di.scope.PerActivity;

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @PerActivity
    @ContextLife("activity")
    public Context provideActivityContext() {
        return mActivity;
    }

    @Provides
    @PerActivity
    public Activity provideActivity(){
        return mActivity;
    }
}
