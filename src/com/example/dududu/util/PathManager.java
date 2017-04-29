package com.example.dududu.util;

import android.os.Environment;

public class PathManager {
  public static String getRootPath() {
    return Environment.getExternalStorageDirectory() + "/Dududu";
  }

  public static String getSavePath() {
    return Environment.getExternalStorageDirectory() + "/Dududu/" + TimeUtility.getCurrentDate();
  }
}
