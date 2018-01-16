package basistam.pl.remoteclient.service.address;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import basistam.pl.remoteclient.R;

public class AddressService implements AddressReader, AddressWriter {

    private String address;
    private String port;

    public AddressService(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("pl.basistam.remote", Context.MODE_PRIVATE);
        this.address = sharedPreferences.getString("ADDRESS", "localhost");
        this.port = sharedPreferences.getString("PORT", "5000");
    }

    @Override
    public void save(String address, String port) {
        this.address = address;
        this.port = port;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getPort() {
        return port;
    }

    @Override
    public String getFullAddress() {
        return "http://" + address + ":" + port;
    }
}
