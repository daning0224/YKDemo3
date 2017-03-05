package com.bawei.ykdemo3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bawei.ykdemo3.adapter.MyRecyclerAdapter;
import com.bawei.ykdemo3.adapter.MyRecyclerAdapter1;
import com.bawei.ykdemo3.bean.Bean;
import com.bawei.ykdemo3.bean.Children;
import com.bawei.ykdemo3.bean.RS;
import com.bawei.ykdemo3.util.OkHttpUtils;
import com.bawei.ykdemo3.view.DividerListItemDecoration;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        recyclerView1 = (RecyclerView) findViewById(R.id.recyclerView1);
        recyclerView2 = (RecyclerView) findViewById(R.id.recyclerView2);

        //请求数据
        requsetData();

        //添加分割线
        recyclerView1.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerListItemDecoration.VERTICAL_LIST));
        recyclerView2.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerListItemDecoration.VERTICAL_LIST));
    }

    private void requsetData() {

        String url = "http://mock.eoapi.cn/success/4q69ckcRaBdxhdHySqp2Mnxdju5Z8Yr4";
        HashMap<String, String> map = new HashMap<>();

        App.okHttpUtils.setUrl(url, map, Bean.class, OkHttpUtils.Methods.GET);

        App.okHttpUtils.setCallbackM(new OkHttpUtils.CallbackM() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Object response) {
                Bean bean = (Bean) response;
                final ArrayList<RS> rs = bean.getRs();

//                Log.d(TAG, "+++++++++++++++++++" + rs.toString());
                //LayoutManager
                recyclerView1.setLayoutManager(new LinearLayoutManager(MainActivity.this,
                        LinearLayoutManager.VERTICAL, false));
                //左边RecyclerView的适配器
                final MyRecyclerAdapter adapter = new MyRecyclerAdapter(MainActivity.this, rs);
                recyclerView1.setAdapter(adapter);

                //第一次进入页面，第二个RecyclerView显示对应的数据
                if (rs != null) {
                    //LayoutManager
                    recyclerView2.setLayoutManager(new LinearLayoutManager(MainActivity.this,
                            LinearLayoutManager.VERTICAL, false));
                    //右边RecyclerView的适配器
                    MyRecyclerAdapter1 dapter1 = new MyRecyclerAdapter1(MainActivity.this, rs.get(App.LEFT_POSITION).getChildren());
                    recyclerView2.setAdapter(dapter1);
                }

                adapter.setOnItemClickListener(new MyRecyclerAdapter.onItemClickListener() {
                    @Override
                    public void setOnItemClickListener(View view, int position) {
                        //让右边的数据与左边的同步
                        App.LEFT_POSITION = position;
                        adapter.notifyDataSetChanged();
                        ArrayList<Children> children = rs.get(App.LEFT_POSITION).getChildren();

                        /*****************************************************************************/
                        //LayoutManager
                        recyclerView2.setLayoutManager(new LinearLayoutManager(MainActivity.this,
                                LinearLayoutManager.VERTICAL, false));
                        //右边RecyclerView的适配器
                        MyRecyclerAdapter1 dapter1 = new MyRecyclerAdapter1(MainActivity.this, children);
                        recyclerView2.setAdapter(dapter1);
                    }
                });
            }
        });
    }
}
