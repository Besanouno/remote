package basistam.pl.remoteclient.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import basistam.pl.remoteclient.retrofit.AudioRetrofit;
import basistam.pl.remoteclient.service.address.AddressReader;
import basistam.pl.remoteclient.tasks.audio.ChangeAudioVolumeTask;
import basistam.pl.remoteclient.tasks.audio.ChangeSpeakersStatusTask;
import basistam.pl.remoteclient.tasks.audio.GetAudioVolumeTask;
import basistam.pl.remoteclient.tasks.audio.GetSpeakersStatusTask;
import basistam.pl.remoteclient.tasks.audio.SkipTask;
import basistam.pl.remoteclient.utils.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AudioServiceImpl implements AudioService {

    private final AudioRetrofit audioRetrofit;

    public AudioServiceImpl(AddressReader addressReader) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        audioRetrofit = new Retrofit.Builder()
                .baseUrl(addressReader.getFullAddress())
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

    @Override
    public void next() {
        new SkipTask(audioRetrofit).execute("next");
    }

    @Override
    public void prev() {
        new SkipTask(audioRetrofit).execute("prev");
    }
}
