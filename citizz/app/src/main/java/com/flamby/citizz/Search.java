package com.flamby.citizz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Search extends AppCompatActivity implements View.OnClickListener{

    Button Search_button;

    EditText txtregion_report, txtdepartment_report, txtcity_report;
    Spinner mySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);

        Search_button = findViewById(R.id.button);

        Search_button.setOnClickListener(this);

        mySpinner = findViewById(R.id.spinner_report);

        txtregion_report = findViewById(R.id.Region);
        txtdepartment_report = findViewById(R.id.Department);
        txtcity_report = findViewById(R.id.City);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                if (validateData()){
                    try {
                        search();
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                    } catch (IOException e) {
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                    }
                }
                else{
                    Intent intent = new Intent(this, Search.class);
                    startActivity(intent);
                }
                break;
        }
    }

    private boolean validateData(){
        if(txtregion_report.getText().toString().equals("")) {
            return false;
        }
        if(txtdepartment_report.getText().toString().equals("")) {
            return false;
        }
        if(txtcity_report.getText().toString().equals("")) {
            return false;
        }
        return true;
    }

    public void search() throws IOException {
        FileOutputStream out = this.openFileOutput("search.txt", MODE_PRIVATE);
        out.write("True".getBytes());
        out.write("\n".getBytes());

        String text = mySpinner.getSelectedItem().toString();

        String mnemonic;

        switch (text) {
            case "Paid work":
                mnemonic = "advert2";
                break;
            case "Accident":
                mnemonic = "advert1";
                break;
            case "Events":
                mnemonic = "advert4";
                break;
            default:
                mnemonic = "advert3";
                break;
        }
        out.write(mnemonic.getBytes());
        out.write("\n".getBytes());
        out.write(txtregion_report.getText().toString().getBytes());
        out.write("\n".getBytes());
        out.write(txtdepartment_report.getText().toString().getBytes());
        out.write("\n".getBytes());
        out.write(txtcity_report.getText().toString().getBytes());
        out.close();
    }

}
