package basistam.pl.remoteclient.tasks.audio.unix;

import android.os.AsyncTask;

import java.io.IOException;

import basistam.pl.remoteclient.retrofit.AudioRetrofit;
import basistam.pl.remoteclient.utils.Callback;
import retrofit2.Call;

public class GetSpeakersStatusTask extends AsyncTask<Void, Void, String> {

    private final AudioRetrofit audioRetrofit;
    private final Callback<String> callback;

    public GetSpeakersStatusTask(AudioRetrofit audioRetrofit, Callback<String> callback) {
        this.audioRetrofit = audioRetrofit;
        this.callback = callback;
    }

    @Override
    protected String doInBackground(Void... params) {
        Call<String> request = audioRetrofit.getUnixSpeakersStatusRequest();
        try {
            return request.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        callback.call(result);
    }
}
