package qkun.com.wanandroid.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import qkun.com.wanandroid.R;

/**
 * Created by QKun on 2018/11/7.
 */
public class GlideUtils {
    public static void load(Context context, String url, ImageView imageView) {
        RequestOptions options =new RequestOptions()
                .placeholder(R.mipmap.xyjy)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .error(R.mipmap.xyjy);
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

}
