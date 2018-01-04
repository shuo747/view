package com.buaa.contentresolve;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResolveActivity extends AppCompatActivity {
    private ListView listView;
    private List<Map<String, Object>> mapList;
    private List<User> userList;
    private UserResolve userResolve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resolve);

        initData();
        initView();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.user_list);
        final SimpleAdapter simpleAdapter = new SimpleAdapter(
                this, mapList, R.layout.user_item,
                new String[]{"name", "age", "phone"}, new int[]{R.id.user_name, R.id.user_age, R.id.user_phone});
        listView.setAdapter(simpleAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int location = position;
                new AlertDialog.Builder(ResolveActivity.this)
                        .setTitle("警告")
                        .setMessage("确定删除此条数据吗？")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                userResolve.delete(userList.get(location));
                                mapList.remove(location);
                                simpleAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("否", null)
                        .show();
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int location = position;
                TextView nameText = (TextView) view.findViewById(R.id.user_name);
                final EditText ageEdit = new EditText(ResolveActivity.this);
                //设置EditText的输入为数字
                ageEdit.setInputType(InputType.TYPE_CLASS_NUMBER);

                new AlertDialog.Builder(ResolveActivity.this)
                        .setTitle("修改" + nameText.getText().toString() + "的年龄")
                        .setView(ageEdit)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String updateAge = ageEdit.getText().toString();

                                User user = userList.get(location);
                                user.setAge(Integer.parseInt(updateAge));
                                userResolve.update(user);

                                Map<String, Object> updateMap = mapList.get(location);
                                updateMap.put("age", Integer.parseInt(updateAge));
                                mapList.remove(location);
                                mapList.add(location, updateMap);
                                simpleAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });
    }

    private void initData() {
        userResolve = new UserResolve(this);
        userList = userResolve.query();
        mapList = new ArrayList<>();
        for (User user : userList) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", user.getName());
            map.put("age", user.getAge());
            map.put("phone", user.getPhone());
            mapList.add(map);
        }
    }
}
