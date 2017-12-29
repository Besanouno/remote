package basistam.pl.remoteclient.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import basistam.pl.remoteclient.retrofit.AudioRetrofit;
import basistam.pl.remoteclient.tasks.ChangeAudioVolumeTask;
import basistam.pl.remoteclient.tasks.ChangeSpeakersStatusTask;
import basistam.pl.remoteclient.tasks.GetAudioVolumeTask;
import basistam.pl.remoteclient.tasks.GetSpeakersStatusTask;
import basistam.pl.remoteclient.utils.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AudioServiceImpl implements AudioService {

    private final AudioRetrofit audioRetrofit;
    private final String BASE_URL = "http://192.168.1.3:5000/";

    public AudioServiceImpl() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        audioRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(AudioRetrofit.class);
    }

    @Override
    public void setAudioVolume(final int volume) {
        new ChangeAudioVolumeTask(audioRetrofit).execute(volume);
    }

    @Override
    public void getAudioVolumeAndCall(final Callback<Integer> callback) {
        new GetAudioVolumeTask(audioRetrofit, callback).execute();
    }

    @Override
    public void setSpeakersStatus(String status) {
        new ChangeSpeakersStatusTask(audioRetrofit).execute(status);
    }

    @Override
    public void getSpeakersStatusAndCall(Callback<String> callback) {
        new GetSpeakersStatusTask(audioRetrofit, callback).execute();
    }
}
