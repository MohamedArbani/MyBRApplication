package ma.ac.emi.mybrapplication;

import android.telecom.Call;
import android.telecom.CallScreeningService;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.Date;

public class ScreeningService extends CallScreeningService {

    @Override
    public void onScreenCall(@NonNull Call.Details details) {
        String phoneNumber = details.getHandle().getSchemeSpecificPart();
        Log.i("Tag SS","Appel received from : "+phoneNumber);
        Date callStartTime = new Date();
        Toast.makeText(getApplicationContext(), "Appel received from : "+phoneNumber + " at "+ callStartTime, Toast.LENGTH_SHORT).show();
        MainActivity.showPhoneNumber(phoneNumber,callStartTime);
    }
}
