package ma.ac.emi.mybrapplication;

import static androidx.core.content.ContextCompat.getSystemService;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import java.util.Date;

public class MyIntercepterCallsReceiver extends BroadcastReceiver {

    String MyPhoneNumber;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint({"HardwareIds"})
    @Override
    public void onReceive(Context context, Intent intent) {

        String callState = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
        String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER); //TO DO: xxx

        // utilisez TelephonyManager pour obtenir le numéro appelant en tant qu’extra
        if(callState.equals(TelephonyManager.EXTRA_STATE_RINGING)){
            //appel entrant
            Date callStartTime = new Date();
            //TO DO : afficher num d’appel et date/heure d’appel
            //Toast.makeText(context, "Appel received from : "+number + " at "+ callStartTime, Toast.LENGTH_SHORT).show();

            //MainActivity.showPhoneNumber(number,callStartTime);
        }
    }
}