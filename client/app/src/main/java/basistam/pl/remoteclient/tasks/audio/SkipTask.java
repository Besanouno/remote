package basistam.pl.remoteclient.tasks.audio;

import android.os.AsyncTask;

import java.io.IOException;

import basistam.pl.remoteclient.retrofit.AudioRetrofit;


public class SkipTask extends AsyncTask<String, Void, Void> {

    private final AudioRetrofit audioRetrofit;

    public SkipTask(AudioRetrofit audioRetrofit) {
        this.audioRetrofit = audioRetrofit;
    }

    @Override
    protected Void doInBackground(String... params) {
        String dir = null;
        if (params.length != 0) {
            dir = params[0];
        }
        try {
            audioRetrofit.skipRequest(dir).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
