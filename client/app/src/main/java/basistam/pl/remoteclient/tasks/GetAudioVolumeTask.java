package basistam.pl.remoteclient.tasks;

import android.os.AsyncTask;
import android.widget.SeekBar;

import java.io.IOException;

import basistam.pl.remoteclient.retrofit.AudioRetrofit;
import retrofit2.Call;

public class GetAudioVolumeTask extends AsyncTask<Void, Void, Integer> {

    private final SeekBar volumeBar;
    private final AudioRetrofit audioRetrofit;

    public GetAudioVolumeTask(SeekBar volumeBar, AudioRetrofit audioRetrofit) {
        this.volumeBar = volumeBar;
        this.audioRetrofit = audioRetrofit;
    }


    @Override
    protected Integer doInBackground(Void... params) {
        try {
            Call<Integer> request = audioRetrofit.getVolumeRequest();
            return request.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    protected void onPostExecute(Integer result) {
        if (result != -1)
            volumeBar.setProgress(result);
    }
}
