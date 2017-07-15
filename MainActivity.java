package hemanth.codeforgood;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView twregister,twadmin;
    EditText etEmailId,etPassword;
    Button bSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPref = getSharedPreferences("mypref", 0);
        Boolean isfirst = sharedPref.getBoolean("isfirst", true);

        if (isfirst) {
            setContentView(R.layout.activity_main);
            //final  String token = sharedPref.getString("token","");
            etEmailId = (EditText) findViewById(R.id.etEmailId);
            etPassword = (EditText) findViewById(R.id.etPassword);
            twregister =(TextView) findViewById(R.id.twregister);
            twadmin = (TextView) findViewById(R.id.twadmin);
            twadmin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(MainActivity.this, AdminLogin.class);
                    finish();
                    startActivity(intent);
                }
            });
            twregister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(MainActivity.this, RegistrationActivity.class);
                    startActivity(intent);
                }
            });
            bSignIn = (Button) findViewById(R.id.bSignIn);
            bSignIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email= etEmailId.getText().toString();
                    String password=etPassword.getText().toString();
                    if(email.isEmpty()||password.isEmpty())
                    {
                        Toast.makeText(getBaseContext(),"Fill All Fields",Toast.LENGTH_SHORT).show();
                    }else {
                        ConnectivityManager Cm=(ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
                        NetworkInfo ni = Cm.getActiveNetworkInfo();
                        if(ni!=null&&ni.isConnected())
                        {
                            BackgroundTask backgroundTask = new BackgroundTask();
                            backgroundTask.execute(email,password);
                        }
                        else {
                            Toast.makeText(getBaseContext(),"NO NETWORK CONNECTED",Toast.LENGTH_LONG).show();
                        }

                    }
                }
            });
        }
        else {

            Intent intent=new Intent(this, StudentHomePage.class);
            finish();
            startActivity(intent);
            //twregister.setText("Invalid Credentials");
        }
    }
    class BackgroundTask extends AsyncTask<String,Void,String> {
        ProgressDialog progressDoalog;
        @Override
        protected void onPreExecute() {
            progressDoalog = new ProgressDialog(MainActivity.this);
            progressDoalog.setMessage("Please Wait.....\nIf It Is Taking Long Please Check Internet Connection");
            progressDoalog.setTitle("Signing In");
            progressDoalog.show();

        }
        @Override
        protected String doInBackground(String... args) {
            final String email,password ;
            email= args[0];
            password=args[1];
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        if (success) {
                            progressDoalog.dismiss();
                            //String name = jsonResponse.getString("name");
                            SharedPreferences sharedPref = getSharedPreferences("mypref", 0);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            //put your value
                            editor.putBoolean("isfirst", false);
                            //editor.putString("name", name);
                            editor.commit();
                            Intent intent = new Intent(MainActivity.this, StudentHomePage.class);
                            finish();
                            startActivity(intent);
                        } else {
                            progressDoalog.dismiss();
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                            alertDialogBuilder.setMessage("Login Failed");
                            alertDialogBuilder.setPositiveButton("Retry",
                                    new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface arg0, int arg1) {

                                        }
                                    });

                            AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

            LoginRequest loginRequest = new LoginRequest(email,password, responseListener);
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            queue.add(loginRequest);

            return "Login Success";
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(String result) {
            //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        }
    }
    public class LoginRequest extends StringRequest {
        private static final String LOGIN_REQUEST_URL = "https://hemanthraparthi.000webhostapp.com/CodeForGood/login.php";
        private Map<String, String> params;

        public LoginRequest(String email, String password, Response.Listener<String> listener) {
            super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
            params = new HashMap<>();
            params.put("email", email);
            params.put("password",password);
        }

        @Override
        public Map<String, String> getParams() {
            return params;
        }
    }
}
