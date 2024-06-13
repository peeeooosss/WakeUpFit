package com.example.wakeupfit.Goals;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Intent serviceIntent = new Intent(context, StepCounterService.class);
            context.startService(serviceIntent);
        }
    }
}
