package qkun.com.wanandroid;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatImageView;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.blankj.utilcode.util.ToastUtils;

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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView() {
        showFragment(mCurrentFgIndex);
    }


    @Override
    protected void initEventAndData() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.icon1:
                        ToastUtils.showShort("第一个");
                        showFragment(Constant.TYPE_HOME_PAGER);
                        break;
                    case R.id.icon2:
                        ToastUtils.showShort("第二个");
                        showFragment(Constant.TYPE_KNOWLEDGE);
                        break;
                    case R.id.icon3:
                        ToastUtils.showShort("第三个");
                        showFragment(Constant.TYPE_NAVIGATION);
                        break;
                    case R.id.icon4:
                        ToastUtils.showShort("第四个");
                        showFragment(Constant.TYPE_PROJECT);
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

    private void showFragment(int index) {
        mCurrentFgIndex = index;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        mLastFgIndex = index;
        switch (index) {
            case Constant.TYPE_HOME_PAGER:
                if (mHomePagerFragment == null) {
                    mHomePagerFragment = HomePagerFragment.newInstance(getString(R.string.home_pager));
                    transaction.add(R.id.container, mHomePagerFragment);
                }
                transaction.show(mHomePagerFragment);
                break;
            case Constant.TYPE_KNOWLEDGE:
                if (mKnowledgeFragment == null) {
                    mKnowledgeFragment = KnowledgeFragment.newInstance(getString(R.string.knowledge_hierarchy));
                    transaction.add(R.id.container, mKnowledgeFragment);
                }
                transaction.show(mKnowledgeFragment);
                break;
            case Constant.TYPE_NAVIGATION:
                if (mNavigationFragment == null) {
                    mNavigationFragment = NavigationFragment.newInstance(getString(R.string.navigation));
                    transaction.add(R.id.container, mNavigationFragment);
                }
                transaction.show(mNavigationFragment);
                break;
            case Constant.TYPE_PROJECT:
                if (mProjectFragment == null) {
                    mProjectFragment = ProjectFragment.newInstance(getString(R.string.project));
                    transaction.add(R.id.container, mProjectFragment);
                }
                transaction.show(mProjectFragment);
                break;
            default:
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    private void hideFragment(FragmentTransaction transaction) {
        switch (mLastFgIndex) {
            case Constant.TYPE_HOME_PAGER:
                if (mHomePagerFragment != null) {
                    transaction.hide(mHomePagerFragment);
                }
                break;
            case Constant.TYPE_KNOWLEDGE:
                if (mKnowledgeFragment != null) {
                    transaction.hide(mKnowledgeFragment);
                }
                break;
            case Constant.TYPE_NAVIGATION:
                if (mNavigationFragment != null) {
                    transaction.hide(mNavigationFragment);
                }
                break;
            case Constant.TYPE_PROJECT:
                if (mProjectFragment != null) {
                    transaction.hide(mProjectFragment);
                }
                break;
            default:
                break;

        }
    }


}
