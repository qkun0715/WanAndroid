package qkun.com.wanandroid.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class AbstractSimpleActivity extends RxAppCompatActivity implements View.OnClickListener{

    private Unbinder mBind;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mBind = ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != null && mBind != Unbinder.EMPTY) {
            mBind.unbind();
            mBind = null;
        }
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 获取布局id
     *
     * @return
     */
    protected abstract int getLayoutId();



    /**
     * 初始化布局
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initEventAndData();
}
