package com.buaa.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProductLinearLayoutActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(this);//创建线性布局管理器
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, //设置宽度为充满父元素
                ViewGroup.LayoutParams.MATCH_PARENT);//设置高度为充满父元素
        linearLayout.setOrientation(LinearLayout.VERTICAL);//垂直布局

        TextView textView = new TextView(this);//创建一个文本控件
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, //设置宽度为包裹内容
                ViewGroup.LayoutParams.WRAP_CONTENT);//设置高度为包裹内容
        textView.setLayoutParams(textParams);//将参数传入文本控件
        textView.setText("我是这本书的作者李瑞奇");//给文本控件传入内容
        textView.setTextSize(30);//设置文字的大小

        linearLayout.addView(textView);//布局管理器使用高度与宽度的参数
        super.addContentView(linearLayout, params);//设置了activity要显示的组件和参数
    }
}
