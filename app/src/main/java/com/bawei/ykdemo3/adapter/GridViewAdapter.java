package com.bawei.ykdemo3.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.ykdemo3.R;
import com.bawei.ykdemo3.bean.Children1;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

/**
 * 作    者：云凯文
 * 时    间：2017/3/5
 * 描    述：
 * 修改时间：
 */

class GridViewAdapter extends BaseAdapter {


    private final Context context;
    private final ArrayList<Children1> children1;
    private ImageView im_image;
    private TextView tv_name;
    private LinearLayout item;

    public GridViewAdapter(Context context, ArrayList<Children1> children1) {
        this.context = context;
        this.children1 = children1;
    }

    @Override
    public int getCount() {
        return children1.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.griditem, null);
            im_image = (ImageView) convertView.findViewById(R.id.im_image);
            tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            item = (LinearLayout) convertView.findViewById(R.id.item);
        }

        ImageLoader instance = ImageLoader.getInstance();
        instance.init(new ImageLoaderConfiguration.Builder(context).build());
        instance.displayImage(children1.get(position).getImgApp(), im_image);
        tv_name.setText(children1.get(position).getDirName());

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, children1.get(position).getDirName(), Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
