package basistam.pl.remoteclient.tasks;

import android.os.AsyncTask;
import android.widget.SeekBar;

import java.io.IOException;

import basistam.pl.remoteclient.retrofit.AudioRetrofit;
import basistam.pl.remoteclient.utils.Callback;
import retrofit2.Call;

public class GetAudioVolumeTask extends AsyncTask<Void, Void, Integer> {

    private final AudioRetrofit audioRetrofit;
    private final Callback<Integer> callback;

    public GetAudioVolumeTask(AudioRetrofit audioRetrofit, Callback<Integer> callback) {
        this.audioRetrofit = audioRetrofit;
        this.callback = callback;
    }


    @Override
    protected Integer doInBackground(Void... params) {
        try {
            Call<Integer> request = audioRetrofit.getVolumeRequest();
            return request.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Integer result) {
        if (result != null)
            callback.call(result);
    }
}
