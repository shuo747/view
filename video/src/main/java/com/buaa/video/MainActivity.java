package com.buaa.video;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private VideoView videoView;
    private Button play;
    private Button pause;
    private Button stop;
    private SeekBar seekBar;
    private TextView playTime;
    private TextView allTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getPermission();
    }

    private void initView() {
        playTime = (TextView) findViewById(R.id.played_time);
        allTime = (TextView) findViewById(R.id.all_time);

        play = (Button) findViewById(R.id.play);
        pause = (Button) findViewById(R.id.pause);
        stop = (Button) findViewById(R.id.stop);
        videoView = (VideoView) findViewById(R.id.video);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);

        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                if (fromUser == true) {
                    videoView.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void initVideoPath() {
        File file = new File(Environment.getExternalStorageDirectory(),
                "music/爱的代价.mp4");
        videoView.setVideoPath(file.getPath());
    }

    private void setVideoViewFromNet() {
        Uri uri = Uri.parse("http://120.210.209.69/hc.yinyuetai.com/uploads/videos/common/B7D20154766D39739AF193BCE138419F.flv?sc\\u003d9765de84d699cd91\\u0026br\\u003d759\\u0026vid\\u003d2561631\\u0026aid\\u003d311\\u0026area\\u003dHT\\u0026vst\\u003d2");
        videoView.setVideoURI(uri);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:
                if (!videoView.isPlaying()) {
                    videoView.start();
                    seekBar.setMax(videoView.getDuration());
                    Message message = handler.obtainMessage();
                    message.what = 1;
                    message.arg1 = videoView.getDuration();
                    handler.sendMessage(message);
                    handler.post(updateThread);
                }
                break;
            case R.id.pause:
                if (videoView.isPlaying()) {
                    videoView.pause();
                    handler.removeCallbacks(updateThread);
                }
                break;
            case R.id.stop:
                if (videoView.isPlaying()) {
                    videoView.stopPlayback();
                    initVideoPath();
                    handler.removeCallbacks(updateThread);
                    handler.sendEmptyMessage(3);
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null) {
            videoView.suspend();
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    allTime.setText(msg.arg1 / 60000 + ":" + msg.arg1 / 1000 % 60);
                    break;
                case 3:
                    allTime.setText("00:00");
                    playTime.setText("00:00");
                    seekBar.setProgress(0);
                    break;
            }
        }
    };
    Runnable updateThread = new Runnable() {
        public void run() {
            seekBar.setProgress(videoView.getCurrentPosition());
            playTime.setText(videoView.getCurrentPosition() / 60000 +
                    ":" + videoView.getCurrentPosition() / 1000 % 60);
            handler.postDelayed(updateThread, 100);
        }
    };

    public void getPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkPermission = ContextCompat.
                    checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            if (checkPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        111);
                return;
            } else {
                initVideoPath();
            }
        } else {
            initVideoPath();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case 111:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "获取权限成功", Toast.LENGTH_SHORT)
                            .show();
                    initVideoPath();
                } else {
                    Toast.makeText(this, "获取权限失败", Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
