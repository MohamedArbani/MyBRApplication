package ma.ac.emi.mybrapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("BR_TAG"," Event Received ");
        Toast.makeText(context, "I/BR_TAG: Event Received", Toast.LENGTH_SHORT).show();
    }
}