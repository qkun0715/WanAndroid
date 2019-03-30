package qkun.com.wanandroid.http;

import android.support.annotation.CallSuper;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.NetworkUtils;

import io.reactivex.observers.ResourceObserver;
import qkun.com.wanandroid.R;
import qkun.com.wanandroid.base.App;
import qkun.com.wanandroid.base.view.IView;
import qkun.com.wanandroid.http.exception.ServerException;
import retrofit2.HttpException;

public abstract class BaseObserver<T> extends ResourceObserver<BaseResponse<T>> {

    private static final String TAG = "BaseObserver";

    private IView mView;
    private String mErrorMsg;
    private boolean isShowStatusView = true;

    protected BaseObserver(IView view) {
        this.mView = view;
    }

    protected BaseObserver(IView view, String errorMsg) {
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }

    protected BaseObserver(IView view, boolean isShowStatusView) {
        this.mView = view;
        this.isShowStatusView = isShowStatusView;
    }

    protected BaseObserver(IView view, String errorMsg, boolean isShowStatusView) {
        this.mView = view;
        this.mErrorMsg = errorMsg;
        this.isShowStatusView = isShowStatusView;
    }

    public abstract void onSuccess(T t);

    @CallSuper
    public void onFailure(int code, String message) {
        mView.showErrorMsg(message);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart");
        if (isShowStatusView) {
            mView.showLoading();
        }
    }

    @Override
    public void onNext(BaseResponse<T> tBaseResponse) {
        if (tBaseResponse.isSuccess()) {
            Log.d(TAG, "onSuccess");
            if (isShowStatusView) {
                mView.hideLoading();
                mView.showContent();
            }
            onSuccess(tBaseResponse.getData());
        } else {
            Log.d(TAG, "onFailure");
            if (isShowStatusView) {
                mView.hideLoading();
                mView.showContent();
            }
            onFailure(tBaseResponse.getErrorCode(), tBaseResponse.getErrorMsg());
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.d(TAG, "onError");
        if (mView == null) {
            return;
        }
        if (isShowStatusView) {
            mView.hideLoading();
        }
        if (e instanceof HttpException) {
            mView.showErrorMsg(App.getContext().getString(R.string.http_error));
            if (isShowStatusView) {
                mView.showNoNetwork();
            }
        } else if (e instanceof ServerException) {
            mView.showErrorMsg(e.toString());
            if (isShowStatusView) {
                mView.showError();
            }
        } else {
            if (!TextUtils.isEmpty(mErrorMsg)) {
                mView.showErrorMsg(mErrorMsg);
            }
            if (isShowStatusView) {
                mView.showError();
            }
            Log.e(TAG, e.toString());
        }
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete");
        if (mView == null) {
            return;
        }
        if (!NetworkUtils.isConnected()) {
            mView.showErrorMsg(App.getContext().getString(R.string.http_error));
        }
    }
}
