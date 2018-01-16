package basistam.pl.remoteclient.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import basistam.pl.remoteclient.retrofit.WindowRetrofit;
import basistam.pl.remoteclient.service.address.AddressReader;
import basistam.pl.remoteclient.tasks.windows.MoveTask;
import basistam.pl.remoteclient.tasks.windows.TabSwitchTask;
import basistam.pl.remoteclient.tasks.windows.WindowSwitchTask;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WindowServiceImpl implements WindowService {

    private final WindowRetrofit windowRetrofit;

    public WindowServiceImpl(AddressReader addressReader) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        windowRetrofit = new Retrofit.Builder()
                .baseUrl(addressReader.getFullAddress())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(WindowRetrofit.class);
    }

    @Override
    public void startSwitching() {
        new WindowSwitchTask(windowRetrofit).execute("on");
    }

    @Override
    public void stopSwitching() {
        new WindowSwitchTask(windowRetrofit).execute("off");
    }

    @Override
    public void moveRight() {
        new MoveTask(windowRetrofit).execute("right");
    }

    @Override
    public void moveLeft() {
        new MoveTask(windowRetrofit).execute("left");
    }

    @Override
    public void moveUp() {
        new MoveTask(windowRetrofit).execute("up");
    }

    @Override
    public void moveDown() {
        new MoveTask(windowRetrofit).execute("down");
    }

    @Override
    public void switchTab() {
        new TabSwitchTask(windowRetrofit).execute();
    }
}
