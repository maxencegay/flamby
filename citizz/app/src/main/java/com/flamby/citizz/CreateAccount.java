package com.flamby.citizz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Console;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateAccount extends AppCompatActivity implements View.OnClickListener{

    Button main_button;

    //for database
    Connection connect;

    EditText mName;
    EditText mFirstName;
    EditText mMail;
    EditText mCity;
    EditText mDepartment;
    EditText mRegion;
    EditText meditText2;
    EditText meditText3;
    String Password;
    String gender;
    RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account_activity);

        main_button = findViewById(R.id.Create);

        main_button.setOnClickListener(this);

        mName  = (EditText)findViewById(R.id.Name);
        mFirstName = (EditText)findViewById(R.id.FirstName);
        mMail = (EditText)findViewById(R.id.Mail);
        mCity = (EditText)findViewById(R.id.City);
        mDepartment = (EditText)findViewById(R.id.Department);
        mRegion = (EditText)findViewById(R.id.Region);
        meditText2 = (EditText)findViewById(R.id.editText2);
        meditText3 = (EditText)findViewById(R.id.editText3);

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Create:
                System.out.println(validateData());
                if (validateData()){
                    try {
                        if (database()){
                            Intent intent = new Intent(this, MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Intent intent = new Intent(this, CreateAccount.class);
                            startActivity(intent);
                        }

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                else{
                    Intent intent = new Intent(this, CreateAccount.class);
                    startActivity(intent);
                }
                break;
        }
    }

    //for database
    public boolean database() throws SQLException {

        ConnectionDatabaseHelper connectionHelper = new ConnectionDatabaseHelper();
        connect = connectionHelper.connectionclass("45.155.170.63", "project_oop", "postgres", "123456", "5432");
        if (connect != null) {
            String query = "SELECT * FROM account " + "WHERE mail = '" + mMail.getText().toString() + "' AND password = '" + Password + "';";
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(query);
            int i = 0;
            while (rs.next()) {
                i++;
            }
            if (i == 0) {
                try {
                    query = "INSERT INTO account (id, name, fam_name, mail, password, city, region, department, gender) " +
                            "VALUES (nextval('account_id'), '" + mName.getText().toString() + "', '" + mFirstName.getText().toString() + "', '" + mMail.getText().toString() + "', '" + Password + "', '" + mCity.getText().toString() + "', '" + mRegion.getText().toString() + "', '" + mDepartment.getText().toString() + "', '" + gender + "');";
                    st = connect.createStatement();
                    rs = st.executeQuery(query);
                    return true;
                } catch (SQLException e) {
                    try {
                        query = "SELECT * FROM account " + "WHERE mail = '" + mMail.getText().toString() + "' AND password = '" + Password + "';";
                        st = connect.createStatement();
                        rs = st.executeQuery(query);

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
                    return true;
                }
            }
            connect.close();
            return false;
        }
        else {
            String ConnectionResult = "Check Connection";
            System.out.println(ConnectionResult);
            return false;
        }
    }


    private boolean validateData(){
        if(mRegion.getText().toString().equals("")) {
            return false;
        }
        if(mDepartment.getText().toString().equals("")) {
            return false;
        }
        if(mFirstName.getText().toString().equals("")) {
            return false;
        }
        if(mName.getText().toString().equals("")) {
            return false;
        }
        if(mCity.getText().toString().equals("")) {
            return false;
        }
        if(mMail.getText().toString().equals("")) {
            return false;
        }
        if(meditText2.getText().toString().equals("")) {
            return false;
        }
        if(!meditText3.getText().toString().equals(meditText2.getText().toString())){
            return false;
        }
        gender = "";
        switch (radioGroup.getCheckedRadioButtonId()){
            case R.id.Male:
                gender = "Male";
                break;
            case R.id.Female:
                gender = "Female";
                break;
        }
        if(gender == ""){
            return false;
        }

        Password = meditText3.getText().toString();

        return true;
    }
}
