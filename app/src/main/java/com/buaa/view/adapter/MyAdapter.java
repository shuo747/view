package com.buaa.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.buaa.view.R;

import java.util.List;
import java.util.Map;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, Object>> dataList;

    public MyAdapter(Context context, List<Map<String, Object>> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list, null);
            holder.textView = (TextView) convertView.findViewById(R.id.item_text);
            holder.button = (Button) convertView.findViewById(R.id.item_but);
            holder.imageView = (ImageView) convertView.findViewById(R.id.item_draw);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Map<String, Object> map = dataList.get(position);
        holder.imageView.setImageResource((Integer) map.get("draw"));
        holder.textView.setText((String) map.get("name"));

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "删除了第" + position + "行", Toast.LENGTH_LONG).show();
                dataList.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    static class ViewHolder {
        TextView textView;
        Button button;
        ImageView imageView;
    }
}
