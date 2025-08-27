package com.freehands.assistant;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {
    private static final String TAG = "BootReceiver";
    
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        
        if (Intent.ACTION_BOOT_COMPLETED.equals(action) ||
            Intent.ACTION_MY_PACKAGE_REPLACED.equals(action) ||
            Intent.ACTION_PACKAGE_REPLACED.equals(action)) {
            
            Log.d(TAG, "Boot completed or package updated, starting FreeHands service");
            
            try {
                // Start the voice listening service
                Intent serviceIntent = new Intent(context, VoiceListeningService.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    context.startForegroundService(serviceIntent);
                } else {
                    context.startService(serviceIntent);
                }
                
                Log.d(TAG, "FreeHands service started successfully");
                
            } catch (Exception e) {
                Log.e(TAG, "Error starting FreeHands service on boot", e);
            }
        }
    }
}
