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
import android.widget.Toast;

public class StartAndStopListener implements OnClickListener {

  private Button btn_start;
  private Button btn_stop;
  private Activity activity;

  private RandomAccessFile randomAccessFile;

  private String prevTime;

  public StartAndStopListener(Activity activity, Button start, Button stop, String filename) {
    this.activity = activity;
    btn_start = start;
    btn_stop = stop;
    try {
      randomAccessFile =
          new RandomAccessFile(PathManager.getSavePath() + "/" + filename + ".csv", "rw");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.btn_start) {
      btn_start.setEnabled(false);
      btn_stop.setEnabled(true);

      prevTime = TimeUtility.getCurrentTime();
    } else {
      btn_start.setEnabled(true);
      btn_stop.setEnabled(false);

      try {
        String currentTime = TimeUtility.getCurrentTime();
        randomAccessFile.seek(randomAccessFile.length());
        randomAccessFile.writeUTF(prevTime + "," + currentTime + "\n");
        Toast.makeText(activity, prevTime + "  " + currentTime, Toast.LENGTH_SHORT).show();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
