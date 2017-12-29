package basistam.pl.remoteclient.controller;

import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

import basistam.pl.remoteclient.service.AudioService;
import basistam.pl.remoteclient.service.AudioServiceImpl;
import basistam.pl.remoteclient.utils.Callback;

public class AudioController {

    private final AudioService audioService;

    private SeekBar volumeBar;

    public AudioController(SeekBar volumeBar, ImageButton btnSynchronize) {
        this.audioService = new AudioServiceImpl();
        initVolumeBar(volumeBar);
        initBtnSynchronize(btnSynchronize);
    }

    private void initVolumeBar(SeekBar volumeBar) {
        this.volumeBar = volumeBar;
        initVolumeBarChangeListener();
        synchronizeVolumeBar();
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

    private void synchronizeVolumeBar() {
        audioService.getAudioVolumeAndCall(param -> volumeBar.setProgress(param));
    }

    private void initBtnSynchronize(ImageButton btnSynchronize) {
        btnSynchronize.setOnClickListener(view -> synchronizeVolumeBar());
    }
}
