package basistam.pl.remoteclient.tasks.audio;

import android.os.AsyncTask;

import java.io.IOException;

import basistam.pl.remoteclient.retrofit.AudioRetrofit;
import retrofit2.Call;

public class ChangeSpeakersStatusTask extends AsyncTask<String, Void, String> {

    private final AudioRetrofit audioRetrofit;

    public ChangeSpeakersStatusTask(AudioRetrofit audioRetrofit) {
        this.audioRetrofit = audioRetrofit;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            if (params.length != 0) {
                String status = params[0];
                Call<String> request = audioRetrofit.setSpeakersStatusRequest(status);
                request.execute();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
