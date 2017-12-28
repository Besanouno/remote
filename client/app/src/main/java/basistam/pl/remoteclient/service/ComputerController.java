package basistam.pl.remoteclient.service;

import android.os.AsyncTask;

import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ComputerController {

    private final ComputerConnectionService connectionService;
    private final String BASE_URL = "http://192.168.1.3:5000/";

    public ComputerController() {
        connectionService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ComputerConnectionService.class);
    }

    public void setAudioVolume(final int volume) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                try {
                    return connectionService
                            .changeVolume(volume)
                            .execute()
                            .body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return "";
            }

            @Override
            protected void onPostExecute(String result) {
                System.out.println(result);
            }
        }.execute();
    }
}
