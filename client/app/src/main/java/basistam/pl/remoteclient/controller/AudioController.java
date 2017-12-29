package basistam.pl.remoteclient.controller;

import android.widget.SeekBar;

import basistam.pl.remoteclient.service.AudioService;
import basistam.pl.remoteclient.service.AudioServiceImpl;

public class AudioController {

    private final AudioService audioService;

    private SeekBar volumeBar;

    public AudioController(SeekBar volumeBar) {
        this.audioService = new AudioServiceImpl();
        initVolumeBar(volumeBar);
    }

    private void initVolumeBar(SeekBar volumeBar) {
        this.volumeBar = volumeBar;
        initVolumeBarChangeListener();
        this.audioService.getAudioVolumeAndUpdate(volumeBar);
    }

    private void initVolumeBarChangeListener() {
        volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioService.setAudioVolume(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }
}
