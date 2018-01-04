package com.buaa.layout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class ProductFrameActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = new FrameLayout(this);//创建线性布局管理器
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, //设置宽度为充满父元素
                ViewGroup.LayoutParams.MATCH_PARENT);//设置高度为充满父元素


        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, //设置宽度为包裹内容
                ViewGroup.LayoutParams.WRAP_CONTENT);//设置高度为包裹内容
        TextView textView = new TextView(this);//创建一个文本控件
        textView.setLayoutParams(params);//将参数传入文本控件
        textView.setText("这是帧布局的一个文本");

        Button button = new Button(this);
        button.setLayoutParams(params);
        button.setText("李瑞奇");

        frameLayout.addView(textView);
        frameLayout.addView(button);
        super.addContentView(frameLayout, layoutParams);//设置了activity要显示的组件和参数
    }
}
