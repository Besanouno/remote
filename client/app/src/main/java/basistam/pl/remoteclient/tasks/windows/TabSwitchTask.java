package basistam.pl.remoteclient.tasks.windows;

import android.os.AsyncTask;

import java.io.IOException;

import basistam.pl.remoteclient.retrofit.WindowRetrofit;

public class TabSwitchTask extends AsyncTask<Void, Void, Void> {

    private final WindowRetrofit windowRetrofit;

    public TabSwitchTask(WindowRetrofit windowRetrofit) {
        this.windowRetrofit = windowRetrofit;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            windowRetrofit.switchTab().execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
