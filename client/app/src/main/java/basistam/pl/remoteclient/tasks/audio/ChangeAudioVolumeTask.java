package basistam.pl.remoteclient.tasks.audio;

import android.os.AsyncTask;

import java.io.IOException;

import basistam.pl.remoteclient.retrofit.AudioRetrofit;
import retrofit2.Call;

public class ChangeAudioVolumeTask extends AsyncTask<Integer, Void, Void> {

    private final AudioRetrofit audioRetrofit;

    public ChangeAudioVolumeTask(AudioRetrofit audioRetrofit) {
        this.audioRetrofit = audioRetrofit;
    }

    @Override
    protected Void doInBackground(Integer... params) {
        try {
            if (params.length != 0) {
                int volume = params[0];
                Call<Integer> request = audioRetrofit.setVolumeRequest(volume);
                request.execute();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
