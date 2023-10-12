package com.example.weather;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.weather.Logic.WeatherDataInquireTool;
import com.example.weather.Logic.model.TwentyFourHourWeatherDatabase;
import com.example.weather.Logic.netWorkUtil.HourlyWeatherData;
import com.example.weather.Ui.SearchActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "haojinhui";

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;  //给予位置权限

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        WeatherDataInquireTool.dpHourWeatherDatabase = TwentyFourHourWeatherDatabase.getInstance(this);


        //显示出操作栏
        Toolbar toolbar = findViewById(R.id.toolbar);


//        toolbar.setOnClickListener((v) -> {
//            new Thread(() -> {
//                List<HourlyWeatherData.HourlyDTO> allData = WeatherDataInquireTool.dpHourWeatherDatabase.weatherDataDao().getAllData();
//                Log.e(TAG, "onCreate: " + allData.size());
//                for (HourlyWeatherData.HourlyDTO allDatum : allData) {
//                    Log.e(TAG, "onCreate: " + allDatum);
//                }
//            }).start();
//        });
        setSupportActionBar(toolbar);


        // 动态请求位置权限
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }

        //设置导航栏
        setSupportActionBar(toolbar);


        // 测试没问题
//        TwentyFourHourWeatherDataDao dao = TwentyFourHourWeatherDatabase.getInstance().weatherDataDao();
//        TwentyFourHourWeatherData data = new TwentyFourHourWeatherData();
//        new Thread(() -> {
//            dao.insertData(data, data, data);
//        }).start();
//        new Thread(() -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            Log.e(TAG, "onCreate: haojinhui: " + dao.getAllData());
//        }).start();
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
