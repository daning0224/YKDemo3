package com.bawei.ykdemo3.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.ykdemo3.App;
import com.bawei.ykdemo3.R;
import com.bawei.ykdemo3.bean.RS;

import java.util.ArrayList;

/**
 * 作    者：云凯文
 * 时    间：2017/3/5
 * 描    述：
 * 修改时间：
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<RS> rs;

    public MyRecyclerAdapter(Context context, ArrayList<RS> rs) {
        this.context = context;
        this.rs = rs;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item1, null);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        ((ViewHolder) holder).tv_dirName.setText(rs.get(position).getDirName());
        //设置背景颜色
        if (App.LEFT_POSITION == position) {
            ((ViewHolder) holder).tv_dirName.setTextColor(context.getResources().getColor(R.color.textColor1));
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.backgroundColor));
        } else {
            ((ViewHolder) holder).tv_dirName.setTextColor(context.getResources().getColor(R.color.notextColor));
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.nobackgroundColor));
        }


        //点击监听事件
        if (null != onitemClickListener) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //获取当前点击的索引
                    int layoutPosition = holder.getLayoutPosition();
                    onitemClickListener.setOnItemClickListener(holder.itemView, layoutPosition);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return rs == null ? 0 : rs.size();
    }

    //创建ViewHolder
    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_dirName;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_dirName = (TextView) itemView.findViewById(R.id.tv_dirName);
        }
    }

    //接口回调，观察者模式
    private onItemClickListener onitemClickListener;

    public interface onItemClickListener {
        void setOnItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(onItemClickListener onitemClickListener) {
        this.onitemClickListener = onitemClickListener;
    }
}
