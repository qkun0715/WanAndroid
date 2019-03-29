package qkun.com.wanandroid.di.mudule;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;



import dagger.Module;
import dagger.Provides;
import qkun.com.wanandroid.di.scope.ContextLife;
import qkun.com.wanandroid.di.scope.PerFragment;

/**
 * Created by QKun on 2018/10/31.
 */
@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @PerFragment
    @ContextLife("Activity")
    public Context provideActivityContext() {
        return mFragment.getActivity();
    }

    @Provides
    @PerFragment
    public Activity provideActivity() {
        return mFragment.getActivity();
    }

    @Provides
    @PerFragment
    public Fragment provideFragment() {
        return mFragment;
    }
}
