package com.example.huaian.weather.view.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huaian.weather.R;
import com.example.huaian.weather.base.BaseQuery;
import com.example.huaian.weather.db.City;
import com.example.huaian.weather.db.County;
import com.example.huaian.weather.db.Province;
import com.example.huaian.weather.util.ConstantUtil;
import com.example.huaian.weather.util.HttpUtil;
import com.example.huaian.weather.util.LogUtil;
import com.example.huaian.weather.util.Utility;
import com.example.huaian.weather.view.activity.HomeActivity;
import com.example.huaian.weather.view.activity.WeatherActivity;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemSelected;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Description: 遍历省市县数据
 * Created by HuaiAn
 * on 2018/11/29 09:32
 */
public class ChooseFragment extends Fragment implements BaseQuery.Location {
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.back_button)
    Button backButton;
    @BindView(R.id.list_view)
    ListView listView;
    Unbinder unbinder;

    private static final String TAG = ChooseFragment.class.getSimpleName();

    public static final int LEVEL_PROVINCE = 0;

    public static final int LEVEL_CITY = 1;

    public static final int LEVEL_COUNTY = 2;

    /**
     * ProgressDialog was deprecated in API level 26.
     * Instead of using this class, you should use a progress indicator like ProgressBar,
     */
    private ProgressDialog progressDialog;

    private ArrayAdapter<String> adapter;

    private List<String> dataList = new ArrayList();

    /**
     * 省列表
     */
    private List<Province> provinceList;

    /**
     * 市列表
     */
    private List<City> cityList;

    /**
     * 县列表
     */
    private List<County> countyList;

    /**
     * 选中的省份
     */
    private Province selectedProvince;

    /**
     * 选中的城市
     */
    private City selectedCity;

    /**
     * 选中的县镇
     */
    private County selectedCounty;

    /**
     * 当前选中的级别
     */
    private int currentLevel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_area, container, false);
        this.unbinder = ButterKnife.bind(this, view);
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 默认查询省份
        queryProvinces();
    }

    @OnClick({R.id.back_button})
    public void onClicked() {
        Log.e(TAG, "onClicked: 返回");
        if (currentLevel == LEVEL_COUNTY) {
            queryCities();
        } else if (currentLevel == LEVEL_CITY) {
            queryProvinces();
        }
    }

    @OnItemClick(R.id.list_view)
    void onItemSelected(int pos) {
        if (currentLevel == LEVEL_PROVINCE) {
            selectedProvince = provinceList.get(pos);
            queryCities();
        } else if (currentLevel == LEVEL_CITY) {
            selectedCity = cityList.get(pos);
            queryCounties();
        } else if (currentLevel == LEVEL_COUNTY) {
            String weatherId = countyList.get(pos).getWeatherId();
            /**
             * instanceof 判断一个对象是否属于某个类
             * 在 HomeActivty中则正常进行
             * 若是WeatherActivity 则关闭策划栏，显示下拉刷新，请求天气信息
             */
            if (getActivity() instanceof HomeActivity) {
                Intent intent = new Intent(getActivity(), WeatherActivity.class);
                intent.putExtra("weather_id", weatherId);
                startActivity(intent);
                getActivity().finish();
            } else if (getActivity() instanceof WeatherActivity){
                WeatherActivity activity = (WeatherActivity) getActivity();
                activity.drawerLayout.closeDrawers();
                activity.swipeRefresh.setRefreshing(true);
                activity.requestWeather(weatherId);
            }
        }
    }

    /**
     * 优先从数据库中查询省份，如没有再到服务器上查询数据
     */
    @Override
    public void queryProvinces() {
        titleText.setText("中国");
        //返回不可见
        backButton.setVisibility(View.GONE);
        //  获取所有数据并填充到List
        provinceList = LitePal.findAll(Province.class);
        if (provinceList.size() > 0) {
            dataList.clear();
            for (Province province : provinceList) {
                dataList.add(province.getProvinceName());
                LogUtil.d(province.getProvinceName());
            }
            //  更新listView
//            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_PROVINCE;
        } else {
            queryFormServer(ConstantUtil.ADRESS, "province");
        }
    }

    /**
     * 优先从数据库中查询城市，如没有再到服务器上查询数据
     */
    @Override
    public void queryCities() {
        Log.d(TAG, "queryCities: ");
        titleText.setText(selectedProvince.getProvinceName());
        backButton.setVisibility(View.VISIBLE);
        //  查询 selectedProvince.getId() 的字段，并填充到 City 中
        cityList = LitePal.where("provinceid = ?",
                String.valueOf(selectedProvince.getId())).find(City.class);
        if (cityList.size() > 0) {
            dataList.clear();
            for (City city : cityList) {
                dataList.add(city.getCityName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_CITY;
        } else {
            int provinceCode = selectedProvince.getProvinceCode();
            String address = ConstantUtil.ADRESS + "/" + provinceCode;
            Log.d(TAG, "queryCities: " + address);
            queryFormServer(address, "city");
        }
    }

    /**
     * 优先从数据库中查询乡镇，如没有再到服务器上查询数据
     */
    @Override
    public void queryCounties() {
//        Log.d(TAG, "queryCounties: ");
        titleText.setText(selectedCity.getCityName());
        backButton.setVisibility(View.VISIBLE);
        countyList = LitePal.where("cityid = ?",
                String.valueOf(selectedCity.getId())).find(County.class);
        if (countyList.size() > 0) {
            dataList.clear();
            for (County county : countyList) {
                dataList.add(county.getCountyName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_COUNTY;
        } else {
            int provinceCode = selectedProvince.getProvinceCode();
            int cityCode = selectedCity.getCityCode();
            String address = ConstantUtil.ADRESS + "/" + provinceCode + "/" + cityCode;
            Log.d(TAG, "queryCounties: " + address);
            queryFormServer(address, "county");
        }
    }

    /**
     * 根据传入地址和类型在服务器上查询省市县数据
     *
     * @param address 地址
     * @param type    类型
     */
    @Override
    public void queryFormServer(String address, String type) {
//        Log.d(TAG, "queryFormServer: address：" + address + "type" + type);
        showProgressDialog();
        HttpUtil.HttpOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //  切换主线程
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        closeProgressDialog();
                        LogUtil.e("run: Fail to download the information！" + e.getLocalizedMessage());
                        //Toast.makeText(getActivity(), "加载失败!", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                Log.d(TAG, "onResponse: " + responseText);
                boolean result = false;
                if ("province".equals(type)) {
                    result = Utility.handleProvinceResponse(responseText);
                } else if ("city".equals(type)) {
                    result = Utility.handleCityResponse(responseText, selectedProvince.getId());
                } else if ("county".equals(type)) {
                    result = Utility.handleCountryResponse(responseText, selectedCity.getId());
                }
                if (result) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //  取消加载对话框
                            closeProgressDialog();
                            if ("province".equals(type)) {
                                queryProvinces();
                            } else if ("city".equals(type)) {
                                queryCities();
                            } else if ("county".equals(type)) {
                                queryCounties();
                            }
                        }
                    });
                }
            }
        });
    }

    /**
     * 显示进度条对话框
     */
    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading...");
            //  点击框外取消对话框
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    /**
     * 关闭进度条
     */
    private void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
