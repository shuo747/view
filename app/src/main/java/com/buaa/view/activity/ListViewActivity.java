package com.buaa.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.buaa.view.R;
import com.buaa.view.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private ListView listView;
    List<Map<String, Object>> dataList = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        initData();
        initView();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.list);
        myAdapter = new MyAdapter(this,
                dataList);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
    }

    private void initData() {
        Map<String, Object> map;
        for (int i = 0; i < 15000; i++) {
            map = new HashMap<>();
            map.put("name", "ricky" + i);
            map.put("draw", R.drawable.ic_launcher);
            dataList.add(map);
        }
    }

    //点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "删除第" + (position + 1) + "行", Toast.LENGTH_LONG).show();
        dataList.remove(position);
        myAdapter.notifyDataSetChanged();
    }

    //长按事件
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "您长按了第" + (position + 1) + "行", Toast.LENGTH_LONG).show();
        return true;
    }
}
