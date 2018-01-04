package com.buaa.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ProductTabLayoutActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TableLayout tableLayout = new TableLayout(this);//创建表格布局管理器
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, //设置宽度为充满父元素
                ViewGroup.LayoutParams.MATCH_PARENT);//设置高度为充满父元素
        for (int i = 0; i < 5; i++) {
            TableRow tableRow = new TableRow(this);//创建一列
            for (int j = 0; j < 4; j++) {
                TextView textView = new TextView(this);
                textView.setText("第" + i + "行第" + j + "列");
                tableRow.addView(textView);//将文本加入此列
            }
            tableLayout.addView(tableRow);//将一列降入表格布局管理器
        }
        tableLayout.setStretchAllColumns(true);//设置每一列都可扩展
        super.addContentView(tableLayout, params);//将布局展示出来
    }
}
