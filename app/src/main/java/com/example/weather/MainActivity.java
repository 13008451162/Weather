package com.example.weather;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
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
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.weather.Ui.Place.Fragment.CityInquireFragment;
import com.example.weather.Ui.Place.Fragment.CityWeatherFragment;
import com.example.weather.Ui.SearchActivity;
import com.example.weather.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;  //给予位置权限

    private static Activity activity;

    private static ActivityMainBinding binding;

    private static Typeface font;
    private static List<Fragment> fragmentList;
    private static ViewPager2FragmentAdapter viewPager2FragmentAdapter;

    public static Typeface getFont() {
        return font;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        activity = this;

        /*
         注意只要使用binding加载页面，就需要更改所有的findViewById
             Toolbar toolbar = findViewById(R.id.toolbar);
             setSupportActionBar(toolbar);
         */

        //显示出操作栏
        Toolbar toolbar = binding.toolbar; // 使用 binding 对象来查找 Toolbar
        setSupportActionBar(toolbar);


        // 动态请求位置权限
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }

        //判断网络情况
        if (!isNetworkConnected(this)) {
            Toast.makeText(this, "网络出错了,请检查网络链接", Toast.LENGTH_SHORT).show();
        }

        //设置导航栏
        setSupportActionBar(toolbar);

        font = Typeface.createFromAsset(getAssets(), "qweather-icons.ttf");//加载图标字体

//        WeatherReplaceFragment(new CityWeatherFragment());

        fragmentList = new ArrayList<>();
        fragmentList.add(new CityWeatherFragment());
        viewPager2FragmentAdapter = new ViewPager2FragmentAdapter(this, fragmentList);

        binding.ViewPager2.setAdapter(viewPager2FragmentAdapter);

        setContentView(binding.getRoot());

    }


    /**
     *  增加城市天气
     * @param fragments 需要添加的新的fragment
     */
    public static void addDataFragment(CityWeatherFragment...fragments){
        Collections.addAll(fragmentList,fragments);
        viewPager2FragmentAdapter.notifyDataSetChanged();

    }

    /**
     * 删除城市天气
     * @param position 需要删除的位置
     */
    public static void delteDataFragment(int position){
        fragmentList.remove(position);
        viewPager2FragmentAdapter.notifyDataSetChanged();
    }


    // 判断网络连接状态
    public static boolean isNetworkConnected(Context context) {
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

    private void WeatherReplaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.WeatherFragment, fragment);
        transaction.commit();
    }

    public static Activity getActivity() {
        return activity;
    }

    public static ActivityMainBinding getBinding() {
        return binding;
    }


    // 提供了FragmentStateAdapter 只需要继承它即可 不用继承RecycleView.Adapter
    static class ViewPager2FragmentAdapter extends FragmentStateAdapter {

        private List<Fragment> mFragmentList;

        public ViewPager2FragmentAdapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> mFragmentList) {
            super(fragmentActivity);
            this.mFragmentList = mFragmentList;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getItemCount() {
            return mFragmentList != null ? mFragmentList.size() : 0;
        }
    }
}
