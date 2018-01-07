package basistam.pl.remoteclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import basistam.pl.remoteclient.controller.AudioController;
import basistam.pl.remoteclient.controller.VideoController;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControllers();
    }

    private void initControllers() {
        new AudioController(this);
        new VideoController(this);
    }
}
