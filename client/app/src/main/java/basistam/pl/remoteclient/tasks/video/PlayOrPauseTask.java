package basistam.pl.remoteclient.tasks.video;

import android.os.AsyncTask;

import java.io.IOException;

import basistam.pl.remoteclient.retrofit.VideoRetrofit;

public class PlayOrPauseTask extends AsyncTask<Void, Void, Void> {

    private final VideoRetrofit videoRetrofit;

    public PlayOrPauseTask(VideoRetrofit videoRetrofit) {
        this.videoRetrofit = videoRetrofit;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            videoRetrofit.playOrPause().execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
