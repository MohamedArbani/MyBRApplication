package ma.ac.emi.mybrapplication;

import static androidx.core.content.ContextCompat.getSystemService;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.role.RoleManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    public Activity getActivity(){
        return this;
    }

    static TextView phone;
    static TextView dateTV;


    private static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 1;
    MyReceiver myReceiver = new MyReceiver();
    MyBroadcastBatteryLow myBroadcastBatteryLow = new MyBroadcastBatteryLow();
    IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_LOW);

    MyIntercepterCallsReceiver myIntercepterCallsReceiver = new MyIntercepterCallsReceiver();
    IntentFilter filterCR = new IntentFilter("android.intent.action.PHONE_STATE");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);

        phone = findViewById(R.id.PhoneNumber);
        dateTV = findViewById(R.id.dateTV);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_PHONE_STATE)){}
            else
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
        }

        registerReceiver(myIntercepterCallsReceiver,filterCR);

        // Créez un Intent pour démarrer le service de filtrage d'appels
        Intent callScreeningIntent = new Intent();
        callScreeningIntent.setClassName("ma.ac.emi.mybrapplication", "ma.ac.emi.mybrapplication.ScreeningService");
        // Ajoutez des données supplémentaires à l'Intent si nécessaire

        // Démarrez le service de filtrage d'appels
        startService(callScreeningIntent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            requestRole();
        }
    }

    private static final int REQUEST_ID = 1;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void requestRole() {
        RoleManager roleManager = (RoleManager) getSystemService(ROLE_SERVICE);
        Intent intent = roleManager.createRequestRoleIntent(RoleManager.ROLE_CALL_SCREENING);
        startActivityForResult(intent, REQUEST_ID);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ID) {
            if (resultCode == Activity.RESULT_OK) {
                // Your app is now the call screening app
            } else {
                // Your app is not the call screening app
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_PHONE_STATE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "GRANTED CALL", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Operation field caused by permissions denied", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }

    public static void showPhoneNumber(String number, Date date){
        phone.setText(number);
        dateTV.setText(date.toString());
    }

    public void sendBroadcastTest(View view) {
        Intent intent = new Intent("FAKE_EVENT_INFO");
        sendBroadcast(intent);
        registerReceiver(myReceiver,new IntentFilter("FAKE_EVENT_INFO"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(myBroadcastBatteryLow,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myBroadcastBatteryLow);
        unregisterReceiver(myIntercepterCallsReceiver);
    }
}