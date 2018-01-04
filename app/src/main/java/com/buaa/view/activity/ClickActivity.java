package com.buaa.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.buaa.view.R;

public class ClickActivity extends AppCompatActivity implements
        View.OnClickListener, View.OnLongClickListener,
        View.OnTouchListener, View.OnKeyListener,
        AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener,
        View.OnFocusChangeListener {

    private Button button;
    private TextView textView;
    private LinearLayout linearLayout;
    private EditText editText;
    private Spinner spinner;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);

        intiView();
    }

    private void intiView() {
        button = (Button) findViewById(R.id.click_event);
        textView = (TextView) findViewById(R.id.text_event);

        button.setOnClickListener(this);
        textView.setOnClickListener(this);
        textView.setOnTouchListener(this);

        linearLayout = (LinearLayout) findViewById(R.id.long_click_event);
        linearLayout.setOnLongClickListener(this);

        editText = (EditText) findViewById(R.id.edti_event);
        //   editText.setOnKeyListener(this);

        spinner = (Spinner) findViewById(R.id.spinner_event);
        spinner.setOnItemSelectedListener(this);

        radioGroup = (RadioGroup) findViewById(R.id.sex_event);
        radioGroup.setOnCheckedChangeListener(this);

        editText.setOnFocusChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_event:
                Toast.makeText(this, "您点击了一个TextView", Toast.LENGTH_LONG).show();
                break;
            case R.id.click_event:
                Toast.makeText(this, "您点击了一个Button", Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        Toast.makeText(this, "您长按了一个LinearLayout", Toast.LENGTH_LONG).show();
        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Toast.makeText(this, "您触摸了一个TextView，它的坐标是：" +
                "X=" + event.getX() + ",Y=" + event.getY(), Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        switch (event.getAction()) {
            case KeyEvent.ACTION_DOWN:
                Toast.makeText(this, "按键落下", Toast.LENGTH_LONG).show();
                break;
            case KeyEvent.ACTION_UP:
                EditText et = (EditText) v;
                Toast.makeText(this, "按键弹起，键入的是：" + et.getText().toString(), Toast.LENGTH_LONG).show();
                break;
        }
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String value = parent.getItemAtPosition(position).toString();
        // editText.setText("您的位置是：" + value);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton radioButton = (RadioButton) findViewById(checkedId);
        Toast.makeText(this, "您选择了：" + radioButton.getText().toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            Toast.makeText(this, "第一个EditText获得了焦点", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "第一个EditText失去了焦点", Toast.LENGTH_LONG).show();
        }
    }
}