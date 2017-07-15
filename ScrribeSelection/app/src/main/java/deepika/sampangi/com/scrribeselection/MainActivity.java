package deepika.sampangi.com.scrribeselection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    int preference,availability,QuailifOfVol,ReqQualif, LangPrefOfVol,reqLang,gender,Stdgender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        if(preference==0)
        {

            if(availability!=0 && QuailifOfVol==ReqQualif && LangPrefOfVol==reqLang && gender==Stdgender)
            {

               //Then send mssg and mail
            }

        }

        else
        {
            //send a message and update volunteer
        }



    }
}
