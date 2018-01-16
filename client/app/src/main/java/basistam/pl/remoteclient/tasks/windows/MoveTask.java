package basistam.pl.remoteclient.tasks.windows;

import android.os.AsyncTask;

import java.io.IOException;

import basistam.pl.remoteclient.retrofit.WindowRetrofit;

public class MoveTask extends AsyncTask<String, Void, Void> {

    private final WindowRetrofit windowRetrofit;

    public MoveTask(WindowRetrofit windowRetrofit) {
        this.windowRetrofit = windowRetrofit;
    }

    @Override
    protected Void doInBackground(String... params) {
        String dir = "";
        if (params.length != 0)
            dir = params[0];
        try {
            windowRetrofit.move(dir).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
