package com.example.weather;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;

public class MainActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    //获取初始状态的位置
    private LocationClient mLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View button = findViewById((int) R.id.button);

        try {
            mLocationClient = new LocationClient(getApplicationContext());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        // 请求位置权限
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 在按钮点击事件中执行地图显示等操作
                initMap();
            }
        });
    }

    // 初始化地图和定位
    private void initMap() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MapView mapView = new MapView(getApplicationContext());
                BaiduMap mBaiduMap = mapView.getMap();
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                mBaiduMap.setMyLocationEnabled(true);

                // 定位初始化
                LocationClientOption option = new LocationClientOption();
                option.setOpenGps(true);
                option.setCoorType("bd09ll");
                option.setScanSpan(1000);
                mLocationClient.setLocOption(option);

                MyLocationListener myLocationListener = new MyLocationListener(mapView,mBaiduMap);
                mLocationClient.registerLocationListener(myLocationListener);
                mLocationClient.start();



                setContentView(mapView);
            }
        });
    }

    // 处理权限请求结果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 用户同意了位置权限，初始化地图等操作
                initMap();
            } else {
                // 用户拒绝了位置权限，你可以给予适当的提示或处理
                Log.e("Permission", "Location permission denied");
            }
        }
    }
}
