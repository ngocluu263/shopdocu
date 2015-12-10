package com.swd2015.shopdocu.Controller.Util.Object;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Created by Minh on 12/10/2015.
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
  public Activity activity;

  public ExceptionHandler(Activity activity){
      this.activity=activity;
      Thread.getDefaultUncaughtExceptionHandler();
  }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        try{
            StackTraceElement[] arr = ex.getStackTrace();
            String report = ex.toString();

            String strStack = "";
            strStack += "Stack trace: ";
            for (int i=0; i<arr.length; i++){
                strStack += "    "+arr[i].toString()+"\t";
            }
            // If the exception was thrown in a background thread inside
            // AsyncTask, then the actual exception can be found with getCause
            String strCause = "";
            strCause += "Cause: ";
            Throwable cause = ex.getCause();
        }
        catch(Exception ex1){
            new AlertDialog.Builder(activity).
                    setMessage("Có lỗi xảy ra").
                    setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).
                    show();
        }
    }
}
