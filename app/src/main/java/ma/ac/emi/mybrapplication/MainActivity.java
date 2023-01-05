package ma.ac.emi.mybrapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MyReceiver myReceiver = new MyReceiver();
    MyBroadcastBatteryLow myBroadcastBatteryLow = new MyBroadcastBatteryLow();
    IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_LOW);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    }
}