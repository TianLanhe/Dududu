package com.example.dududu;

import java.io.File;

import com.example.dududu.listener.ArriveListener;
import com.example.dududu.listener.StartAndStopListener;
import com.example.dududu.util.PathManager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    // 设置根布局
    LinearLayout rootView =
        (LinearLayout) LayoutInflater.from(this).inflate(R.layout.activity_main, null);
    setContentView(rootView);

    createRootDirectory();
    createDateDirectory();

    View view;
    Button btn_start;
    Button btn_stop;
    Button btn_arrive;
    for (int i = 0; i < 10; ++i) {
      // 加载子布局
      view = LayoutInflater.from(this).inflate(R.layout.include_button, null);
      // 设置序号
      ((TextView) view.findViewById(R.id.txt_order)).setText("" + (i + 1));

      LinearLayout.LayoutParams params =
          new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
              LinearLayout.LayoutParams.WRAP_CONTENT, 1);
      params.gravity = Gravity.CENTER;
      // 设置居中
      view.setLayoutParams(params);

      btn_start = (Button) view.findViewById(R.id.btn_start);
      btn_stop = (Button) view.findViewById(R.id.btn_stop);
      btn_arrive = (Button) view.findViewById(R.id.btn_arrive);

      // 添加监听器
      btn_arrive.setOnClickListener(new ArriveListener(MainActivity.this, "到达" + (i + 1)));
      StartAndStopListener startAndStopListener =
          new StartAndStopListener(MainActivity.this, btn_start, btn_stop, "服务" + (i + 1));
      btn_start.setOnClickListener(startAndStopListener);
      btn_stop.setOnClickListener(startAndStopListener);

      rootView.addView(view);
    }
  }

  // 创建当天的文件夹
  private void createDateDirectory() {
    File file = new File(PathManager.getSavePath());
    if (!file.exists()) {
      file.mkdir();
    } else if (!file.isDirectory()) {
      file.delete();
      file.mkdir();
    }
  }

  // 创建储存数据的文件夹
  private void createRootDirectory() {
    File file = new File(PathManager.getRootPath());
    if (!file.exists()) {
      file.mkdir();
    } else if (!file.isDirectory()) {
      file.delete();
      file.mkdir();
    }
  }
}
