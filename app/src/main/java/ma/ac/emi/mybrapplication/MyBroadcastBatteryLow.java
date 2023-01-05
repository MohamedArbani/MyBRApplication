package ma.ac.emi.mybrapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

public class MyBroadcastBatteryLow extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "I/BR_TAG: Evenement Batterie faible reçu", Toast.LENGTH_SHORT).show();
    }
}