package basistam.pl.remoteclient.service;

import android.widget.SeekBar;

import basistam.pl.remoteclient.retrofit.AudioRetrofit;
import basistam.pl.remoteclient.tasks.ChangeAudioVolumeTask;
import basistam.pl.remoteclient.tasks.GetAudioVolumeTask;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AudioServiceImpl implements AudioService {

    private final AudioRetrofit audioRetrofit;
    private final String BASE_URL = "http://192.168.1.3:5000/";

    public AudioServiceImpl() {
        audioRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AudioRetrofit.class);
    }

    @Override
    public void setAudioVolume(final int volume) {
        new ChangeAudioVolumeTask(audioRetrofit).execute(volume);
    }

    @Override
    public void getAudioVolumeAndUpdate(final SeekBar volumeBar) {
        new GetAudioVolumeTask(audioRetrofit, volumeBar).execute();
    }
}
