package qkun.com.wanandroid.base.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import qkun.com.wanandroid.base.App;
import qkun.com.wanandroid.base.activity.BaseActivity;
import qkun.com.wanandroid.base.presenter.IPresenter;
import qkun.com.wanandroid.base.view.IView;
import qkun.com.wanandroid.di.component.DaggerFragmentComponent;
import qkun.com.wanandroid.di.component.FragmentComponent;
import qkun.com.wanandroid.di.mudule.FragmentModule;

public abstract class BaseFragment<T extends IPresenter> extends RxFragment implements IView {
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    protected BaseActivity mActivity;

    @Inject
    protected T mPresenter;
    private Unbinder mBind;

    //根布局视图
    private View mRootView;
    //视图是否已经初始化完毕
    private boolean isViewReady;
    //fragment是否处于可见状态
    private boolean isFragmentVisible;
    //是否已经初始化加载过
    protected boolean isLoaded;
    private FragmentComponent mFragmentComponent;

    protected abstract int getLayoutId();

    protected abstract void initInjector();

    protected abstract boolean isLazyLoad();//是否使用懒加载 (Fragment可见时才进行初始化操作(以下四个方法))

    protected abstract void initView(View view);

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (BaseActivity) activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragmentComponent();
        initInjector();
        attachView();
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
        }
        mBind = ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    /**
     * 当Activity中的onCreate方法执行完后调用
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewReady = true;
        if (!isLazyLoad() || isFragmentVisible) {
            init();
        }
    }

    /**
     * tab切换一次，执行一次setUserVisibleHint()方法
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isFragmentVisible = isVisibleToUser;
        //如果视图准备完毕且Fragment处于可见状态，则开始初始化操作
        if (isLazyLoad() && isViewReady && isFragmentVisible) {
            init();
        }
    }

    private void init() {
        if (!isLoaded) {
            isLoaded = true;
            initView(mRootView);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBind.unbind();
        detachView();
        isViewReady = false;
        isLoaded = false;
    }

    /** 初始化FragmentComponent*/
    private void initFragmentComponent() {
        mFragmentComponent = DaggerFragmentComponent.builder()
                .applicationComponent(((App) getActivity().getApplication()).getmApplicationComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
    }

    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    private void detachView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
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
}
