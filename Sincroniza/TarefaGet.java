package com.example.project2.Sincroniza;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TarefaGet extends AsyncTask<Void, Void, String> {
 public TarefaInterface tarefaInterface;
    @Override
    protected String doInBackground(Void... strings) {
        StringBuffer buffer = new StringBuffer();
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL("http://172.16.1.78:8080/clientes");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setReadTimeout(15000);
            urlConnection.setConnectTimeout(15000);

            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String linha;
            while ((linha = reader.readLine()) != null) {
                buffer.append(linha);
                buffer.append("\n");

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }

    @Override
    protected void onPostExecute(String srtJson) {
        if(tarefaInterface!=null){
     tarefaInterface.carregarlistagem(srtJson);
    }}
}
