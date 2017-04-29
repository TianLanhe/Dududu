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

    // ���ø�����
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
      // �����Ӳ���
      view = LayoutInflater.from(this).inflate(R.layout.include_button, null);
      // �������
      ((TextView) view.findViewById(R.id.txt_order)).setText("" + (i + 1));

      LinearLayout.LayoutParams params =
          new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
              LinearLayout.LayoutParams.WRAP_CONTENT, 1);
      params.gravity = Gravity.CENTER;
      // ���þ���
      view.setLayoutParams(params);

      btn_start = (Button) view.findViewById(R.id.btn_start);
      btn_stop = (Button) view.findViewById(R.id.btn_stop);
      btn_arrive = (Button) view.findViewById(R.id.btn_arrive);

      // ��Ӽ�����
      btn_arrive.setOnClickListener(new ArriveListener(MainActivity.this, "����" + (i + 1)));
      StartAndStopListener startAndStopListener =
          new StartAndStopListener(MainActivity.this, btn_start, btn_stop, "����" + (i + 1));
      btn_start.setOnClickListener(startAndStopListener);
      btn_stop.setOnClickListener(startAndStopListener);

      rootView.addView(view);
    }
  }

  // ����������ļ���
  private void createDateDirectory() {
    File file = new File(PathManager.getSavePath());
    if (!file.exists()) {
      file.mkdir();
    } else if (!file.isDirectory()) {
      file.delete();
      file.mkdir();
    }
  }

  // �����������ݵ��ļ���
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
