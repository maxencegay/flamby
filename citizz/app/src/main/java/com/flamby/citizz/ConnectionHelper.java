package com.flamby.citizz;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {

    public Connection connectionclass(String ip, String database, String user, String pass, String port){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String url = null;

        try{
            Class.forName("org.postgresql.Driver");
            url = "jdbc:postgresql://" + ip + ":" + port + "/" + database;
            connection = DriverManager.getConnection(url, user, pass);
        }
        catch (Exception ex){
            Log.e("Error ", ex.getMessage());
        }

        return connection;
    }
}
