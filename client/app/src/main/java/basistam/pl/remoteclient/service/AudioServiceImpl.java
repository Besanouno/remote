package basistam.pl.remoteclient.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import basistam.pl.remoteclient.retrofit.AudioRetrofit;
import basistam.pl.remoteclient.service.address.AddressReader;
import basistam.pl.remoteclient.tasks.audio.unix.ChangeUnixAudioVolumeTask;
import basistam.pl.remoteclient.tasks.audio.unix.ChangeSpeakersStatusTask;
import basistam.pl.remoteclient.tasks.audio.unix.GetAudioVolumeTask;
import basistam.pl.remoteclient.tasks.audio.unix.GetSpeakersStatusTask;
import basistam.pl.remoteclient.tasks.audio.SkipTask;
import basistam.pl.remoteclient.tasks.audio.win.ChangeWinAudioVolumeTask;
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
    public void setUnixAudioVolume(final int volume) {
        new ChangeUnixAudioVolumeTask(audioRetrofit).execute(volume);
    }

    @Override
    public void getUnixAudioVolumeAndCall(final Callback<Integer> callback) {
        new GetAudioVolumeTask(audioRetrofit, callback).execute();
    }

    @Override
    public void setUnixSpeakersStatus(String status) {
        new ChangeSpeakersStatusTask(audioRetrofit).execute(status);
    }

    @Override
    public void getUnixSpeakersStatusAndCall(Callback<String> callback) {
        new GetSpeakersStatusTask(audioRetrofit, callback).execute();
    }

    @Override
    public void setWinAudioVolumeUp() {
        new ChangeWinAudioVolumeTask(audioRetrofit).execute("up");
    }

    @Override
    public void setWinAudioVolumeDown() {
        new ChangeWinAudioVolumeTask(audioRetrofit).execute("down");
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
