package qkun.com.wanandroid.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import qkun.com.wanandroid.base.App;
import qkun.com.wanandroid.base.presenter.IPresenter;
import qkun.com.wanandroid.base.view.IView;
import qkun.com.wanandroid.constant.Constant;
import qkun.com.wanandroid.constant.LoadType;
import qkun.com.wanandroid.di.component.ActivityComponent;
import qkun.com.wanandroid.di.component.DaggerActivityComponent;
import qkun.com.wanandroid.di.mudule.ActivityModule;

public abstract class BaseActivity<T extends IPresenter> extends RxAppCompatActivity implements IView,View.OnClickListener {

    @Nullable
    @Inject
    protected T mPresenter;

    private Unbinder mBind;
    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityComponent();
        setContentView(getLayoutId());
        //状态栏
        ImmersionBar.with(this).init();
        initInjector();
        mBind = ButterKnife.bind(this);
        attachView();
        initView();
        initEventAndData();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != null && mBind != Unbinder.EMPTY) {
            mBind.unbind();
            mBind = null;
        }
        detachView();
    }

    /**present 吸附 view */
    private void attachView() {
        if (mPresenter!=null){
            mPresenter.attachView(this);
        }
    }

    /**present 分离 view */
    private void detachView() {
        if (mPresenter!=null){
            mPresenter.detachView();
        }
    }

    private void initActivityComponent() {
        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((App) getApplication()).getmApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showSuccess(String message) {

    }

    @Override
    public void showFailed(String message) {

    }

    @Override
    public void showNoNet() {

    }

    @Override
    public void onRetry() {

    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.bindToLifecycle();
    }

    @Override
    public void NoData() {

    }


    protected void setLoadDataResult(BaseQuickAdapter adapter, SmartRefreshLayout refreshLayout, List list,
                                     @LoadType.checker int loadType) {
        switch (loadType) {
            case LoadType.TYPE_REFRESH_SUCCESS:
                adapter.setNewData(list);
                refreshLayout.finishRefresh();
                break;
            case LoadType.TYPE_REFRESH_ERROR:
                refreshLayout.finishRefresh();
                break;
            case LoadType.TYPE_LOAD_MORE_SUCCESS:
                if (list != null) {
                    adapter.addData(list);
                    refreshLayout.finishLoadMore();
                }
                break;
            case LoadType.TYPE_LOAD_MORE_ERROR:
                break;
            default:
                break;
        }
        if (list == null || list.isEmpty() || list.size() < Constant.PAGE_SIZE) {
            adapter.loadMoreEnd(false);
        } else {
            adapter.loadMoreComplete();
        }
    }

    /**
     * 获取布局id
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 进行dagger注入
     */
    protected abstract void initInjector();

    /**
     * 初始化布局
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initEventAndData();
}
