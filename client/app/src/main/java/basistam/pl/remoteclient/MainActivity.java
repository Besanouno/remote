package basistam.pl.remoteclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

import basistam.pl.remoteclient.listeners.VolumeChangeListener;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVolumeBar();
    }

    private void initVolumeBar() {
        SeekBar volumeBar = (SeekBar) findViewById(R.id.volumeBar);
        volumeBar.setOnSeekBarChangeListener(new VolumeChangeListener());
    }
}
