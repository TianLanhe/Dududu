package com.example.dududu.listener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.example.dududu.util.PathManager;
import com.example.dududu.util.TimeUtility;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class ArriveListener implements OnClickListener {

  private RandomAccessFile randomAccessFile;
  Activity activity;

  @SuppressLint("SimpleDateFormat")
  public ArriveListener(Activity activity, String filename) {
    this.activity = activity;
    try {
      randomAccessFile =
          new RandomAccessFile(PathManager.getSavePath()+"/" + filename + ".csv", "rw");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onClick(View v) {
    try {
      String currentTime = TimeUtility.getCurrentTime();
      randomAccessFile.seek(randomAccessFile.length());
      randomAccessFile.writeUTF(currentTime + "\n");
      Toast.makeText(activity, currentTime, Toast.LENGTH_SHORT).show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
