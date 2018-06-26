package com.example.y.photographu;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Util {
    private HandleResponse handleResponse;
    public Util() {
    }
    
    public interface HandleResponse {
        void handleResponses(Response response) throws IOException;
    }

    public void setHandleResponse(HandleResponse handleResponse) {
        this.handleResponse = handleResponse;
    }

    public void savaDataToSharedPreference(){

    }



    public void save(String data, Context context) {
        FileOutputStream out;
        BufferedWriter writer = null;
        try {
            out = context.openFileOutput("userFile", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public String read(Context context) {
        FileInputStream inputStream;
        BufferedReader reader = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            inputStream = context.openFileInput("userFile");
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null)
                stringBuilder.append(line);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }


    public void doPost(AppCompatActivity context, final Request request) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    }
                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        handleResponse(response);
                    }
                });
            }
        });
    }

    private void handleResponse(Response response) throws IOException {
        if (handleResponse != null) {
            handleResponse.handleResponses(response);
        }
    }
}
