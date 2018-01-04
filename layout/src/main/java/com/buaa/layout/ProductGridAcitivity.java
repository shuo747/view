package com.buaa.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class ProductGridAcitivity extends Activity {
    String[] btnLable = new String[]{
            "7", "8", "9", "+",
            "4", "5", "6", "-",
            "1", "2", "3", "*",
            "0", ".", "=", "/"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GridLayout gridLayout = new GridLayout(this);

        TextView textView = new TextView(this);//创建一个文本控件
        textView.setText("0");
        textView.setTextSize(30);
        gridLayout.addView(textView);

        for (int i = 0; i < btnLable.length; i++) {    //添加16个网格，每个网格1个按钮
            Button button = new Button(this);
            button.setText(btnLable[i]);
            button.setTextSize(30);
            GridLayout.Spec rowSpec = GridLayout.spec(i / 4 + 2);   //行位置
            GridLayout.Spec colSpec = GridLayout.spec(i % 4);       //列位置
            GridLayout.LayoutParams gridLayoutParams = new GridLayout.LayoutParams(rowSpec, colSpec);//属性
            gridLayoutParams.setGravity(Gravity.FILL_HORIZONTAL);//横向排满容器
            gridLayout.addView(button, gridLayoutParams);              //将按钮添加到网格容器内
        }
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
        super.addContentView(gridLayout, layoutParams);
    }
}