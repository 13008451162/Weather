package com.example.weather;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.example.weather.TestTool.LogUtil;
import com.example.weather.Ui.Place.CityInquireFragment;
import com.example.weather.Ui.SearchActivity;
import com.example.weather.databinding.FragmentPlaceBinding;

public class MainActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;  //给予位置权限
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View button = findViewById((int) R.id.imageButton);

        //显示出操作栏
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // 动态请求位置权限
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }

//        //设置导航栏
//        setSupportActionBar(toolbar);
//
////得到这个导航栏
//        ActionBar actionBar = getSupportActionBar();
//
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);  //打开homeAsUp按钮
////            actionBar.setHomeAsUpIndicator(R.drawable.apple);   //为这个按钮设置图片
//        }

    }


    // 处理权限请求结果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 用户同意了位置权限，初始化地图等操作
            } else {
                // 用户拒绝了位置权限，你可以给予适当的提示或处理
                Log.e("Permission", "Location permission denied");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.status_bar, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int Id = item.getItemId();

        if(Id == R.id.Clothes){
            return true;
        } else if (Id == R.id.Country) {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
//            replaceFragment(new CityInquireFragment());
            return true;
        }else if(Id == R.id.Set){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 加载城市搜索的Fragment控件
     * @param fragment 需要加载的fragment
     */
    private void replaceFragment(Fragment fragment){

        //获取FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        //获取Fragment管理器
        FragmentTransaction transaction= fragmentManager.beginTransaction();

        // 将MyFragment添加到Activity
//        transaction.replace(R.id.LocationFragment,fragment);

        // 提交事务
        transaction.commit();
    }

}
