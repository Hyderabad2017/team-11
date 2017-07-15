package com.example.sumanth.studentmainpage;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class StudentMainPage extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    public static final ArrayList<String> ExamNames=new ArrayList<>();
    public static final ArrayList<Integer> Request_status=new ArrayList<>();

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }
    private static void loadSpinnerData(View rootView) {
        final List<String> spinnerArray =  new ArrayList<>();
        RestAdapter api = new RestAdapter.Builder().setEndpoint(" http://a4477b74.ngrok.io/BunkMaar").build();
        Student_Main_Retro ai = api.create(Student_Main_Retro.class);
        //Retrieve from DB and add to spinnerArray
        ai.spn_pop(new Callback<JsonElement>() {
            @Override
            public void success(JsonElement jsonElement, Response response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(jsonElement.toString());
                    if(jsonObject.getInt("result")==1)
                    {
                        JSONArray bunk= jsonObject.getJSONArray("bunk");
                        for(int i=0;i<bunk.length();i++)
                        {
                            JSONObject ele=bunk.getJSONObject(i);
                            spinnerArray.add(ele.getString("Exam"));
                        }
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                rootView.getContext(), R.layout.request, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) rootView.findViewById(R.id.exam_spn);
        sItems.setAdapter(adapter);
    }

    public static void handle(View rootView,int position){
        switch(position){
            case 0:
                Spinner spn=(Spinner) rootView.findViewById(R.id.exam_spn);
                loadSpinnerData(rootView);
                spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String examName = String.valueOf(adapterView.getSelectedItem());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                EditText medium=(EditText)rootView.findViewById(R.id.med_txt);
                EditText date=(EditText)rootView.findViewById(R.id.date_txt);
                EditText city=(EditText)rootView.findViewById(R.id.city_txt);
                //send the examName , medium, date, city to the database
                break;
            case 1:
                ListView ls= (ListView) rootView.findViewById(R.id.upc_lst);
                RestAdapter api = new RestAdapter.Builder().setEndpoint(" http://a4477b74.ngrok.io/BunkMaar").build();
                Student_Main_Retro ai = api.create(Student_Main_Retro.class);
                ai.student_requests(new Callback<JsonElement>() {
                    @Override
                    public void success(JsonElement jsonElement, Response response) {
                        //get the requests from DB and add it to ExamNames and Request Status
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
                Upcoming_List up=new Upcoming_List(rootView.getContext(),ExamNames,Request_status);
                ls.setAdapter(up);
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView=null;
            switch(getArguments().getInt(ARG_SECTION_NUMBER)){
                case 0:
                    rootView = inflater.inflate(R.layout.request, container, false);
                    handle(rootView,getArguments().getInt(ARG_SECTION_NUMBER));
                    return rootView;
                case 1:
                    rootView = inflater.inflate(R.layout.upcoming, container, false);
                    handle(rootView,getArguments().getInt(ARG_SECTION_NUMBER));
                    return rootView;
                default:
            }
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "REQUEST";
                case 1:
                    return "UPCOMING";
            }
            return null;
        }
    }
}
