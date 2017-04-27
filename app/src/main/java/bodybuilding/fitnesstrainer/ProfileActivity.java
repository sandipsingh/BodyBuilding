package bodybuilding.fitnesstrainer;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import database.DatabaseHandler;
import model.PersonDetails;

public class ProfileActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Toolbar toolbar;
    ImageView btn_back;
    TextView txt_title;
    EditText txt_first_name, txt_lastname, txt_monno, txt_weight, txt_hieght, txt_email, txt_gender;
    String first_name, lastname, monno, dob, weight, hieght, email, gender, w_sign, h_sign;
    String s_first_name, s_lastname, s_monno, s_dob, s_weight, s_hieght, s_email, s_gender, s_w_sign, s_h_sign;
    Button btn_save;
    static final int DATE_DIALOG_ID = 999;
    private int myear;
    private int mmonth;
    private int mday;
    TextView txt_dob;
    int id = 1;
    //    List<PersonDetails> list_personDetails;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    ArrayAdapter<String> dataAdapter;
    ArrayAdapter<String> dataAdapter_ht;

    Spinner spinner_wt;
    Spinner spinner_ht;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        list_personDetails = new ArrayList<>();
        setContentView(R.layout.activity_profile);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        btn_back = (ImageView) findViewById(R.id.btn_back);
        txt_title = (TextView) findViewById(R.id.title);
        txt_dob = (TextView) findViewById(R.id.dob);
        txt_email = (EditText) findViewById(R.id.email);
        txt_first_name = (EditText) findViewById(R.id.first_name);
        txt_lastname = (EditText) findViewById(R.id.last_name);
        txt_hieght = (EditText) findViewById(R.id.hieght);
        txt_weight = (EditText) findViewById(R.id.weight);
        txt_monno = (EditText) findViewById(R.id.mobile);
        btn_save = (Button) findViewById(R.id.btn_submit);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        txt_title.setText("Profile");
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        spinner_wt = (Spinner) findViewById(R.id.spinner_wt);
        spinner_ht = (Spinner) findViewById(R.id.spinner_ht);

        // Spinner click listener
        spinner_wt.setOnItemSelectedListener(this);
        spinner_ht.setOnItemSelectedListener(this);
//        final DatabaseHandler db = new DatabaseHandler(this);


        setCurrentDateOnView();
// set dob
        txt_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        // Spinner Drop down elements
        List<String> list_wt = new ArrayList<String>();
        list_wt.add("KG");
        list_wt.add("LBS");

        // Creating adapter for spinner
        dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_layout, list_wt);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        spinner_wt.setAdapter(dataAdapter);


        // Spinner Drop down elements
        List<String> list_ht = new ArrayList<String>();
        list_ht.add("Cm");
        list_ht.add("Ft");

        // Creating adapter for spinner
        dataAdapter_ht = new ArrayAdapter<String>(this, R.layout.spinner_layout, list_ht);
        // Drop down layout style - list view with radio button
        dataAdapter_ht.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        spinner_ht.setAdapter(dataAdapter_ht);

        File file = new File("/data/data/bodybuilding.fitnesstrainer/shared_prefs/pref.xml");
        if (file.exists()) {
            showdata();
            s_w_sign = preferences.getString("w_sign", null);
            s_h_sign = preferences.getString("h_sign", null);
            if (s_h_sign!=null) {
                int spinnerPosition = dataAdapter_ht.getPosition(s_h_sign);
                spinner_ht.setSelection(spinnerPosition);
            }
            if (s_w_sign!=null) {
                int spinnerPosition = dataAdapter.getPosition(s_w_sign);
                spinner_wt.setSelection(spinnerPosition);
            }
        } else {

        }

       /* txt_first_name.setText("" + list_personDetails.get(0).getF_name());
        txt_lastname.setText("" + list_personDetails.get(0).getL_name());
        txt_email.setText("" + list_personDetails.get(0).getEmail());
        txt_dob.setText("" + list_personDetails.get(0).getDob());
        txt_monno.setText("" + list_personDetails.get(0).getMob_no());
        txt_weight.setText("" + list_personDetails.get(0).getWeight());
        txt_hieght.setText("" + list_personDetails.get(0).getHieght());
        System.out.println("======person  name" + list_personDetails.get(0).getF_name());
        System.out.println("======person last name" + list_personDetails.get(0).getL_name());
        System.out.println("======person  email" + list_personDetails.get(0).getEmail());
        System.out.println("======person  dob" + list_personDetails.get(0).getDob());
        System.out.println("======person  mobile" + list_personDetails.get(0).getMob_no());
        System.out.println("======person  wieght" + list_personDetails.get(0).getWeight());
        System.out.println("======person  hieght" + list_personDetails.get(0).getHieght());
        PersonDetails personDetails=new PersonDetails();
        list_personDetails = db.getAllContacts();
        for (int i=0;i<list_personDetails.size();i++){
            txt_first_name.setText("" + list_personDetails.get(0).getF_name());
            txt_lastname.setText("" + list_personDetails.get(0).getL_name());
            txt_email.setText("" + list_personDetails.get(0).getEmail());
            txt_dob.setText("" + list_personDetails.get(0).getDob());
            txt_monno.setText("" + list_personDetails.get(0).getMob_no());
            txt_weight.setText("" + list_personDetails.get(0).getWeight());
            txt_hieght.setText("" + list_personDetails.get(0).getHieght());
            System.out.println("======person  name" + list_personDetails.get(0).getF_name());
            System.out.println("======person last name" + list_personDetails.get(0).getL_name());
            System.out.println("======person  email" + list_personDetails.get(0).getEmail());
            System.out.println("======person  dob" + list_personDetails.get(0).getDob());
            System.out.println("======person  mobile" + list_personDetails.get(0).getMob_no());
            System.out.println("======person  wieght" + list_personDetails.get(0).getWeight());
            System.out.println("======person  hieght" + list_personDetails.get(0).getHieght());
        }*/
//


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                list_personDetails.clear();
                first_name = txt_first_name.getText().toString();
                lastname = txt_lastname.getText().toString();
                monno = txt_monno.getText().toString();
                email = txt_email.getText().toString();
                hieght = txt_hieght.getText().toString();
                weight = txt_weight.getText().toString();
                dob = txt_dob.getText().toString();
                preferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
                editor = preferences.edit();
                editor.putString("first_name", first_name);
                editor.putString("lastname", lastname);
                editor.putString("email", email);
                editor.putString("dob", dob);
                editor.putString("monno", monno);
                editor.putString("weight", weight);
                editor.putString("w_sign", w_sign);
                editor.putString("hieght", hieght);
                editor.putString("h_sign", h_sign);
                editor.commit();

                showdata();
                Toast.makeText(getApplicationContext(),"Save successfully",Toast.LENGTH_LONG).show();
//                db.addContact(new PersonDetails(id, first_name, lastname, email, dob, monno, weight, w_sign, hieght, h_sign));
//
//
//                db.updateContact(new PersonDetails(id, first_name, lastname, email, dob, monno, weight, w_sign, hieght, h_sign));
//                list_personDetails = db.getAllContacts();
//                PersonDetails personDetails=new PersonDetails();
                /*for (int i=0;i<list_personDetails.size();i++) {
                    txt_first_name.setText("" + list_personDetails.get(0).getF_name());
                    txt_lastname.setText("" + list_personDetails.get(0).getL_name());
                    txt_email.setText("" + list_personDetails.get(0).getEmail());
                    txt_dob.setText("" + list_personDetails.get(0).getDob());
                    txt_monno.setText("" + list_personDetails.get(0).getMob_no());
                    txt_weight.setText("" + list_personDetails.get(0).getWeight());
                    txt_hieght.setText("" + list_personDetails.get(0).getHieght());
                }
*/


            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                DatePickerDialog _date = new DatePickerDialog(this, datePickerListener, myear, mmonth,
                        mday) {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if (year > myear)
                            view.updateDate(myear, mmonth, mday);

                        if (monthOfYear > mmonth && year == myear)
                            view.updateDate(myear, mmonth, mday);

                        if (dayOfMonth > mday && year == myear && monthOfYear == mmonth)
                            view.updateDate(myear, mmonth, mday);

                    }
                };
                return _date;
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            myear = selectedYear;
            mmonth = selectedMonth;
            mday = selectedDay;

            // set selected date into textview
            txt_dob.setText(new StringBuilder().append(mmonth + 1)
                    .append("-").append(mday).append("-").append(myear)
                    .append(" "));

        }
    };


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        switch (parent.getId()) {
            case R.id.spinner_wt:
                //Your Action Here.

                w_sign = parent.getItemAtPosition(position).toString();

                break;
            case R.id.spinner_ht:
                //Your Another Action Here.
                h_sign = parent.getItemAtPosition(position).toString();

                break;
        }

    }

    // display current date
    public void setCurrentDateOnView() {


        final Calendar c = Calendar.getInstance();
        myear = c.get(Calendar.YEAR);
        mmonth = c.get(Calendar.MONTH);
        mday = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview
//        txt_dob.setText(new StringBuilder()
//                // Month is 0 based, just add 1
//                .append(mmonth + 1).append("/").append(mday).append("/")
//                .append(myear).append(" "));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void showdata() {
        preferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        s_first_name = preferences.getString("first_name", null);
        s_lastname = preferences.getString("lastname", null);
        s_email = preferences.getString("email", null);
        s_dob = preferences.getString("dob", null);
        s_monno = preferences.getString("monno", null);
        s_weight = preferences.getString("weight", null);
        s_hieght = preferences.getString("hieght", null);

        System.out.println("======person insert name" + s_first_name);
        System.out.println("======person insert name" + s_lastname);
        System.out.println("======person insert email" + s_email);
        System.out.println("======person insert dob" + s_dob);
        System.out.println("======person insert mobile" + s_monno);
        System.out.println("======person insert wieght" + s_weight);
        System.out.println("======person insert wt_sign" + s_w_sign);
        System.out.println("======person insert hieght" + s_hieght);
        System.out.println("======person insert h_sign" + s_h_sign);
        txt_first_name.setText("" + s_first_name);
        txt_lastname.setText("" + s_lastname);
        txt_email.setText("" + s_email);
        txt_dob.setText("" + s_dob);
        txt_monno.setText("" + s_monno);
        txt_weight.setText("" + s_weight);
        txt_hieght.setText("" + s_hieght);
    }
}
