package hemanth.codeforgood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StudentHomePage extends AppCompatActivity {
    EditText exam,location,date,time;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home_page);
        exam = (EditText) findViewById(R.id.editText);
        location = (EditText) findViewById(R.id.editText2);
        date =(EditText) findViewById(R.id.editText3);
        time = (EditText) findViewById(R.id.editText4);
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String exam1=exam.getText().toString();
                String location1=location.getText().toString();
                String date1=date.getText().toString();
                String time1=time.getText().toString();

            }
        });
    }
}
