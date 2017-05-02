package com.example.dududu.listener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.example.dududu.R;
import com.example.dududu.util.PathManager;
import com.example.dududu.util.TimeUtility;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class QueueListener implements OnClickListener {

  private Button btn_leave;
  private LinearLayout lly_queue;
  private Activity activity;

  private RandomAccessFile randFileArrive = null;
  private RandomAccessFile randFileServer = null;

  private String arriveFilename;
  private String serverFilename;

  private String prevTime;

  public QueueListener(Activity mainActivity, int i, Button btn_leave, LinearLayout lly_queue) {
    this.btn_leave = btn_leave;
    this.lly_queue = lly_queue;
    this.activity = mainActivity;

    arriveFilename = "到达" + i;
    serverFilename = "服务" + i;
  }

  @Override
  public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.btn_arrive)
      arriveOnClick();
    else
      leaveOnClick();
  }

  private void leaveOnClick() {
    // 写此次服务时间到文件
    try {
      if (randFileServer == null) {
        randFileServer =
            new RandomAccessFile(PathManager.getSavePath() + "/" + serverFilename + ".csv", "rw");
      }
      String currentTime = TimeUtility.getCurrentTime();
      randFileServer.seek(randFileServer.length());

      randFileServer.writeUTF(prevTime + "," + currentTime + "\n");
      Toast.makeText(activity, prevTime + "  " + currentTime, Toast.LENGTH_SHORT).show();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    lly_queue.removeViewAt(0);
    prevTime = TimeUtility.getCurrentTime();

    if (lly_queue.getChildCount() == 0) btn_leave.setEnabled(false);
  }

  // 创建一个对象表示一个到达的人，以便插入队列中
  private ImageView createQueueEle() {
    ImageView ret;
    ret = new ImageView(activity);
    ret.setBackgroundResource(R.drawable.block);

    LinearLayout.LayoutParams params =
        new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);
    params.setMargins(0, 0, 2, 0);
    // 设置右边间距1dp
    ret.setLayoutParams(params);

    return ret;
  }

  private void arriveOnClick() {
    // 写当前到达日期到文件
    try {
      if (randFileArrive == null) {
        randFileArrive =
            new RandomAccessFile(PathManager.getSavePath() + "/" + arriveFilename + ".csv", "rw");
      }
      String currentTime = TimeUtility.getCurrentTime();
      randFileArrive.seek(randFileArrive.length());

      randFileArrive.writeUTF(currentTime + "\n");
      Toast.makeText(activity, currentTime, Toast.LENGTH_SHORT).show();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // 插入一个元素到队列
    ImageView imageView = createQueueEle();
    lly_queue.addView(imageView);
    if (lly_queue.getChildCount() == 1) {
      prevTime = TimeUtility.getCurrentTime();
    }
    if (!btn_leave.isEnabled()) btn_leave.setEnabled(true);
  }

}
