package com.example.monika.patient_r;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity
{


    EditText obj_name,obj_lname,obj_email,obj_pass,obj_add,obj_num,obj_adhar,obj_dob,
            obj_doc1,obj_doc2,obj_doc3,obj_doc4,obj_doc5,obj_pin,obj_enum,obj_age;

    TextView obj_text_email;

    RadioButton m, f;
    View date;
    String gender;
    Spinner obj_bg;
    Button obj_submit,obj_login;

    /*for validating Birthdate*/
    private Pattern pattern;
    private Matcher matcher_bday;
    private static final String DATE_PATTERN =
            "((19|20)\\d\\d)[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])";//yyyy/mm/dd
            //"(0[1-9]|1[012])[/.-](0[1-9]|[12][0-9]|3[01])[/.-]((19|20)\\d\\d)";
           //"^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d{2}$"; //mm/dd/yyyy vallid pattern


    /*for validating email id*/

    private Matcher matcher_email;
    private static final String EMAIL_PATTERN =
    "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            //"[a-zA-Z0-9._-]+@[a-z]+\\\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/



        obj_name= (EditText) findViewById(R.id.firstname);
        obj_lname= (EditText) findViewById(R.id.lastname);
        obj_email= (EditText) findViewById(R.id.emailid);
        obj_text_email= (TextView) findViewById(R.id.email);
        obj_pass= (EditText) findViewById(R.id.password);
        obj_add= (EditText) findViewById(R.id.address);
        obj_pin= (EditText) findViewById(R.id.pincode);
        obj_num= (EditText) findViewById(R.id.mobile_no);
        obj_enum= (EditText) findViewById(R.id.emergency_no);
        obj_adhar= (EditText) findViewById(R.id.adhar_id);
        obj_dob=(EditText) findViewById(R.id.dob);
        obj_age= (EditText) findViewById(R.id.age);
        obj_bg= (Spinner) findViewById(R.id.spinner);
        obj_doc1=(EditText) findViewById(R.id.d1);
        obj_doc2=(EditText) findViewById(R.id.d2);
        obj_doc3=(EditText) findViewById(R.id.d3);
        obj_doc4=(EditText) findViewById(R.id.d4);
        obj_doc5=(EditText) findViewById(R.id.d5);


        m= (RadioButton) findViewById(R.id.male);
        f= (RadioButton) findViewById(R.id.female);

        obj_submit= (Button) findViewById(R.id.submit);
        obj_login= (Button) findViewById(R.id.log);

        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender="M";
            }
        });

        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender="F";
            }
        });


        obj_submit.setOnClickListener(new View.OnClickListener()
        {


            @Override
            public void onClick(View v) {

                matcher_bday = Pattern.compile(DATE_PATTERN).matcher(obj_dob.getText().toString());//for bday validation
                matcher_email= Pattern.compile(EMAIL_PATTERN).matcher(obj_email.getText().toString());//for email validation
               //if(obj_name.equals(""))
                 //   Toast.makeText(MainActivity.this,"Enter First Name",Toast.LENGTH_SHORT).show();

                if(obj_name.getText().toString().length()==0){
                    obj_name.setError("First name not entered");
                    obj_name.requestFocus();
                }
                if(obj_lname.getText().toString().length()==0){
                    obj_lname.setError("Last name not entered");
                    obj_lname.requestFocus();
                }

                if(obj_email.getText().toString().length()==0){
                    obj_email.setError("Email not entered");
                    obj_email.requestFocus();
                }

                 if (!matcher_email.matches()) {

                    Toast.makeText(MainActivity.this, "Invalid email id", Toast.LENGTH_SHORT).show();
                }
                if(obj_pass.getText().toString().length()==0){
                    obj_pass.setError("Password not entered");
                    obj_pass.requestFocus();
                }

               if(obj_add.getText().toString().length()==0){
                    obj_add.setError("address not entered");
                    obj_add.requestFocus();
                }

                if(obj_pin.getText().toString().length()==0){
                    obj_pin.setError("pincode not entered");
                    obj_pin.requestFocus();
                }
              if(obj_num.getText().toString().length()==0){
                    obj_num.setError("Mobile number is not entered");
                    obj_num.requestFocus();
                }
                if(obj_num.getText().toString().length()!=10){
                    Toast.makeText(MainActivity.this,"Mobile number should be of 10 digits",Toast.LENGTH_SHORT).show();

                }


                if(obj_enum.getText().toString().length()==0){
                    obj_enum.setError("emergency number is not entered");
                    obj_enum.requestFocus();
                }

                if(obj_enum.getText().toString().length()!=10){
                    Toast.makeText(MainActivity.this,"Emergency number should be of 10 digits",Toast.LENGTH_SHORT).show();

                }

               if(obj_adhar.getText().toString().length()==0){
                    obj_adhar.setError("Aadhar number not entered");
                    obj_adhar.requestFocus();
                }

                if(obj_adhar.getText().toString().length()!=12){
                    Toast.makeText(MainActivity.this,"Adhar number should be of 12 digits",Toast.LENGTH_SHORT).show();

                }
               if(obj_dob.getText().toString().length()==0){
                    obj_dob.setError("Date of Birth not entered");
                    obj_dob.requestFocus();
                }


                if (!matcher_bday.matches()) {

                        Toast.makeText(MainActivity.this, "Birthday format should be yyyy/mm/dd!", Toast.LENGTH_SHORT).show();
                }

                if(!(m.isChecked()) && !(f.isChecked()))
                {
                    Toast.makeText(MainActivity.this,"Select Gender",Toast.LENGTH_LONG).show();
                }

                if (obj_bg.getSelectedItem().toString().trim().equals("Select one")) {
                    Toast.makeText(MainActivity.this, "Enter blood group", Toast.LENGTH_SHORT).show();
                }

               if(obj_age.getText().toString().length()==0){
                    obj_age.setError("age not entered");
                    obj_age.requestFocus();
                }


                if(obj_doc1.getText().toString().length()==0){
                    obj_doc1.setError("doctor name not entered");
                    obj_doc1.requestFocus();
                }

               if(obj_doc2.getText().toString().length()==0){
                    obj_doc2.setError("doctor name not entered");
                    obj_doc2.requestFocus();
                }

               if(obj_doc3.getText().toString().length()==0){
                    obj_doc3.setError("doctor name not entered");
                    obj_doc3.requestFocus();
                }

                if(obj_doc4.getText().toString().length()==0){
                    obj_doc4.setError("doctor name not entered");
                    obj_doc4.requestFocus();
                }

                if(obj_doc5.getText().toString().length()==0){
                    obj_doc5.setError("doctor name not entered");
                    obj_doc5.requestFocus();
                }
                /*if(gender.toString().length()==0){
                    Toast.makeText(MainActivity.this,"Select Gender",Toast.LENGTH_SHORT).show();
                }
                /*if(gender.equals("")) {
                    Toast.makeText(MainActivity.this,"Select Gender",Toast.LENGTH_SHORT).show();
                }*/
              else {
                    publishData();
                   Intent i = new Intent(MainActivity.this, ConfirmActivity.class);
                   startActivity(i);
               }

                }


        }


        );

        obj_login.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    public void publishData() {

            final RequestQueue adddata;
            adddata= Volley.newRequestQueue(this);
            String url="http://eitraproject.ml/telehealth/patient_reg.php?"+
                    "aadhar_id="+obj_adhar.getText().toString()+
                    "&password="+obj_pass.getText().toString()+
                    "&first_name="+obj_name.getText().toString()+
                    "&last_name="+obj_lname.getText().toString()+
                    "&email_id="+obj_email.getText().toString()+
                    "&mobile_no="+obj_num.getText().toString()+
                    "&emergency_ph_no="+obj_enum.getText().toString()+
                    "&address="+obj_add.getText().toString()+
                    "&pincode="+obj_pin.getText().toString()+
                    "&dob="+obj_dob.getText().toString()+
                  //  "&age="+obj_age.getText().toString()+
                    "&gender="+gender.toString()+
                    "&blood_group="+obj_bg.getSelectedItem().toString()+
                    "&doc1="+obj_doc1.getText().toString()+
                    "&doc2="+obj_doc2.getText().toString()+
                    "&doc3="+obj_doc3.getText().toString()+
                    "&doc4="+obj_doc4.getText().toString()+
                    "&doc5="+obj_doc5.getText().toString();
        Toast.makeText(MainActivity.this,url,Toast.LENGTH_LONG).show();

        final StringRequest pushdata=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
              public void onResponse(String response) {
                    Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener(){


            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

        };
        adddata.add(pushdata);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
