package com.bawei.ykdemo3.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.ykdemo3.R;
import com.bawei.ykdemo3.bean.Children;
import com.bawei.ykdemo3.bean.Children1;
import com.bawei.ykdemo3.view.MyGridView;

import java.util.ArrayList;

/**
 * 作    者：云凯文
 * 时    间：2017/3/5
 * 描    述：
 * 修改时间：
 */

public class MyRecyclerAdapter1 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    private ArrayList<Children> children;

    public MyRecyclerAdapter1(Context context, ArrayList<Children> children) {
        this.context = context;
        this.children = children;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item2, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //设置TextView
        ((ViewHolder) holder).tv_dirName.setText(children.get(position).getDirName());
        //获取小集合里面的数据
        ArrayList<Children1> children1 = this.children.get(position).getChildren();

        GridViewAdapter gridViewAdapter = new GridViewAdapter(context, children1);
        ((ViewHolder) holder).gv_item.setAdapter(gridViewAdapter);
    }

    @Override
    public int getItemCount() {
        return children == null ? 0 : children.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_dirName;
        private MyGridView gv_item;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_dirName = (TextView) itemView.findViewById(R.id.tv_dirName);
            gv_item = (MyGridView) itemView.findViewById(R.id.gv_item);
        }
    }
}
