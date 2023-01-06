package ma.ac.emi.mybrapplication;

import android.telecom.Call;
import android.telecom.CallScreeningService;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class ScreeningService extends CallScreeningService {

    @Override
    public void onScreenCall(@NonNull Call.Details details) {
        String phoneNumber = details.getHandle().getSchemeSpecificPart();
        Toast.makeText(getApplicationContext(), "Appel received from : "+phoneNumber, Toast.LENGTH_SHORT).show();
    }
}
