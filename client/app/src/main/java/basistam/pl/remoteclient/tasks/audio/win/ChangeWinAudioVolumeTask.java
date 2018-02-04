package basistam.pl.remoteclient.tasks.audio.win;

import android.os.AsyncTask;

import java.io.IOException;

import basistam.pl.remoteclient.retrofit.AudioRetrofit;
import retrofit2.Call;

public class ChangeWinAudioVolumeTask extends AsyncTask<String, Void, Void> {

    private final AudioRetrofit audioRetrofit;

    public ChangeWinAudioVolumeTask(AudioRetrofit audioRetrofit) {
        this.audioRetrofit = audioRetrofit;
    }

    @Override
    protected Void doInBackground(String... params) {
        try {
            if (params.length != 0) {
                String dir = params[0];
                Call<String> request = audioRetrofit.setWindowsVolumeRequest(dir);
                request.execute();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
