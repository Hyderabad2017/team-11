package deepika.sampangi.com.latlongtrials;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends Activity {
    Button btnShowLocation;

    GPSTracker gps;
    int x;
    boolean access=true;
    // Location location;
    Calendar calendar = Calendar.getInstance();
    String minutes;
    private static BroadcastReceiver tickReceiver;
    private static final int PERMISSION_REQUEST_CODE_LOCATION = 1;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        btnShowLocation = (Button) findViewById(R.id.btn);
        // show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                fetchLocationData();


            }
        });
        tickReceiver=new BroadcastReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent.getAction().compareTo(Intent.ACTION_TIME_TICK)==0)
                {
                    fetchLocationData();
                    // Toast.makeText(getApplicationContext(), "\nminutes: " + minutes, Toast.LENGTH_LONG).show();
                }

            }
        };

        //Register the broadcast receiver to receive TIME_TICK
        registerReceiver(tickReceiver, new IntentFilter(Intent.ACTION_TIME_TICK));

    }
    @Override
    public void onStop()
    {
        super.onStop();
        //unregister broadcast receiver.
        if(tickReceiver!=null)
            unregisterReceiver(tickReceiver);
    }


    private void fetchLocationData()
    {
        //code to use the granted permission (location)
       // minutes = calendar.get(Calendar.MINUTE);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        minutes =sdf.format(cal.getTime());

        gps = new GPSTracker(MainActivity.this);
        if (gps.canGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();


           // Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude + "\nminutes: " + minutes, Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude + "\nminutes: " + minutes, Toast.LENGTH_LONG).show();

        }
        else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }


    }


}
