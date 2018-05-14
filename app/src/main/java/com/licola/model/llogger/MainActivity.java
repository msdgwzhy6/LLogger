package com.licola.model.llogger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.licola.llogger.LLogger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


  }

  public void onClickLogV(View view) {
    LLogger.v();
    LLogger.v("verbose");
    LLogger.v("verbose", view);
  }

  public void onClickLogD(View view) {
    LLogger.d();
    LLogger.d("debug");
    LLogger.d("debug", view);
  }

  public void onClickLogI(View view) {
    LLogger.i();
    LLogger.i("info");
    LLogger.i("info", view);
  }

  public void onClickLogW(View view) {
    LLogger.w();
    LLogger.w("warn");
    LLogger.w("warn", view);
  }

  public void onClickLogE(View view) {
    LLogger.e();
    LLogger.e("error");
    LLogger.e("error", view);
  }

  public void onClickLogA(View view) {
    LLogger.a();
    LLogger.a("assert");
    LLogger.a("assert", view);
  }

  public void onClickLogJson(View view) {
    JSONObject jsonObject = new JSONObject();
    try {
      jsonObject.put("key1", "value1");
      jsonObject.put("key2", "value2");
    } catch (JSONException e) {
      e.printStackTrace();
    }
    LLogger.json(jsonObject);

    JSONArray jsonArray = new JSONArray();
    try {
      jsonArray.put(0, "index1");
      jsonArray.put(1, "index2");
    } catch (JSONException e) {
      e.printStackTrace();
    }
    LLogger.json(jsonArray);
  }

  public void onClickLogTrace(View view) {
    LLogger.trace();
  }

  public void onClickLogInnerClass(View view) {
    MyRunnable myRunnable = new MyRunnable();
    myRunnable.run();
  }

  static class MyRunnable implements Runnable {

    @Override
    public void run() {
      LLogger.d("inner class");
    }
  }

}
