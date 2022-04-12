package com.flamby.citizz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.BreakIterator;

public class Login extends AppCompatActivity implements View.OnClickListener{

    Button noaccount_button;
    Button connect_button;

    //for database
    Connection connect;

    EditText mMail;
    EditText mPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        noaccount_button = findViewById(R.id.noaccount);
        connect_button = findViewById(R.id.connect);

        noaccount_button.setOnClickListener(this);
        connect_button.setOnClickListener(this);

        mMail = (EditText)findViewById(R.id.editText4);
        mPassword = (EditText)findViewById(R.id.editText);

        try {
            if (connectionauto()){
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean connectionauto()throws SQLException{
        ConnectionDatabaseHelper connectionHelper = new ConnectionDatabaseHelper();
        connect = connectionHelper.connectionclass("45.155.170.63", "project_oop", "postgres", "123456", "5432");
        if (connect != null) {
            try {
                FileInputStream in = openFileInput("auto.txt");
                BufferedReader br= new BufferedReader(new InputStreamReader(in));

                if (br.readLine().equals("True")){
                    FileInputStream in2 = openFileInput("save.txt");
                    BufferedReader br2= new BufferedReader(new InputStreamReader(in2));

                    String mail = br2.readLine();
                    String password = br2.readLine();

                    String query = "SELECT * FROM account " + "WHERE mail = '" + mail + "' AND password = '" + password + "';";
                    Statement st = connect.createStatement();
                    ResultSet rs = st.executeQuery(query);

                    int i = 0;
                    while (rs.next()){
                        i++;
                    }

                    if (i == 1){
                        connect.close();
                        return true;
                    }
                    br2.close();
                }
                br.close();
            }
            catch (Exception ignored) {
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.noaccount:
                //setContentView(R.layout.create_account_activity);
                Intent intent = new Intent(this, CreateAccount.class);
                startActivity(intent);
                break;
            case R.id.connect:
                try {
                    if (database()){
                        intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        intent = new Intent(this, Login.class);
                        startActivity(intent);
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
        }
    }

    public boolean database() throws SQLException {
        ConnectionDatabaseHelper connectionHelper = new ConnectionDatabaseHelper();
        connect = connectionHelper.connectionclass("45.155.170.63", "project_oop", "postgres", "123456", "5432");
        if (connect != null) {
            String query = "SELECT * FROM account " + "WHERE mail = '" + mMail.getText().toString() + "' AND password = '" + mPassword.getText().toString() + "';";
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(query);
            int i = 0;
            while (rs.next()) {
                i++;
            }
            if (i == 1){
                try {
                    query = "SELECT * FROM account " + "WHERE mail = '" + mMail.getText().toString() + "' AND password = '" + mPassword.getText().toString() + "';";
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
            connect.close();
            return false;
        }
        else {
            String ConnectionResult = "Check Connection";
            System.out.println(ConnectionResult);
            return false;
        }
    }
}

