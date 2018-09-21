package com.gaoyuhao.zxingsimple;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.util.Log;

/**
 * author: gaoyuhao
 * time: 2018/9/12
 * description:
 */
final class InactivityTimer {
    private static final String TAG = InactivityTimer.class.getSimpleName();

    private static final long INACTIVITY_DELAY_MS = 5 * 60 * 1000L;


    private final Activity activity;
    private BroadcastReceiver powerStatusReceiver;
    private boolean registered;
    private AsyncTask<Object,Object,Object> inactivityTask;

    InactivityTimer(Activity activity){
        this.activity = activity;
        powerStatusReceiver = new PowerStatusReceiver();
        registered = false;
        onActivity();
    }

    synchronized void onActivity(){
        cancel();
        inactivityTask = new InactivityAsyncTask();
        inactivityTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    synchronized void onPause(){
        cancel();
        if(registered){
            activity.unregisterReceiver(powerStatusReceiver);
            registered = false;
        }else{
            Log.w(TAG, "PowerStatusReceiver was never registered?");
        }
    }

    synchronized void onResume() {
        if (registered) {
            Log.w(TAG, "PowerStatusReceiver was already registered?");
        } else {
            activity.registerReceiver(powerStatusReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            registered = true;
        }
        onActivity();
    }

    private synchronized void cancel() {
        AsyncTask<?,?,?> task = inactivityTask;
        if (task != null) {
            task.cancel(true);
            inactivityTask = null;
        }
    }

    void shutdown(){
        cancel();
    }

    private class PowerStatusReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }

    private class InactivityAsyncTask extends AsyncTask<Object, Object, Object> {
        @Override
        protected Object doInBackground(Object... objects) {
            return null;
        }
    }
}
