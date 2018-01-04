package com.buaa.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ProductRelativeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative);//调用布局上面的文件

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relative);//获取布局管理器
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, //设置宽度为充满父元素
                ViewGroup.LayoutParams.MATCH_PARENT);//设置高度为充满父元素
        params.addRule(RelativeLayout.BELOW,R.id.txtSMS);//设置参数，在id为txtSMS的下面

        TextView textView = new TextView(this);//创建一个TextView
        textView.setText("已发送");
        textView.setTextSize(28);
        relativeLayout.addView(textView,params);//将这个TextView加入RelativeLayout，位置根据params参数决定
    }
}
