package com.example.weather;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.weather.Ui.SearchActivity;

public class MainActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;  //给予位置权限

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //显示出操作栏
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        Log.d("TAG","12");
        // 动态请求位置权限
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("TAG","1234");
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }

        //判断网络情况
        if(!isNetworkConnected(this)){
            Toast.makeText(this,"网络出错了,请检查网络链接", Toast.LENGTH_SHORT).show();
        }

        //设置导航栏
        setSupportActionBar(toolbar);


    }

    // 判断网络连接状态
    public boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                return true; // 有可用网络连接
            }
        }
        return false; // 没有可用网络连接
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
                Toast.makeText(this, "定位出现问题了，请检查定位是否开启", Toast.LENGTH_SHORT).show();

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

        if (Id == R.id.Clothes) {
            return true;
        } else if (Id == R.id.Country) {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
            return true;
        } else if (Id == R.id.Set) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 加载城市搜索的Fragment控件
     *
     * @param fragment 需要加载的fragment
     */
    private void replaceFragment(Fragment fragment) {

        //获取FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        //获取Fragment管理器
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // 将MyFragment添加到Activity
//        transaction.replace(R.id.LocationFragment,fragment);

        // 提交事务
        transaction.commit();
    }

}
