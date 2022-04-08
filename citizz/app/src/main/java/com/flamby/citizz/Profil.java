package com.flamby.citizz;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;


public class Profil extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EditText txtName, txtFirstName, txtMail, txtRegion, txtDepartment, txtCity;
    private Button btnSave;
    private TextView wName, wFirstName, wMail, wRegion, wDepartment, wCity;
    private RadioGroup rgGender;
    private ConstraintLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        initViews();

        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                initRegister();
            }
        });
    }

    private void initRegister(){
        Log.d(TAG,"initRegister");
        if(validateData()){
            showSnackBar();
        }
    }

    private void showSnackBar(){
        Log.d(TAG, "showSnackBar");
        wName.setVisibility(View.GONE);
        wFirstName.setVisibility(View.GONE);
        wMail.setVisibility(View.GONE);

        String name = txtName.getText().toString();
        String firstname = txtFirstName.getText().toString();
        String mail = txtMail.getText().toString();
        String region = txtRegion.getText().toString();
        String department = txtDepartment.getText().toString();
        String city = txtCity.getText().toString();
        String gender = "";
        switch (rgGender.getCheckedRadioButtonId()){
            case R.id.Male:
                gender = "Male";
                break;
            case R.id.Female:
                gender = "Female";
                break;
            default:
                gender = "Unknown";
                break;
        }
        String snackText = "Name: "+ name+"\n"+ "FirstName: "+ firstname+"\n"+"Email: " +mail+"\n"+"City :"+city+"\n"+"Department: "+department+"\n"+"Region: "+region+"\n"+"Gender: "+gender;

        Log.d(TAG, "showSnackBar : Snack Bar Text: "+snackText);

        Snackbar.make(parent,snackText, Snackbar.LENGTH_INDEFINITE).setAction("Dismiss", new View.OnClickListener(){
            @Override
            public void onClick(View v){
                txtName.setText("");
                txtMail.setText("");
                txtFirstName.setText("");
                txtRegion.setText("");
                txtDepartment.setText("");
                txtCity.setText("");
            }
        }).show();
    }


    private boolean validateData(){
        Log.d(TAG, "validateData");
        if(txtName.getText().toString().equals("")) {
            wName.setVisibility((View.VISIBLE));
            wName.setText("Enter your Name");
            return false;
        }
        if(txtFirstName.getText().toString().equals("")) {
            wFirstName.setVisibility((View.VISIBLE));
            wFirstName.setText("Enter your FirstName");
            return false;
        }
        if(txtMail.getText().toString().equals("")) {
            wMail.setVisibility((View.VISIBLE));
            wMail.setText("Enter your Email");
            return false;
        }
        if(txtDepartment.getText().toString().equals("")) {
            wDepartment.setVisibility((View.VISIBLE));
            wDepartment.setText("Enter your Department");
            return false;
        }
        if(txtRegion.getText().toString().equals("")) {
            wRegion.setVisibility((View.VISIBLE));
            wRegion.setText("Enter your Region");
            return false;
        }
        if(txtCity.getText().toString().equals("")) {
            wCity.setVisibility((View.VISIBLE));
            wCity.setText("Enter your City");
            return false;
        }

        return true;
    }


    private void initViews() {
        Log.d(TAG, "initViews");

        txtName = findViewById(R.id.Name);
        txtFirstName = findViewById(R.id.FirstName);
        txtMail = findViewById(R.id.Mail);
        txtRegion = findViewById(R.id.Region);
        txtDepartment = findViewById(R.id.Department);
        txtCity = findViewById(R.id.City);

        btnSave = findViewById(R.id.Save);

        wName = findViewById(R.id.wName);
        wFirstName = findViewById(R.id.wFirstName);
        wMail = findViewById(R.id.wMail);
        wRegion = findViewById(R.id.wRegion);
        wDepartment = findViewById(R.id.wDepartment);
        wCity = findViewById(R.id.wCity);

        rgGender = findViewById(R.id.Button);

        parent = findViewById(R.id.parent);
    }
}