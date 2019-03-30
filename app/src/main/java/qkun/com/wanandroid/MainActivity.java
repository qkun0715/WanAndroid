package qkun.com.wanandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatImageView;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.blankj.utilcode.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import qkun.com.wanandroid.base.activity.BaseActivity;
import qkun.com.wanandroid.constant.Constant;
import qkun.com.wanandroid.ui.home.HomePagerFragment;
import qkun.com.wanandroid.ui.knowledge.KnowledgeFragment;
import qkun.com.wanandroid.ui.navigation.NavigationFragment;
import qkun.com.wanandroid.ui.project.ProjectFragment;

public class MainActivity extends BaseActivity {


    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.ma_iv_index)
    AppCompatImageView maIvIndex;

    //fragments
    private HomePagerFragment mHomePagerFragment;
    private KnowledgeFragment mKnowledgeFragment;
    private NavigationFragment mNavigationFragment;
    private ProjectFragment mProjectFragment;
    /**
     * 上一个fragment角标
     */
    private int mLastFgIndex = -1;
    /**
     * 当前fragment所在的位置，默认第一个显示
     */
    private int mCurrentFgIndex = 0;

    private List<Fragment> mFragments;

    private int lastPosition;//上次fragment的位置
    private Fragment currentFragment;//要显示的Fragment
    private Fragment hideFragment;//要隐藏的Fragment

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("last_position", lastPosition);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lastPosition = savedInstanceState.getInt("last_position");//获取重建时的fragment的位置
        setSelectedFragment(lastPosition);//恢复销毁前显示的fragment
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initFragment();
        if (savedInstanceState == null) {
            setSelectedFragment(0);
        }
    }

    private void setSelectedFragment(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        currentFragment = fragmentManager.findFragmentByTag("fragment" + position);
        hideFragment = fragmentManager.findFragmentByTag("fragment" + lastPosition);
        if (position != lastPosition) {//如果位置不同
            if (hideFragment != null) {//如果要隐藏的fragment存在，则隐藏
                transaction.hide(hideFragment);
            }
            if (currentFragment == null) {//如果要显示的fragment不存在，则新加并提交事务
                currentFragment = mFragments.get(position);
                transaction.add(R.id.container, currentFragment, "fragment" + position);
            } else {//如果要显示的存在则直接显示
                transaction.show(currentFragment);
            }
        }

        if (position == lastPosition) {//如果位置相同
            if (currentFragment == null) {//如果fragment不存在(第一次启动应用的时候)
                currentFragment = mFragments.get(position);
                transaction.add(R.id.container, currentFragment, "fragment" + position);
            }//如果位置相同，且fragment存在，则不作任何操作
        }
        transaction.commit();//提交事务
        lastPosition = position;//更新要隐藏的fragment的位置
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(HomePagerFragment.newInstance(getString(R.string.home_pager)));
        mFragments.add(KnowledgeFragment.newInstance(getString(R.string.knowledge_hierarchy)));
        mFragments.add(NavigationFragment.newInstance(getString(R.string.navigation)));
        mFragments.add(ProjectFragment.newInstance(getString(R.string.project)));
    }


    @Override
    protected void initEventAndData() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.icon1:
                        ToastUtils.showShort("第一个");
                        setSelectedFragment(0);
                        break;
                    case R.id.icon2:
                        ToastUtils.showShort("第二个");
                        setSelectedFragment(1);
                        break;
                    case R.id.icon3:
                        ToastUtils.showShort("第三个");
                        setSelectedFragment(2);
                        break;
                    case R.id.icon4:
                        ToastUtils.showShort("第四个");
                        setSelectedFragment(3);
                        break;
                }
                return true;
            }
        });
        maIvIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("我是中间的，妈的");
            }
        });
    }


}
