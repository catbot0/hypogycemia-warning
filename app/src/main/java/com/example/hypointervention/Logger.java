package com.example.hypointervention;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    private static final String TAG = "Logger";

    public void appendLog(String text)
    {
        File path = Environment.getExternalStorageDirectory(); //con.getExternalFilesDir(null);
        String root = path.toString();
        Log.i(TAG, "Root: " + root.toString());

        File File = new File(root, "log.log");
        String logFile = File.toString();
        Log.i(TAG, "File: " + logFile.toString());

        //File logFile = new File("/LOG.log");
        if (!File.exists())
        {
            try
            {
                boolean fileCreated = File.createNewFile();
                if(fileCreated){
                Log.i(TAG, "File created");
                }
                else {
                    Log.i(TAG, "File exists");
                }
                } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        try
        {
            //BufferedWriter for performance, true to set append to file flag
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
            Log.i(TAG, "BufferedWriter created");
            buf.append(text);
            Log.i(TAG, "Text appended");
            buf.newLine();
            buf.close();
        }
        catch (IOException e)
        {
            Log.e("Exception", "File write failed");
            e.printStackTrace();
        }
    }
}
