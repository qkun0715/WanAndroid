package qkun.com.wanandroid.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatImageView;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import qkun.com.wanandroid.R;
import qkun.com.wanandroid.base.activity.BaseActivity;
import qkun.com.wanandroid.base.fragment.BaseFragment;
import qkun.com.wanandroid.ui.home.HomePagerFragment;
import qkun.com.wanandroid.ui.knowledge.KnowledgeFragment;
import qkun.com.wanandroid.ui.navigation.NavigationFragment;
import qkun.com.wanandroid.ui.project.ProjectFragment;

public class SecondActivity extends BaseActivity {
    @BindView(R.id.bottom_navigation_view)
    BottomNavigationBar mBottomNavigation;
    @BindView(R.id.iv_circle)
    AppCompatImageView ivCircle;

    List<BaseFragment> mFragments = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_second;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initFragment();
        setDefaultFragment();
        initBottomNavigation();
    }


    private void initFragment() {
        mFragments.add(HomePagerFragment.newInstance(getString(R.string.home_pager)));
        mFragments.add(KnowledgeFragment.newInstance(getString(R.string.knowledge_hierarchy)));
        mFragments.add(NavigationFragment.newInstance(getString(R.string.navigation)));
        mFragments.add(ProjectFragment.newInstance(getString(R.string.project)));
    }

    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, mFragments.get(0));
        ft.commit();
    }

    private void initBottomNavigation() {
        mBottomNavigation.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigation.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigation.setActiveColor(R.color.colorAccent)
                .addItem(new BottomNavigationItem(R.drawable.ic_home_pager, getString(R.string.home_pager)))
                .addItem(new BottomNavigationItem(R.drawable.ic_knowledge_hierarchy, getString(R.string.knowledge_hierarchy)))
//                .addItem(new BottomNavigationItem(R.drawable.ic_wechat_black_24dp, getString(R.string.wechat)))
//                .addItem(new BottomNavigationItem(null, null))
                .addItem(new BottomNavigationItem(R.drawable.ic_navigation, getString(R.string.navigation)))
                .addItem(new BottomNavigationItem(R.drawable.ic_project, getString(R.string.project)))
                .setFirstSelectedPosition(0)
                .initialise();
        mBottomNavigation.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                if (mFragments != null) {
                    if (position <= mFragments.size()) {
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        Fragment currentFragment = fm.findFragmentById(R.id.container);
                        BaseFragment nextFragment = mFragments.get(position);
                        if (nextFragment.isAdded()) {
                            ft.hide(currentFragment).show(nextFragment);
                        } else {
                            ft.hide(currentFragment).add(R.id.container, nextFragment);
                            if (nextFragment.isHidden()) {
                                ft.show(nextFragment);
                            }
                        }
                        ft.commitAllowingStateLoss();

                    }
                }
            }

            @Override
            public void onTabUnselected(int position) {
                if (mFragments != null) {
                    if (position < mFragments.size()) {
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        Fragment nextFragment = mFragments.get(position);
                        ft.hide(nextFragment);
                        ft.commitAllowingStateLoss();
                    }
                }
            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    @Override
    protected void initEventAndData() {

    }


}
