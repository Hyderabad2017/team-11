package hemanth.codeforgood;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {
    TextView student,Scribe;
    EditText etusername,etpassword,etlocation,etlanguage,etgender,ettype;
    Button bsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        etusername = (EditText) findViewById(R.id.username);
        etpassword = (EditText) findViewById(R.id.password);
        etlocation =(EditText) findViewById(R.id.location);
        etlanguage = (EditText) findViewById(R.id.language);
        etgender = (EditText) findViewById(R.id.gender);
        bsubmit = (Button) findViewById(R.id.submit);
        student=(TextView) findViewById(R.id.textView11);
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegistrationActivity.this, StudentRegister.class);
                finish();
                startActivity(intent);
            }
        });

        bsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=etusername.getText().toString();
                String password=etpassword.getText().toString();
                String location=etlocation.getText().toString();
                String language=etlanguage.getText().toString();
                String gender=etgender.getText().toString();
                BackgroundTask backgroundTask = new BackgroundTask();
                backgroundTask.execute(username,password,location,language,gender,"Scribe");
            }
        });

    }
    class BackgroundTask extends AsyncTask<String,Void,String> {
        ProgressDialog progressDoalog;
        @Override
        protected void onPreExecute() {
            progressDoalog = new ProgressDialog(RegistrationActivity.this);
            progressDoalog.setMessage("Please Wait.....\nIf It Is Taking Long Please Check Internet Connection");
            progressDoalog.setTitle("Signing In");
            progressDoalog.show();

        }
        @Override
        protected String doInBackground(String... args) {
            final String username,password,location,language,gender,type ;
            username= args[0];
            password=args[1];
            location=args[2];
            language=args[3];
            gender=args[4];
            type=args[5];
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
                            Intent intent = new Intent(RegistrationActivity.this, DashBoardActivity.class);
                            finish();
                            startActivity(intent);
                        } else {
                            progressDoalog.dismiss();
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RegistrationActivity.this);
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

            LoginRequest loginRequest = new LoginRequest(username,password,location,language,gender,type, responseListener);
            RequestQueue queue = Volley.newRequestQueue(RegistrationActivity.this);
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
        private static final String LOGIN_REQUEST_URL = "https://hemanthraparthi.000webhostapp.com/CodeForGood/register.php";
        private Map<String, String> params;

        public LoginRequest(String username, String password,String location,String language,String gender,String type, Response.Listener<String> listener) {
            super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
            params = new HashMap<>();
            params.put("email", username);
            params.put("password",password);
            params.put("location", location);
            params.put("language",language);
            params.put("gender",gender);
            params.put("type",type);
        }

        @Override
        public Map<String, String> getParams() {
            return params;
        }
    }
}
