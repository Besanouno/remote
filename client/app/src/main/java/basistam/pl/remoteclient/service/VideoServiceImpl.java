package basistam.pl.remoteclient.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import basistam.pl.remoteclient.retrofit.VideoRetrofit;
import basistam.pl.remoteclient.service.address.AddressReader;
import basistam.pl.remoteclient.tasks.video.PlayOrPauseTask;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideoServiceImpl implements VideoService {

    private final VideoRetrofit videoRetrofit;

    public VideoServiceImpl(AddressReader addressReader) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        videoRetrofit = new Retrofit.Builder()
                .baseUrl(addressReader.getFullAddress())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(VideoRetrofit.class);
    }

    @Override
    public void playOrPause() {
        new PlayOrPauseTask(videoRetrofit).execute();
    }
}
