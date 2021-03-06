package net.texh.cordovapluginstepcounter;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;

public class StepCounterBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sharedPref = context.getSharedPreferences(CordovaStepCounter.USER_DATA_PREF,
                Context.MODE_PRIVATE);
        Boolean pActive = CordovaStepCounter.getPedometerIsActive(sharedPref);

        if (pActive) {
            Intent stepCounterServiceIntent = new Intent(context, StepCounterService.class);
            if (Build.VERSION.SDK_INT >= 26) {
                context.startForegroundService(stepCounterServiceIntent);
            } else {
                context.startService(stepCounterServiceIntent);
            }
        }
    }
}
