package basistam.pl.remoteclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.SeekBar;

import basistam.pl.remoteclient.controller.AudioController;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVolumeBar();
    }

    private void initVolumeBar() {
        SeekBar volumeBar = (SeekBar) findViewById(R.id.volumeBar);
        ImageButton btnSynchronize = (ImageButton) findViewById(R.id.btn_synchronize);
        new AudioController(volumeBar, btnSynchronize);
    }
}
