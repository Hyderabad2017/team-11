package hemanth.codeforgood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StudentRegister extends AppCompatActivity {
    TextView student,Scribe;
    EditText etusername,etpassword,etlocation,etlanguage,etgender,ettype;
    Button bsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        etusername = (EditText) findViewById(R.id.username);
        etpassword = (EditText) findViewById(R.id.password);
        etlocation =(EditText) findViewById(R.id.location);
        etlanguage = (EditText) findViewById(R.id.language);
        etgender = (EditText) findViewById(R.id.gender);
        bsubmit = (Button) findViewById(R.id.submit);
        student=(TextView) findViewById(R.id.textView10);
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StudentRegister.this, RegistrationActivity.class);
                finish();
                startActivity(intent);
            }
        });
        bsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StudentRegister.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
}
