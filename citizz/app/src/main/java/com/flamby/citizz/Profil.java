package com.flamby.citizz;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Profil extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EditText txtName, txtFirstName, txtMail, txtRegion, txtDepartment, txtCity;
    private Button btnSave;
    private TextView wName, wFirstName, wMail, wRegion, wDepartment, wCity;
    private RadioGroup rgGender;
    private ConstraintLayout parent;

    //for database
    Connection connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        initViews();

        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                initRegister(v);
            }
        });
    }

    private void initRegister(View view){
        Log.d(TAG,"initRegister");
        if(validateData()){
            //showSnackBar();
            switch (view.getId()){
                case R.id.Save:
                    try {
                        database();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }

    public void database() throws SQLException{
        ConnectionDatabaseHelper connectionHelper = new ConnectionDatabaseHelper();
        connect = connectionHelper.connectionclass("45.155.170.63", "project_oop", "postgres", "123456", "5432");
        if (connect != null) {
            String gender = "";
            switch (rgGender.getCheckedRadioButtonId()) {
                case R.id.Male:
                    gender = "Male";
                    break;
                case R.id.Female:
                    gender = "Female";
                    break;
            }


            try {
                FileInputStream in = openFileInput("save.txt");

                BufferedReader br= new BufferedReader(new InputStreamReader(in));

                br.readLine();
                br.readLine();

                String id = br.readLine();

                String query = "UPDATE account SET (name, fam_name, mail, city, region, department, gender) " +
                        "= ('" + txtFirstName.getText().toString() + "', '" + txtName.getText().toString() + "', '" + txtMail.getText().toString() + "', '" + txtCity.getText().toString() + "', '" + txtRegion.getText().toString() + "', '" + txtDepartment.getText().toString() + "', '" + gender + "') " +
                        "WHERE id = "+ id +";";
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);
                br.close();

            } catch (Exception e) {
            }


            try {
                FileInputStream in = openFileInput("save.txt");

                BufferedReader br= new BufferedReader(new InputStreamReader(in));

                br.readLine();
                br.readLine();

                String id = br.readLine();
                br.close();


                String query = "SELECT * FROM account WHERE id = '" + id + "';";
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);

                rs.next();


                FileOutputStream out = this.openFileOutput("save.txt", MODE_PRIVATE);

                out.write(rs.getString(4).getBytes());
                out.write("\n".getBytes());
                out.write(rs.getString(5).getBytes());
                out.write("\n".getBytes());
                out.write(rs.getString(1).getBytes());
                out.write("\n".getBytes());
                out.write(rs.getString(2).getBytes());
                out.write("\n".getBytes());
                out.write(rs.getString(3).getBytes());
                out.write("\n".getBytes());
                out.write(rs.getString(6).getBytes());
                out.write("\n".getBytes());
                out.write(rs.getString(7).getBytes());
                out.write("\n".getBytes());
                out.write(rs.getString(8).getBytes());
                out.write("\n".getBytes());
                out.write(rs.getString(9).getBytes());
                out.close();
            }
            catch (Exception ee) {
            }


            connect.close();
        }
        else {
            String ConnectionResult = "Check Connection";
            System.out.println(ConnectionResult);
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

        try {
            FileInputStream in = openFileInput("save.txt");

            BufferedReader br= new BufferedReader(new InputStreamReader(in));

            txtMail.setText(br.readLine());
            br.readLine();
            br.readLine();
            txtFirstName.setText(br.readLine());
            txtName.setText(br.readLine());
            txtCity.setText(br.readLine());
            txtRegion.setText(br.readLine());
            txtDepartment.setText(br.readLine());

            String gender = br.readLine();

            if (gender.equals("Male")){
                rgGender.check(R.id.Male);
            }
            if (gender.equals("Female")){
                rgGender.check(R.id.Female);
            }


        } catch (Exception e) {
        }
    }
}