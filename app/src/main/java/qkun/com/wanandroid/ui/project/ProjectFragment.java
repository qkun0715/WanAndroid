package qkun.com.wanandroid.ui.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import qkun.com.wanandroid.R;
import qkun.com.wanandroid.base.fragment.BaseFragment;
import qkun.com.wanandroid.bean.MarkerInfoUtil;

public class ProjectFragment extends BaseFragment {
    public static final String TAG = "latitude";
    public static final String TAG1 = "longitude";
    @BindView(R.id.map_view)
    MapView mapView;
    @BindView(R.id.iv_lock_me)
    ImageView ivLockMe;
    private double latitude;
    private double longitude;
    private BaiduMap mapViewMap;
    private ArrayList<MarkerInfoUtil> infos;

    public static ProjectFragment newInstance(double latitude, double longitude) {
        Bundle bundle = new Bundle();
        bundle.putDouble(TAG, latitude);
        bundle.putDouble(TAG1, longitude);
        ProjectFragment fragment = new ProjectFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected boolean isLazyLoad() {
        return false;
    }

    @Override
    protected void initView(View view) {
        //获取到 纬度 latitude 和 经度 longitude
        latitude = (double) ProjectFragment.this.getArguments().getDouble(TAG, 0d);
        longitude = (double) ProjectFragment.this.getArguments().getDouble(TAG1, 0d);
        LogUtils.d("纬度:" + latitude + " 经度：" + longitude);
        ToastUtils.showShort("纬度:" + latitude + " 经度：" + longitude);


        mapViewMap = mapView.getMap();
        // 不显示百度地图Logo
        mapView.removeViewAt(1);
        //百度地图设置是否显示我的位置
        mapViewMap.setMyLocationEnabled(true);


        setCenterMarker();
        setMarkers();
//        setMarkerInfo();
        setUserMapCenter();
    }

    private void setMarkerInfo() {
        infos = new ArrayList<MarkerInfoUtil>();
        infos.add(new MarkerInfoUtil(30.516832, 114.419203, "天津站", R.drawable.ic_home_pager, "天津站，俗称天津东站，隶属北京铁路局管辖"));
        infos.add(new MarkerInfoUtil(30.406832, 114.400203, "南开大学", R.drawable.ic_home_pager, "正式成立于1919年，是由严修、张伯苓秉承教育救国理念创办的综合性大学。"));
        infos.add(new MarkerInfoUtil(30.466832, 114.429203, "天津水上公园", R.drawable.ic_home_pager, "天津水上公园原称青龙潭，1951年7月1日正式对游客开放，有北方的小西子之称。"));
        addOverlay(infos);

    }

    private void addOverlay(List<MarkerInfoUtil> infos2) {
        //清空地图
        mapViewMap.clear();
        //创建marker的显示图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.icon_markers);
        LatLng latLng = null;
        Marker marker;
        OverlayOptions options;
        for (MarkerInfoUtil info : infos) {
            //获取经纬度
            latLng = new LatLng(info.getLatitude(), info.getLongitude());
            //设置marker
            options = new MarkerOptions()
                    .position(latLng)//设置位置
                    .icon(bitmap)//设置图标样式
                    .zIndex(9) // 设置marker所在层级
                    .draggable(true); // 设置手势拖拽;
            //添加marker
            marker = (Marker) mapViewMap.addOverlay(options);
            //使用marker携带info信息，当点击事件的时候可以通过marker获得info信息
            Bundle bundle = new Bundle();
            //info必须实现序列化接口
            bundle.putSerializable("info", info);
            marker.setExtraInfo(bundle);
        }
        setCenterMarker();
        //将地图显示在最后一个marker的位置
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
        mapViewMap.setMapStatus(msu);
    }


    /**
     * 添加多个markers
     */
    private void setMarkers() {
//        mapViewMap.clear();
        List<OverlayOptions> optionsList = new ArrayList<OverlayOptions>();
        //创建marker的显示图标
//        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.icon_markers);
        LatLng latLng = null;
        Marker marker;
//        OverlayOptions options;
//        for (int i = 5; i < 10; i++) {
//            latLng = new LatLng((29.972587-(10*i)/100),113.942558-(10*i)/100);
//            //设置marker
//            options = new MarkerOptions()
//                    .position(latLng)//设置位置
//                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_markers));
////                    .zIndex(9); // 设置marker所在层级
//            optionsList.add(options);
        //添加marker
//                mapViewMap.addOverlay(options);
//            marker = (Marker) mapViewMap.addOverlay(options);
//            //使用marker携带info信息，当点击事件的时候可以通过marker获得info信息
//            Bundle bundle = new Bundle();
//            //info必须实现序列化接口
//            bundle.putSerializable("info", info);
//            marker.setExtraInfo(bundle);
//        }
//        LatLng center = new LatLng(latitude, longitude);
//        BitmapDescriptor centerBitmap = BitmapDescriptorFactory
//                .fromResource(R.mipmap.icon_marker);
////构建MarkerOption，用于在地图上添加Marker
//        OverlayOptions centerOption = new MarkerOptions()
//                .position(center)
//                .icon(centerBitmap);
//        optionsList.add(centerOption);
//        mapViewMap.addOverlays(optionsList);

//        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
//        mapViewMap.setMapStatus(msu);
        /*-----------------------------------------------*/
        //创建OverlayOptions的集合
        List<OverlayOptions> options = new ArrayList<OverlayOptions>();
//构造大量坐标数据
        LatLng point1 = new LatLng(30.516832, 114.419203);
        LatLng point2 = new LatLng(30.406832, 114.400203);
        LatLng point3 = new LatLng(30.466832, 114.429203);

//创建OverlayOptions属性
        OverlayOptions option1 = new MarkerOptions()
                .position(point1)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_markers));
        OverlayOptions option2 = new MarkerOptions()
                .position(point2)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_markers));
        OverlayOptions option3 = new MarkerOptions()
                .position(point3)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_markers));
//将OverlayOptions添加到list
        options.add(option1);
        options.add(option2);
        options.add(option3);
//在地图上批量添加
        mapViewMap.addOverlays(options);

        //添加marker点击事件的监听
        mapViewMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                ToastUtils.showShort("我点丶");
                return true;
            }
        });
    }

    /**
     * 添加marker
     */
    private void setCenterMarker() {
        LatLng point = new LatLng(latitude, longitude);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.icon_marker);
//构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
//在地图上添加Marker，并显示
        mapViewMap.addOverlay(option);
    }

    /**
     * 设置中心点
     */
    private void setUserMapCenter() {
        LatLng cenpt = new LatLng(latitude, longitude);
//定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(cenpt)
                .zoom(13)
                .build();
//定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
//改变地图状态
        mapViewMap.setMapStatus(mMapStatusUpdate);
    }


    @OnClick(R.id.iv_lock_me)
    public void onViewClicked() {
        setUserMapCenter();
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        mapView.onDestroy();
        super.onDestroyView();
    }
}
