package basistam.pl.remoteclient.tasks.windows;

import android.os.AsyncTask;

import java.io.IOException;

import basistam.pl.remoteclient.retrofit.WindowRetrofit;

public class WindowSwitchTask extends AsyncTask<String, Void, Void> {

    private final WindowRetrofit windowRetrofit;

    public WindowSwitchTask(WindowRetrofit windowRetrofit) {
        this.windowRetrofit = windowRetrofit;
    }

    @Override
    protected Void doInBackground(String... params) {
        String status = "";
        if (params.length != 0)
            status = params[0];
        try {
            windowRetrofit.switchWindow(status).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
