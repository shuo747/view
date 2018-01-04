package com.buaa.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.buaa.view.R;

public class ShowViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_view);
        initView();
        myDialog();
        //test
    }

    private void initView() {
        //获取TextView控件
        TextView textView = (TextView) findViewById(R.id.show_text);
        //设置TextView控件的内容
        textView.setText("通过代码控制的TextView");
        //设置TextView控件文字大小
        textView.setTextSize(20);

        //获取EditText控件
        EditText editText = (EditText) findViewById(R.id.show_edit);
        //获取EditText输入内容，此时EditText中没有内容，注释掉
        // editText.getText().toString();
        //设置EditText输入内容为Text类型
        editText.setInputType(InputType.TYPE_CLASS_TEXT);

        //获取Button控件
        Button button = (Button) findViewById(R.id.show_button);
        //设置为可见
        button.setVisibility(View.VISIBLE);

        //获取ImageView控件
        ImageView imageView = (ImageView) findViewById(R.id.show_image);
        //设置为可见
        imageView.setVisibility(View.VISIBLE);
        //设置ImageView的图片
        imageView.setImageResource(R.drawable.ic_launcher);

        //获取RadioGroup控件
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.show_group_button);
        //默认选中排球
        radioGroup.check(R.id.paiqiu);
    }

    private void toast() {
        //一般情况下使用Toast类的静态方法makeText，然后调用show()方法就可以了
        //第一个参数表示context对象，第二个为显示内容，第三个为显示长度
        Toast toast = Toast.makeText(this, "显示位置的方式", Toast.LENGTH_LONG);
        //设置Toast显示的位置，一般不调用此方法，会默认显示到界面底部
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        //一般情况下会使用下面这种方式显示Toast
        Toast.makeText(this, "常用方式", Toast.LENGTH_SHORT).show();
    }


    private void myDialog() {
        //初始化一个AlertDialog的内置对象builder
        AlertDialog.Builder builder = new AlertDialog.Builder(
                ShowViewActivity.this);
        //设置Dialog的title
        builder.setTitle("提示");
        //设置显示内容
        builder.setMessage("正在显示的是包含多个按钮以及一个icon为美女的对话框");
        //设置icon
        builder.setIcon(R.drawable.img);

        //添加一个确定的按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            //设置当点击确定后使用Toast显示一行文字，并让Dialog消失
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ShowViewActivity.this, "确定被点击",
                        Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        //添加一个否定以后再说按钮
        builder.setNegativeButton("以后再说", new DialogInterface.OnClickListener() {
            //设置当点击此按钮后使用Toast显示一行文字，并让Dialog消失
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Toast.makeText(ShowViewActivity.this, "以后再说被点击",
                        Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        //添加一个忽略按钮
        builder.setNeutralButton("忽略", new DialogInterface.OnClickListener() {
            //设置当点击按钮后使用Toast显示一行文字，并让Dialog消失
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Toast.makeText(ShowViewActivity.this, "忽略被点击",
                        Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        //使用模态，即不对上述三个按钮做操作则不能继续其他操作。默认为可以其他操作
        builder.setCancelable(false);
        //调用show()方法显示这个Dialog
        builder.show();
    }
}
