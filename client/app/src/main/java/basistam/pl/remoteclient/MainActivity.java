package basistam.pl.remoteclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import basistam.pl.remoteclient.controller.AudioController;
import basistam.pl.remoteclient.controller.VideoController;
import basistam.pl.remoteclient.controller.WindowController;
import basistam.pl.remoteclient.service.address.AddressService;

public class MainActivity extends AppCompatActivity {

    private AddressService addressService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.addressService = new AddressService(this);
        setContentView(R.layout.activity_main);
        initControllers();
    }

    private void initControllers() {
        new AudioController(this, addressService);
        new VideoController(this, addressService);
        new WindowController(this, addressService);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.GONE);

    }
}
