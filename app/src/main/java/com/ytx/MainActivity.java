package com.ytx;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ytx.activity.HomeActivity;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpParams;
import org.kymjs.kjframe.http.impl.HttpManager;
import org.kymjs.kjframe.http.impl.HttpPostListener;
import org.kymjs.kjframe.http.impl.HttpResult;
import org.kymjs.kjframe.ui.BindView;

public class MainActivity extends KJActivity {

    @BindView(id = R.id.btn, click = true)
    private Button btn;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initWidget() {
        super.initWidget();
//        post();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch(v.getId()) {
            case R.id.btn:
                post();
                break;
        }
    }

    private void post() {
        skipActivity(this, HomeActivity.class);
        HttpParams params = new HttpParams();
        params.put("app", "life.postcode");
        params.put("postcode", "310000");
        params.put("appkey", "10003");
        params.put("sign", "b59bc3ef6191eb9f747dd4e83c99f2a4");
//        KJHttp http = new KJHttp();
//        http.get("http://api.k780.com:88/?app=life.postcode&postcode=310000&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4",
//                new HttpCallBack() {
//                    @Override
//                    public void onSuccess(String t) {
//                        super.onSuccess(t);
//                        Log.e("msg", "onResult..." + t);
//
//                    }
//                });
        new HttpManager().resolveVoid("http://api.k780.com:88/", params, new HttpPostListener<Void>() {

            @Override
            public void onResult(HttpResult<Void> result) {
                Log.e("msg", "onResult...");
            }
        });
    }
}
